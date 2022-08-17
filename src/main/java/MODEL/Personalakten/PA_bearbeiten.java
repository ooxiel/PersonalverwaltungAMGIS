package MODEL.Personalakten;

import MODEL.Login.CreateNew.AccountErzeugen;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.stream.Stream;

public class PA_bearbeiten {
    public Connection con=null;//Globale Variable für die Verbindung zur Datenbank
    public PA_bearbeiten(){
        //Verbindung zur Datenbank wird hergestellt
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        }catch(ClassNotFoundException e) {
            return;
        }
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Benutzerdaten für einen HR-Zugang werden automatisch generiert
    public void generateHR (int id, String vorname, String nachname, boolean root, JPanel main){
        AccountErzeugen aE = new AccountErzeugen();//Objekt erzeugen mit dem dann der Benutzername und das Passwort genriert wird
        String HR_username = aE.hr_kontoname_erzeugen(id,vorname,nachname);
        String HR_pw       = aE.passwort_erzeugen();
        //SQL Statement Strings erstellen
        String sqlCHECKID = "SELECT HR_ID FROM mitarbeiterstamm WHERE person_id="+id+" AND HR_ID=-1";
        String sql = "INSERT INTO HRROOT VALUES("+id+",'"+HR_username+"','"+HR_pw+"',"+root+"); UPDATE MITARBEITERSTAMM SET HR_ID="+id+" WHERE person_id="+id+";";
        try {
            Statement stmt = con.createStatement();//Statement erstellen
            ResultSet rs = stmt.executeQuery(sqlCHECKID);//Ausführen des Ersten Statements
            //Wir prüfen ob die Personalakte bereits einen HR-Zugang hat.
            //Wenn nämlich die HR_ID nicht auf -1 ist, dann haben wir einen Zugang und wir gehen in den else Zweig
            if(rs.next()){
                stmt.executeQuery(sql);//Zugangdaten in die Datenbank einfügen
                stmt.close();//schließen
                //Daten anzeigen in einem PopUpFenster
                JOptionPane.showMessageDialog(main,"HR Logindaten: \nKontoname: "+HR_username+"\nPasswort: "+HR_pw);
            }else{
                //HR-Zugang existiert bereits
                JOptionPane.showMessageDialog(main,"HR Account existiert bereits!");
            }
        } catch (SQLException ex) {throw new RuntimeException(ex);
        }
    }

    public void speichernPA(int id, String anrede, String vorname,String zweitname, String nachname,String geburtsdatum, String telefon, String email,String strasse,String hausNR,
                            String hausB,String land,String bundesland,String plz,String jobname,String besGrad,String abteilung,String abtLeiter,String raum,String standort,String erstelltDatum){
        //Letzte Änderung wird aktualisiert
        String letzteAenderung=String.valueOf(LocalDateTime.now());
        String sql_Mstamm= "UPDATE Mitarbeiterstamm SET anrede='"+anrede+"',vorname='"+vorname+"',zweitname='"+zweitname+"',nachname='"+nachname+"',geburtstag='"+geburtsdatum+"',telefon='"+telefon+"',email='"+email+"',Aenderung_Datum ='"+letzteAenderung+"' WHERE person_id="+id;
        String sql_adresse="UPDATE Adressinfo SET strasse='" +strasse+"', strassen_nummer='"+hausNR+"',Strassen_buchstabe ='"+hausB +"',land='"+land+"',bundesland='"+bundesland+"',plz='"+plz+"' WHERE adress_id="+id;
        String sql_jobinfo="UPDATE JOBINFO SET jobname='"+jobname+"',Beschaeftigungsgrad ='"+besGrad +"', Abteilung='"+abteilung +"',Abteilungsleiter ='"+abtLeiter+"',Raum ='"+raum +"',Standort ='"+standort+"' WHERE job_ID="+id;
        try {
            Statement stmt = con.createStatement();//Statement erstellen
            //Statements ausführen
            stmt.executeQuery(sql_Mstamm);
            stmt.executeQuery(sql_adresse);
            stmt.executeQuery(sql_jobinfo);

            try {
                /*
                Wenn der Personalakte Daten angefügt wurden -> Pending Ordner NICHT Leer ist, wird erstmal
                geprüft ob es ein Ordner bereits angelegt wurde oder nicht.
                */
                if (!isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))){

                    if(new File("src/main/resources/AktenFiles/"+id).exists()){
                        //Ordner ist vorhanden -> Start und Ziel Ordner deklarieren
                        //(Pending ist der Start, id der Personalakte das Ziel)
                        //Methode zum kopieren aufrufen
                        Path targetPath = Paths.get("src/main/resources/AktenFiles/"+id);
                        Path sourcePath = Paths.get("src/main/resources/AktenFiles/Pending");
                        Files.walk(sourcePath).forEach(path -> showFile(path.toFile(),targetPath,id));
                    }else{
                        //Ordner erstellen mit der ID der Personalakte
                        //(Pending ist der Start, id der Personalakte das Ziel)
                        //Methode zum kopieren aufrufen
                        new File("src/main/resources/AktenFiles/"+id).mkdirs();
                        Path targetPath = Paths.get("src/main/resources/AktenFiles/"+id);
                        Path sourcePath = Paths.get("src/main/resources/AktenFiles/Pending");
                        Files.walk(sourcePath).forEach(path -> showFile(path.toFile(),targetPath,id));
                    }
                }
            } catch (IOException e) {throw new RuntimeException(e);}
            stmt.close();//Statement schließen
        } catch (SQLException e) {throw new RuntimeException(e);}


    }
    public void showFile(File file, Path targetPath,int newID) {
        if(file.isDirectory()) {
            return;
        } else {
            try {
                //File wird aus dem Pending Ordner in den Persönlichen Ordner verlegt
                Files.move(Path.of(file.getAbsolutePath()), targetPath.resolve(file.getName()));
                //SQL Statement als String erstellen
                String sql_insertFile = "INSERT INTO AKTENKENNZEICHEN VALUES ("+nextPOS_NR()+" ,'"+targetPath.resolve(file.getName())+"',"+newID+");";
                Statement stmt = con.createStatement();//Statement erstellen
                stmt.executeQuery(sql_insertFile);//Statement ausführen
                stmt.close();//Statement schließen
            } catch (IOException e) {throw new RuntimeException(e);} catch (SQLException e) {throw new RuntimeException(e);}
        }
    }
    //Ermittlung des PrimaryKeys für Aktenkennzeichen
    public int nextPOS_NR(){
        int nextid=1;//Falls die Datenbank keine Einträge hat ist das unsere erste ID
        try{
            Statement stmt=con.createStatement();//Statement erstellen
            String sql= "SELECT * FROM Aktenkennzeichen";//SQL Statement
            ResultSet r= stmt.executeQuery(sql);//Statement ausführen und Ergebnisse in einem ResultSet speichern
            while(r.next()){//Solange es weitere Daten gibt wird die if-Bedingung geprüft
                //Wenn unsere lokale ID kleiner ist als eine Vorhandene ID wird die Vorhandene ID unsere neue,
                //bis es keine größere mehr gibt
                if(r.getInt(1)>nextid)
                    nextid=r.getInt(1);
            }
            nextid++;//Weil unsere ID, die einer Vorhandenen ist, addieren wir +1
            r.close();//ResultSet schließen
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return nextid;//ID zurückgeben
    }

    //Prüfen ob ein Directory leer ist.
    public boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {//Prüfen ob der Path ein Directory
            //Inhalt des Directory auf Leere prüfen
            try (Stream<Path> entries = Files.list(path)) {
                return !entries.findFirst().isPresent();
                //Wenn keine Datei vorhanden ist -> true zurückgeben
                //Wenn eine Datei vorhanden ist  -> false zurückgeben
            }
        }
        return false;
    }

}