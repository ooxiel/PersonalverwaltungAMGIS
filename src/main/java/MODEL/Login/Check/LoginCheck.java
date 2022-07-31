package MODEL.Login.Check;

import java.sql.*;

public class LoginCheck {
    public Connection c=null;
    public LoginCheck() {
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
    public boolean validateKontoname_M(String eingabeKN){
        if(eingabeKN.equals(checkKontonameInMitarbeiter(eingabeKN))){
            return true;
        }else{
            return false;
        }
    }
    public boolean validateKontoname_HR(String eingabeKN){
        if(eingabeKN.equals(checkKontonameInHR(eingabeKN))){
            return true;
        }else{
            return false;
        }
    }

    public boolean validatePasswort_M(String eingabeKN,String eingabePW){
        if (validateKontoname_M(eingabeKN)==true && eingabePW.equals(getPasswort_M(Integer.parseInt(searchIDwithKN_M(eingabeKN))))){
            return true;
        }else{
            return false;
        }
    }
    public boolean validatePasswort_HR(String eingabeKN,String eingabePW){
        if (validateKontoname_HR(eingabeKN)==true && eingabePW.equals(getPasswort_HR(Integer.parseInt(searchIDwithKN_HR(eingabeKN))))){
            return true;
        }else{
            return false;
        }
    }
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
    //work in progress
    public boolean isRoot(int id) {
        try {
            Statement stmt = c.createStatement();
            String sql = "SELECT Root FROM HRRoot WHERE HR_ID='"+id+"'";
            ResultSet res = stmt.executeQuery(sql);
            //System.out.println(res.getBoolean(1));
            if (res.next()){
                return res.getBoolean(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
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

    private String getPasswort_HR(int id) {
        try {
            Statement stmt=c.createStatement();
            String sqlPW= "SELECT password FROM HRRoot WHERE hr_id='"+id+"'";
            ResultSet res= stmt.executeQuery(sqlPW);
            if (res.next()){
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
    private String getPasswort_M(int id) {
        try {
            Statement stmt=c.createStatement();
            String sqlPW= "SELECT password FROM Mitarbeiterlogin WHERE m_id='"+id+"'";
            ResultSet res= stmt.executeQuery(sqlPW);
            if (res.next()){
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
