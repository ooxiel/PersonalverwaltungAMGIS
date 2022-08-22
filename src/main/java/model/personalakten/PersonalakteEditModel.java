package model.personalakten;

import model.login.create.AccountErzeugen;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.stream.Stream;


/** ====================================================================================================================
 * Klasse enthaelt alle Funktionen um den vollstaendigen Bearbeitungsprozess einer Personalakte abzubilden
 * =====================================================================================================================
 */
public class PersonalakteEditModel {
    public Connection con=null;         //Globale Variable für die Verbindung zur Datenbank

    /** ================================================================================================================
     * Konstrukor der Klasse PersonalakteEditModel
     *
     * Konstruktor enthaelt Aufruf zur Datenbankverbindung
     */
    public PersonalakteEditModel(){
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

    /** ================================================================================================================
     * Methode generiert einen HR-Zugang.
     *
     * @param id        Personal-ID
     * @param vorname   Vorname
     * @param nachname  Nachname
     * @param root      Root-Berechtigung
     * @param main      anzuzeigende Inhalte
     */
    public void generateHR (int id, String vorname, String nachname, boolean root, JPanel main){
        /*
            Objekt erzeugen mit dem Benutzername und Passwort generiert werden
         */
        AccountErzeugen aE = new AccountErzeugen();
            String HR_username = aE.hr_kontoname_erzeugen(id,vorname,nachname);
            String HR_pw       = aE.passwort_erzeugen();

        /*
            SQL Statement Strings erstellen
         */
        String sqlCHECKID = "SELECT HR_ID FROM mitarbeiterstamm WHERE person_id="+id+" AND HR_ID=-1";
        String sql = "INSERT INTO HRROOT VALUES("+id+",'"+HR_username+"','"+HR_pw+"',"+root+"); UPDATE MITARBEITERSTAMM SET HR_ID="+id+" WHERE person_id="+id+";";
        try {
            Statement stmt = con.createStatement();             //Statement erstellen
            ResultSet rs = stmt.executeQuery(sqlCHECKID);       //Ausfuehren des ersten Statements

            /*
                Pruefung, ob die Personalakte bereits einen HR-Zugang
                Wenn HR_ID nicht auf -1, dann Zugang bereits existent
             */

            if(rs.next()){
                stmt.executeQuery(sql);         //Zugangdaten in die Datenbank einfuegen
                stmt.close();                   //schliessen

                //Daten anzeigen in einem PopUpFenster
                JOptionPane.showMessageDialog(main,"HR Logindaten: \nKontoname: "+HR_username+"\nPasswort: "+HR_pw);
            }else{

                //HR-Zugang existiert bereits
                JOptionPane.showMessageDialog(main,"HR Account existiert bereits!");
            }
        } catch (SQLException ex) {throw new RuntimeException(ex);
        }
    }

    /** ================================================================================================================
     * Methode speichert eine Personalakte und alle zugehoerigen Dateien
     *
     * @param id            Personal-ID
     * @param anrede        Anrede
     * @param vorname       Vorname
     * @param zweitname     Zweitname
     * @param nachname      Nachname
     * @param geburtsdatum  Geburtsdatum
     * @param telefon       Telefonnummer
     * @param email         E-Mail-Adresse
     * @param strasse       wohnhafte Strasse
     * @param hausNR        wohnhafte Hausnummer
     * @param hausB         wohnhafte Hausnummerzusatz (z.B. Buchstabe)
     * @param land          wohnhaftes Land
     * @param bundesland    wohnhaftes Bundesland
     * @param plz           wohnhafte Postleitzahl
     * @param jobname       Berufsbezeichnung
     * @param besGrad       Beschaftigungsgrad
     * @param abteilung     Abteilung
     * @param abtLeiter     Abteilungsleiter
     * @param raum          Bueroraum
     * @param standort      Buerostandort
     * @param erstelltDatum 'Erstellt am'-Datum
     */

    public void speichernPA(int id, String anrede, String vorname,String zweitname, String nachname,String geburtsdatum, String telefon, String email,String strasse,String hausNR,
                            String hausB,String land,String bundesland,String plz,String jobname,String besGrad,String abteilung,String abtLeiter,String raum,String standort,String erstelltDatum){

        //Letzte Änderung wird aktualisiert
        String letzteAenderung=String.valueOf(LocalDateTime.now());
        String sql_Mstamm= "UPDATE Mitarbeiterstamm SET anrede='"+anrede+"',vorname='"+vorname+"',zweitname='"+zweitname+"',nachname='"+nachname+"',geburtstag='"+geburtsdatum+"',telefon='"+telefon+"',email='"+email+"',Aenderung_Datum ='"+letzteAenderung+"' WHERE person_id="+id;
        String sql_adresse="UPDATE Adressinfo SET strasse='" +strasse+"', strassen_nummer='"+hausNR+"',Strassen_buchstabe ='"+hausB +"',land='"+land+"',bundesland='"+bundesland+"',plz='"+plz+"' WHERE adress_id="+id;
        String sql_jobinfo="UPDATE JOBINFO SET jobname='"+jobname+"',Beschaeftigungsgrad ='"+besGrad +"', Abteilung='"+abteilung +"',Abteilungsleiter ='"+abtLeiter+"',Raum ='"+raum +"',Standort ='"+standort+"' WHERE job_ID="+id;

        try {
            Statement stmt = con.createStatement(); //Statement erstellen

            //Statements ausführen
            stmt.executeQuery(sql_Mstamm);
            stmt.executeQuery(sql_adresse);
            stmt.executeQuery(sql_jobinfo);

            /*
                Wenn der Personalakte Daten angefügt wurden -> Pending Ordner NICHT Leer ist, wird erstmal
                geprueft ob es ein Ordner bereits angelegt wurde oder nicht.
                */

            try {

                if (!isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))){

                    if(new File("src/main/resources/AktenFiles/"+id).exists()){
                        /*
                            Ordner ist vorhanden -> Start und Ziel Ordner deklarieren
                            (Pending ist der Start, id der Personalakte das Ziel)

                            Methode zum Kopieren aufrufen
                         */

                        Path targetPath = Paths.get("src/main/resources/AktenFiles/"+id);
                        Path sourcePath = Paths.get("src/main/resources/AktenFiles/Pending");
                        Files.walk(sourcePath).forEach(path -> showFile(path.toFile(),targetPath,id));
                    }else{

                        /*
                            Ordner erstellen mit der ID der Personalakte
                            (Pending ist der Start, id der Personalakte das Ziel)

                            Methode zum kopieren aufrufen
                         */

                        new File("src/main/resources/AktenFiles/"+id).mkdirs();
                        Path targetPath = Paths.get("src/main/resources/AktenFiles/"+id);
                        Path sourcePath = Paths.get("src/main/resources/AktenFiles/Pending");
                        Files.walk(sourcePath).forEach(path -> showFile(path.toFile(),targetPath,id));
                    }
                }
            } catch (IOException e) {throw new RuntimeException(e);}
            stmt.close();                                               //Statement schließen
        } catch (SQLException e) {throw new RuntimeException(e);}


    }

    /** ================================================================================================================
     * Methode verschiebt Datei vom Zwischenspeicher (Peding-Ordner) in den einzigartigen Ordner der Personalakte mit
     * der Personal-ID als Titel
     *
     * @param file          Datei
     * @param targetPath    Zieldateipfad
     * @param newID         Personal-ID
     */

    public void showFile(File file, Path targetPath,int newID) {
        if(file.isDirectory()) {
            return;
        } else {
            try {
                /*
                    File wird aus dem Pending Ordner in den Persönlichen Ordner verlegt
                 */
                Files.move(Path.of(file.getAbsolutePath()), targetPath.resolve(file.getName()));

                /*
                    SQL Statement als String erstellen
                 */
                String sql_insertFile = "INSERT INTO AKTENKENNZEICHEN VALUES ("+nextPOS_NR()+" ,'"+targetPath.resolve(file.getName())+"',"+newID+");";
                Statement stmt = con.createStatement();     //Statement erstellen
                stmt.executeQuery(sql_insertFile);          //Statement ausführen
                stmt.close();                               //Statement schließen

            } catch (IOException e) {throw new RuntimeException(e);} catch (SQLException e) {throw new RuntimeException(e);}
        }
    }
    //Ermittlung des PrimaryKeys für Aktenkennzeichen

    /** ================================================================================================================
     * Methode ermittelt den PK fuer die Tablle Aktenkennzeichen
     *
     * @return  ID
     */
    public int nextPOS_NR(){
        int nextid=1;                                       //Falls die Datenbank keine Eintraege hat, ist diese erste ID
        try{
            Statement stmt=con.createStatement();           //Statement erstellen
            String sql= "SELECT * FROM Aktenkennzeichen";   //SQL Statement
            ResultSet r= stmt.executeQuery(sql);            //Statement ausfuehren und Ergebnisse in einem ResultSet speichern

            /*
                Solange es weitere Daten gibt wird die if-Bedingung geprueft
                Wenn lokale ID kleiner ist als eine vorhandene ID wird die vorhandene ID die neue ID, bis es keine groeßere mehr gibt
             */

            while(r.next()){
                if(r.getInt(1)>nextid)
                    nextid=r.getInt(1);
            }
            nextid++;               // ID ist die einer bereits vorhandenen, deswegen +1
            r.close();              //ResultSet schließen
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return nextid;              //ID zurückgeben
    }

    /** ================================================================================================================
     * Methode ueberpruft vor dem Loeschen, ob der Ordner bereits leer ist.
     *
     * @param path              Ordnerpfad
     * @return                  true -> Ordner ist leer; false -> Ordner ist nicht leer
     * @throws IOException
     */
    public boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {                          //Pruefen ob der Path ein Directory

            /*
                Inhalt des Directory auf Leere pruefen

                Wenn keine Datei vorhanden ist -> true zurueckgeben
                Wenn eine Datei vorhanden ist  -> false zurueckgeben
             */
            try (Stream<Path> entries = Files.list(path)) {
                return !entries.findFirst().isPresent();

            }
        }
        return false;
    }

}