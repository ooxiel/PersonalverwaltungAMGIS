package model.login.check;

import java.sql.*;

/** ====================================================================================================================
 * Klasse enthaelt alle Datenbankabfragen, welche fuer die Validierung beim Login notwendig sind
 * =====================================================================================================================
 */
public class LoginCheck {
    public Connection c=null;               //Globale Variable für die Verbindung zur Datenbank

    /** ================================================================================================================
     *  Konstruktor der Klasse LoginCheck
     */
    public LoginCheck() {
        /*
            Verbindung zur Datenbank wird hergestellt
         */
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

    /** ================================================================================================================
     * Methode ueberprueft, ob die Eingabe mit einem Datenbankeintrag in der Mitarbeiterlogintablle uebereinstimmt
     *
     * @param eingabeKN     Username
     * @return              true -> Kontoname ist enthaelten; false -> Kontoname ist nicht enthaelten
     */
    public boolean validateKontoname_M(String eingabeKN){
        if(eingabeKN.equals(checkKontonameInMitarbeiter(eingabeKN))){return true;}
        else{return false;}
    }

    /** ================================================================================================================
     * Methode ueberprueft ruft Methode auf, um zu pruefen, ob die Eingabe mit einem Datenbankeintrag in der HRLogintablle uebereinstimmt
     *
     * @param eingabeKN     Username
     * @return              true -> Kontoname ist enthaelten; false -> Kontoname ist nicht enthaelten
     */
    public boolean validateKontoname_HR(String eingabeKN){
        if(eingabeKN.equals(checkKontonameInHR(eingabeKN))){return true;}
        else{return false;}
    }


    /** ================================================================================================================
     * Methode ueberprueft, ob Mitarbeiter-Kontoname und Passwort in der Datenbank vorhanden und richtig sind
     *
     * @param eingabeKN     Username
     * @param eingabePW     Password
     * @return              true -> Kontoname kombiniert mit Passwort enthaelten; false -> Kontoname kombiniert mit Passwort nicht enthaelten
     */
    public boolean validatePasswort_M(String eingabeKN,String eingabePW){
        if (validateKontoname_M(eingabeKN)==true && eingabePW.equals(getPasswort_M(Integer.parseInt(searchIDwithKN_M(eingabeKN))))){
            return true;
        }else{return false;}
    }

    /** ================================================================================================================
     * Methode ueberprueft, ob HR-Kontoname und Passwort in der Datenbank vorhanden und richtig sind
     *
     * @param eingabeKN     Username
     * @param eingabePW     Password
     * @return              true -> Kontoname kombiniert mit Passwort enthaelten; false -> Kontoname kombiniert mit Passwort nicht enthaelten
     */
    public boolean validatePasswort_HR(String eingabeKN,String eingabePW){
        if (validateKontoname_HR(eingabeKN)==true && eingabePW.equals(getPasswort_HR(Integer.parseInt(searchIDwithKN_HR(eingabeKN))))){
            return true;
        }else{return false;}
    }

    /** ================================================================================================================
     * Methode ueberprueft ob die Eingabe mit einem Datenbankeintrag in der HRLogintablle uebereinstimmt
     * @param KN        Kontoname
     * @return          true -> Kontoname ist vorhanden; false -> Kontoname ist nicht vorhanden
     */
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
    /** ================================================================================================================
     * Methode ueberprueft ob die Eingabe mit einem Datenbankeintrag in der MitarbeiterLogintablle uebereinstimmt
     * @param KN        Kontoname
     * @return          true -> Kontoname ist vorhanden; false -> Kontoname ist nicht vorhanden
     */
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

    /** ================================================================================================================
     * Methode ueberpruft, ob Root-Berechtigungen vorhanden sind
     *
     * @param id        Personal-ID
     * @return          true -> Root-Berechtigung; false -> keine Root-Berechtigung
     */
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

    /** ================================================================================================================
     * Methoden macht Datenbankabfrage, um eine ID mit Hilfe des Kontonamen zu finden
     *
     * @param kontoname     Kontoname
     * @return              Personal-ID
     */
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

    /** ================================================================================================================
     *  Methode macht Datenbankabfrage, um einen Kontonamen mit Hilfe einer ID zu finden
     *
      * @param kontoname       Kontoname
     * @return                 Personal-ID
     */
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

    /** ================================================================================================================
     * Methode macht eine Datenbankabfrage, um ein Passwort mit Hilfe des PK fuer HR-Daten zu suchen
     *
     * @param id    Personal-ID
     * @return      Passwort
     */

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

    /** ================================================================================================================
     *  Methode macht eine Datenbankabfrage, um ein Passwort mit Hilfe des PK fuer Mitarbeiterdaten zu suchen
     *
     * @param id   Personal-ID
     * @return      Passwort
     */
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
