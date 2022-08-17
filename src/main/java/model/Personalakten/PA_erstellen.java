package model.Personalakten;

import view.Personalakte.Login.CreateNew.AccountErzeugen;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static javax.swing.JOptionPane.*;

public class PA_erstellen {
    public Connection con=null;//Globale Variable für die Verbindung zur Datenbank
    public PA_erstellen(){
        //Verbindung zur Datenbank wird hergestellt
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return;}
        try {con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis"); }catch(SQLException e){e.printStackTrace();}}

    //Konstruktor
    public void einfuegenPA(String anrede, String vorname,String zweitname, String nachname,String geburtsdatum, String telefon, String email,String strasse,String hausNR,
                            String hausB,String land,String bundesland,String plz,String jobname,String besGrad,String abteilung,String abtLeiter,String raum,String standort, JPanel main){

        int newID=generateID();//Neue ID erstellen

        //Statements für das Speichern der Daten
        String sql_Mstamm = "INSERT INTO Mitarbeiterstamm VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prep_Mstamm=null;

        String sql_Adr = "INSERT INTO Adressinfo VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prep_Adr=null;

        String sql_MLogin = "INSERT INTO Mitarbeiterlogin VALUES(?,?,?)";
        PreparedStatement prep_MLogin=null;

        String sql_AStamm = "INSERT INTO Aktenstamm VALUES(?,?)";
        PreparedStatement prep_AStamm=null;

        String sql_JobInfo = "INSERT INTO Jobinfo VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prep_JobInfo=null;

        try {
            //Statement mit Connection und dem String aktualisieren
            prep_Mstamm = con.prepareStatement(sql_Mstamm);
            prep_Adr = con.prepareStatement(sql_Adr);
            prep_MLogin=con.prepareStatement(sql_MLogin);
            prep_AStamm=con.prepareStatement(sql_AStamm);
            prep_JobInfo=con.prepareStatement(sql_JobInfo);
        } catch (SQLException e) {e.printStackTrace();}
        try {
            /*
            Mit 'prep_Tabelle.set___(Index, x)'
            wird an der Stelle des Index das '?' durch x ersetzt
             */
            //Mitarbeiterlogin
            //Objekt erzeugen um Kontoname+Passwort zu erzeugen
            AccountErzeugen aE=new AccountErzeugen();
            prep_MLogin.setInt(1,newID);
            prep_MLogin.setString(2,aE.kontoname_erzeugen(newID,vorname,nachname));
            prep_MLogin.setString(3,aE.passwort_erzeugen());
            prep_MLogin.executeUpdate();//Änderungen ausführen
            //AdressInfo
            prep_Adr.setInt(1,newID);
            prep_Adr.setString(2,strasse);
            prep_Adr.setString(3,hausNR);
            prep_Adr.setString(4,hausB);
            prep_Adr.setString(5,land);
            prep_Adr.setString(6,bundesland);
            prep_Adr.setString(7,plz);
            prep_Adr.executeUpdate();//Änderungen ausführen

            //Jobinfo
            prep_JobInfo.setInt(1,newID);
            prep_JobInfo.setString(2,jobname);
            prep_JobInfo.setString(3,besGrad);
            prep_JobInfo.setString(4,abteilung);
            prep_JobInfo.setString(5,abtLeiter);
            prep_JobInfo.setString(6,raum);
            prep_JobInfo.setString(7,standort);
            prep_JobInfo.executeUpdate();//Änderungen ausführen

            //Aktenstamm
            prep_AStamm.setInt(1,newID);
            prep_AStamm.setInt(2,newID);
            prep_AStamm.executeUpdate();//Änderungen ausführen

            //Mitarbeiterstamm
            prep_Mstamm.setInt(1,newID);
            prep_Mstamm.setString(2,anrede);
            prep_Mstamm.setString(3,vorname);
            prep_Mstamm.setString(4,zweitname);
            prep_Mstamm.setString(5,nachname);
            prep_Mstamm.setString(6,geburtsdatum);
            prep_Mstamm.setString(7,telefon);
            prep_Mstamm.setString(8,email);
            prep_Mstamm.setString(9,String.valueOf(LocalDateTime.now()));
            prep_Mstamm.setString(10,String.valueOf(LocalDateTime.now()));
            prep_Mstamm.setInt(11,newID);
            prep_Mstamm.setInt(12,newID);
            prep_Mstamm.setInt(13,newID);
            prep_Mstamm.setInt(14,-1);
            prep_Mstamm.executeUpdate();//Änderungen ausführen
            //Statements schließen
            prep_Mstamm.close();
            prep_Adr.close();
            prep_MLogin.close();
            prep_AStamm.close();
            prep_JobInfo.close();

            //Logindaten anzeigen
            showLogindaten(newID,main);

            try {
                /*
                Wenn in dem Pending Ordner Daten eingefügt sind, wird ein persönlicher
                Ordner (Wenn nicht vorhanden) für die Personalakte erstellt.
                Dann werden die beiden Pfade erstellt und in die Methode übergeben.
                Dabei werden Alle Dateien aus dem Pending Ordner in den target Ordner übergeben
                 */
                if (!isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))){
                    new File("src/main/resources/AktenFiles/"+newID);
                    Path targetPath = Paths.get("src/main/resources/AktenFiles/"+newID);
                    Path sourcePath = Paths.get("src/main/resources/AktenFiles/Pending");
                    Files.walk(sourcePath).forEach(path -> showFile(path.toFile(),targetPath,newID));
                }
            } catch (IOException e) {throw new RuntimeException(e);}
        } catch (SQLException e) {e.printStackTrace();}
    }
    //Nach erstellen einer Personalakte sollen die Anmeldedaten gezeigt werden
    private void  showLogindaten(int newID, JPanel main) {
        //Wir wollen nur den username und passwort von einer Person anzeigen, deshalb die WHERE-Bedingung
        String sql="SELECT username, password FROM Mitarbeiterlogin WHERE M_ID="+newID;//SQL Statement als String
        String username = null;String password = null;//Strings initialisieren
        try {
            Statement stmt = con.createStatement();//Statement erstellen
            ResultSet r= stmt.executeQuery(sql);//Statement ausführen und Ergebniss speichern
            while(r.next()) {
                //Username und Passwort aktualiseren mit den Daten aus der Datenbank
                username = String.valueOf(r.getString(1));
                password = String.valueOf(r.getString(2));
            }
            r.close();//schließen
            stmt.close();
        } catch (SQLException e) {throw new RuntimeException(e);}
        //PopUp-Fenster öffnet sich und zeigt die Daten an
        showMessageDialog(main, "Logindaten  \nKontoname:  "+username+"\nPasswort:  "+password);
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

    public int generateID(){
        int nextid=1;//Falls die Datenbank keine Einträge hat ist das unsere erste ID
        try{
            Statement stmt=con.createStatement();//Statement erstellen
            String sql= "SELECT * FROM Mitarbeiterlogin";//SQL Statement
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
        return nextid;//neue ID zurückgeben
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