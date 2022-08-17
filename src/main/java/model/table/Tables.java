package model.table;

import controller.tablemodel.login.hr.LogindatenTableModel;
import controller.tablemodel.login.mitarbeiter.Mitarbeiterlogindaten;
import controller.tablemodel.login.mitarbeiter.MitarbeiterlogindatenTableModel;
import controller.tablemodel.personalakten.PersonalaktenTableModel;
import controller.tablemodel.login.hr.Logindaten;
import controller.tablemodel.personalakten.Personalakten;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Tables {
    //Globale Variable fuer die Datenbank Connection
    public Connection con=null;

    public AbstractTableModel resultSQL_PA(String sql){
        // Das SQL Statement wird im Methodenaufruf übergeben!!
        try{
            con=getCon();//Verbindung herstellen
            Statement stmt = con.createStatement();//Statement fuer die Datenbank erstellen
            ResultSet rs=stmt.executeQuery(sql);
            /*
            Ergebnisse des ausgeführten Statements werden in einem ResultSet gespeichert und
            werden in der While schleife nacheinander ausgelesen und damit ein Personalaktenobjekt erzeugt.
            und der ArrayList personalakten hinzugefügt.
            */
            List<Personalakten> personalakten = new ArrayList<>();

            while (rs.next()){
                int id                      =rs.getInt(1);
                String anrede               =String.valueOf(rs.getString(2));
                String vorname              =String.valueOf(rs.getString(3));
                String zweitname            =String.valueOf(rs.getString(4));
                String nachname             =String.valueOf(rs.getString(5));
                String jobname              =String.valueOf(rs.getString(6));
                String abteilung            =String.valueOf(rs.getString(7));
                String standort             =String.valueOf(rs.getString(8));
                Personalakten pa = new Personalakten(id,anrede,vorname,zweitname,nachname,jobname,abteilung,standort);
                personalakten.add(pa);
            }
            //Ein PersonalaktenTableModel wird erstellt, welche die ArrayList mit den vorher erzeugten
            //Objekten als Parameter übergeben bekommt und die Tabelle damit füllt.
            PersonalaktenTableModel patm = new PersonalaktenTableModel(personalakten);
            rs.close();//resultset schließen
            stmt.close();//statement schließen
            try{con.close();/*Verbindung schließen*/}catch(SQLException ex) {ex.printStackTrace();}
            return patm;//Tabelle wird zurückgegeben
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JTable filterTable(JTable table,String anrede, String vorname, String nachname,String jobname, String abteilung, String standort){
        //SQL Statement. Die Parameter werden in den String eingefügt.
        String sql= "SELECT  ms.person_id, ms.anrede, ms.vorname, ms.zweitname,ms.nachname,ji.jobname,  ji.abteilung,ji.standort FROM mitarbeiterstamm ms, adressinfo ai, jobinfo ji  WHERE  ms.anrede LIKE '%"+anrede+"%' AND ms.vorname LIKE '%"+vorname+"%' AND ms.nachname LIKE '%"+nachname+"%' AND ji.jobname LIKE '%"+jobname+"%' AND ji.abteilung LIKE'%"+abteilung+"%' AND ji.standort LIKE '%"+standort+"%' AND ms.person_id=ai.Adress_ID AND person_id=ji.job_ID";
        //Das Table, dass übergeben wurde, wird aktualisiert indem wir die resultSQL_PA(sql) Methode aufrufen
        //und das table mit .setModel auf das Ergebniss der Methode gesetzt wird.
        table.setModel(resultSQL_PA(sql));
        return table;//zurückgeben der gefilterten Tabelle
    }

    public JTable defaultTableAccounts(JTable table){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            con=getCon();//Connection herstellen
            String sql= "SELECT  * FROM HRroot";//SQL Abfrage
            Statement stmt = con.createStatement();//Statement fuer die Datenbank erstellen
            //Statement wird ausgeführt und Ergebnisse werden in einem ResultSet gespeichert
            ResultSet rs=stmt.executeQuery(sql);

            //ArrayList erstellen in dem die Objekte gespeichert werden
            List<Logindaten> logindaten = new ArrayList<>();

            while (rs.next()){
                //Solange unser ResultSet weitere Daten hat, werden diese ausgelesen und ein Objekt erzeugt
                //und der ArrayList hinzugefügt
                int id = rs.getInt(1);
                String kontoname = String.valueOf(rs.getString(2));
                String passwort = String.valueOf(rs.getString(3));
                boolean isRoot = rs.getBoolean(4);
                Logindaten ld = new Logindaten(id,kontoname,passwort,isRoot);
                logindaten.add(ld);
            }
            //LogindatenTableModel Objekt erzeugen und die ArrayList übergeben
            LogindatenTableModel ldtm = new LogindatenTableModel(logindaten);
            table.setModel(ldtm);//Table mit dem neuen Daten aktualisieren
            rs.close();//ResultSet und Statement schließen
            stmt.close();
            try{con.close();}catch(SQLException ex) {ex.printStackTrace();}//Verbindung schließen
            return table;//Tabelle zurückgeben
        }catch (Exception e){e.printStackTrace();}
    return table;
    }

    public JTable defaultTableMlogin(JTable table){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            con=getCon();//Connection herstellen
            String sql= "SELECT * FROM Mitarbeiterlogin";//SQL Abfrage
            Statement stmt = con.createStatement();//Statement fuer die Datenbank erstellen
            //Statement wird ausgeführt und Ergebnisse werden in einem ResultSet gespeichert
            ResultSet rs=stmt.executeQuery(sql);
            //ArrayList erstellen in dem die Objekte gespeichert werden
            List<Mitarbeiterlogindaten> mitarbeiterlogindaten = new ArrayList<>();

            while (rs.next()){
                //Solange unser ResultSet weitere Daten hat, werden diese ausgelesen und ein Objekt erzeugt
                //und der ArrayList hinzugefügt
                int id = rs.getInt(1);
                String kontoname = String.valueOf(rs.getString(2));
                String passwort = String.valueOf(rs.getString(3));
                Mitarbeiterlogindaten mld = new Mitarbeiterlogindaten(id,kontoname,passwort);
                mitarbeiterlogindaten.add(mld);
            }
            //MitarbeiterlogindatenTableModel Objekt erzeugen und die ArrayList übergeben
            MitarbeiterlogindatenTableModel mldtm = new MitarbeiterlogindatenTableModel(mitarbeiterlogindaten);
            table.setModel(mldtm);//Table mit dem neuen Daten aktualisieren
            rs.close();//ResultSet und Statement schließen
            stmt.close();
            try{con.close();}catch(SQLException ex) {ex.printStackTrace();}//Verbindung schließen
            return table;//Tabelle zurückgeben
        }catch (Exception e){e.printStackTrace();}
        return table;
    }

    //Methode mit der die Connection zur Datenbank hergestellt wird
    public Connection getCon(){
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return null;}
        //Datenbank ist dem Projektordner gespeichert. Wir uebergeben der Methode den Pfad, Den Benutzernamen und Passwort der Datenbank
        try {Connection con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");return con;}catch(SQLException e){e.printStackTrace();}
        return null;
    }
}
