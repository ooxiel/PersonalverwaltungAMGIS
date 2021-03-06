package com.AMGIS.Login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.desktop.SystemSleepEvent;
import java.io.File;
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
    public boolean isHR_User(String kontoname){
        int id=Integer.parseInt(searchIDwithKN(kontoname));
        try {
            Statement stmt=c.createStatement();
            String sql= "SELECT hrmitarbeiter FROM Accounts WHERE id="+id;
            ResultSet res= stmt.executeQuery(sql);
            if (res.next() && res.getBoolean(1)==true){
                return true;
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validateKontoname(String eingabeKN){
        if(eingabeKN.equals(doesKontonameExist(eingabeKN))){
            return true;
        }else{
            return false;
        }
    }

    public boolean validatePasswort(String eingabeKN,String eingabePW){
        if (validateKontoname(eingabeKN)==true && eingabePW.equals(getPasswort(Integer.parseInt(searchIDwithKN(eingabeKN))))){
            return true;
        }else{
            return false;
        }
    }
    //working  | suche nach dem Kontonamen mit dem Kontonamen
    private String doesKontonameExist(String KN) {
        try {
            Statement stmt=c.createStatement();
            String sqlPW= "SELECT kontoname FROM Accounts WHERE kontoname='"+KN+"'";
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
    //working  | ID des Kontonamen mit dem Kontonamen suchen
    public String searchIDwithKN(String kontoname){
        try {
            Statement stmt=c.createStatement();
            String sqlID= "SELECT id FROM Accounts WHERE kontoname='"+kontoname+"'";
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
    //working  | Kontoname mit der ID suchen
    private String getKontonameWithID(int id) {
        try {
            Statement stmt=c.createStatement();
            String sqlPW= "SELECT kontoname FROM Accounts WHERE id='"+id+"'";
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
        return "id/kontoname nicht vorhanden";
    }
    //working  | Passwort fuer die zugeh??rige ID
    private String getPasswort(int id) {
        try {
            Statement stmt=c.createStatement();
            String sqlPW= "SELECT passwort FROM Accounts WHERE id='"+id+"'";
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
