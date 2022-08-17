package model.login.check;

import java.sql.*;

public class LoginCheck {
    public Connection c=null;//Globale Variable für die Verbindung zur Datenbank
    public LoginCheck() {
        //Verbindung zur Datenbank wird hergestellt
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        }catch(ClassNotFoundException e) {
            return;
        }
        try {
            c = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis"); //url,user,pw
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    //Prüfung ob die Eingabe mit einem Datenbankeintrag in der Mitarbeiterlogintabelle übereinstimmt
    public boolean validateKontoname_M(String eingabeKN){
        if(eingabeKN.equals(checkKontonameInMitarbeiter(eingabeKN))){return true;}
        else{return false;}
    }
    //Prüfung ob die Eingabe mit einem Datenbankeintrag in der HRlogintabelle übereinstimmt
    public boolean validateKontoname_HR(String eingabeKN){
        if(eingabeKN.equals(checkKontonameInHR(eingabeKN))){return true;}
        else{return false;}
    }
    //(Mitarbeiter)PRüfung ob der Kontoname und Passwort in der Datenbank vorhanden sind und richtig sind
    public boolean validatePasswort_M(String eingabeKN,String eingabePW){
        if (validateKontoname_M(eingabeKN)==true && eingabePW.equals(getPasswort_M(Integer.parseInt(searchIDwithKN_M(eingabeKN))))){
            return true;
        }else{return false;}
    }
    //(HR)PRüfung ob der Kontoname und Passwort in der Datenbank vorhanden sind und richtig sind
    public boolean validatePasswort_HR(String eingabeKN,String eingabePW){
        if (validateKontoname_HR(eingabeKN)==true && eingabePW.equals(getPasswort_HR(Integer.parseInt(searchIDwithKN_HR(eingabeKN))))){
            return true;
        }else{return false;}
    }
    //Prüfen ob der Kontoname in der HR Logintabelle vorhanden ist
    private String checkKontonameInHR(String KN) {
        try {
            Statement stmt=c.createStatement();
            String sqlPW= "SELECT username FROM HRRoot WHERE username='"+KN+"'";
            ResultSet res= stmt.executeQuery(sqlPW);
            if (res.next()){
                String kn=res.getString(1);
                return kn;
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Kontoname nicht vorhanden";
    }
    //Prüfen ob der Kontoname in der Mitarbeiterlogintabelle vorhanden ist
    private String checkKontonameInMitarbeiter(String KN) {
        try {
            Statement stmt=c.createStatement();
            String sqlPW= "SELECT username FROM Mitarbeiterlogin WHERE username='"+KN+"'";
            ResultSet res= stmt.executeQuery(sqlPW);
            if (res.next()){
                String kn=res.getString(1);
                return kn;
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Kontoname nicht vorhanden";
    }
    //Prüfen ob ein HR Account Root-Berechtigung besitzt
    public boolean isRoot(int id) {
        try {
            Statement stmt = c.createStatement();
            //'Root' ist ein boolean der aussagt ob Root-Berechtigung vorliegt oder nicht
            String sql = "SELECT Root FROM HRRoot WHERE HR_ID='"+id+"'";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()){
                //zurück geben des boolean root des Ausgewählten Accounts
                return res.getBoolean(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //ID suchen mithilfe des Kontonamen
    public String searchIDwithKN_HR(String kontoname){
        try {
            Statement stmt=c.createStatement();
            String sqlID= "SELECT hr_id FROM HRRoot WHERE username='"+kontoname+"'";
            ResultSet res= stmt.executeQuery(sqlID);
            if (res.next()){
                String id=res.getString(1);
                return id;
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Falscher Kontoname!";
    }
    //ID suchen mithilfe des Kontonamen
    public String searchIDwithKN_M(String kontoname){
        try {
            Statement stmt=c.createStatement();
            String sqlID= "SELECT m_id FROM Mitarbeiterlogin WHERE username='"+kontoname+"'";
            ResultSet res= stmt.executeQuery(sqlID);
            if (res.next()){
                String id=res.getString(1);
                return id;
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Falscher Kontoname!";
    }
    //Passwort suchen mit dem Primary Key ID für HR Daten
    private String getPasswort_HR(int id) {
        try {
            Statement stmt=c.createStatement();//Statement erstellen
            String sqlPW= "SELECT password FROM HRRoot WHERE hr_id='"+id+"'";//SQL Abfrage
            ResultSet res= stmt.executeQuery(sqlPW);//Statement ausführen und Ergebniss speichern
            if (res.next()){//Wenn das Ergebniss der Abfrage einen nächsten Wert hat wird die if-B. erfüllt
                //Wert aus dem ResultSet in einem String speichern und zurück geben
                String pw=res.getString(1);
                return pw;
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "id/passwort nicht vorhanden";
    }
    //Passwort suchen mit dem Primary Key ID für Mitarbeiterdaten
    private String getPasswort_M(int id) {
        try {
            Statement stmt=c.createStatement();//Statement erstellen
            String sqlPW= "SELECT password FROM Mitarbeiterlogin WHERE m_id='"+id+"'";//SQL Abfrage
            ResultSet res= stmt.executeQuery(sqlPW);//Statement ausführen und Ergebniss speichern
            if (res.next()){//Wenn das Ergebniss der Abfrage einen nächsten Wert hat wird die if-B. erfüllt
                //Wert aus dem ResultSet in einem String speichern und zurück geben
                String pw=res.getString(1);
                return pw;
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "id/passwort nicht vorhanden";
    }
}
