package com.AMGIS.Data_Handling;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;

public class PA_erstellen {
    public Connection con=null;
    public PA_erstellen(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        }catch(ClassNotFoundException e) {
            return;
        }
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis"); //url,user,pw

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Konstruktor

    public void einfuegenPA(String anrede, String vorname,String zweitname, String nachname,String geburtsdatum, String telefon, String email,String strasse,String hausNR,
                            String hausB,String land,String bundesland,String plz,String jobname,String besGrad,String abteilung,String abtLeiter,String raum,String standort,boolean hrMitarbeiter){
        int newID=generateID();

        String sqlPA= "INSERT INTO Personalakte VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepPA = null;

        String sqlAcc= "INSERT INTO Accounts VALUES(?,?,?,?)";
        PreparedStatement prepAcc = null;

        try {
            prepPA = con.prepareStatement(sqlPA);
            prepAcc = con.prepareStatement(sqlAcc);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            prepPA.setInt(1,newID);
            prepPA.setString(2,anrede);
            prepPA.setString(3,vorname);
            prepPA.setString(4,zweitname);
            prepPA.setString(5,nachname);
            prepPA.setString(6,geburtsdatum);
            prepPA.setString(7,telefon);
            prepPA.setString(8,email);
            prepPA.setString(9,strasse);
            prepPA.setString(10,hausNR);
            prepPA.setString(11,hausB);
            prepPA.setString(12,land);
            prepPA.setString(13,bundesland);
            prepPA.setString(14,plz);
            prepPA.setString(15,jobname);
            prepPA.setString(16,besGrad);
            prepPA.setString(17,abteilung);
            prepPA.setString(18,abtLeiter);
            prepPA.setString(19,raum);
            prepPA.setString(20,standort);
            prepPA.setString(21,String.valueOf(LocalDateTime.now()));
            prepPA.setString(22,String.valueOf(LocalDateTime.now()));
            prepPA.executeUpdate();

            //Account erzeugen
            AccountErzeugen aE=new AccountErzeugen();
            prepAcc.setInt(1,newID);
            prepAcc.setString(2,aE.kontoname_erzeugen(newID,vorname,nachname));
            prepAcc.setString(3,aE.passwort_erzeugen(newID,vorname,nachname));
            prepAcc.setBoolean(4,hrMitarbeiter);
            prepAcc.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                prepPA.close();
                prepAcc.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int generateID(){
        int nextid=0;
        try{
            Statement stmt=con.createStatement();
            String sql= "SELECT * FROM Personalakte";
            ResultSet r= stmt.executeQuery(sql);
            while(r.next()){
                if(r.getInt(1)>nextid)
                    nextid=r.getInt(1);
            }
            nextid++;
            r.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return nextid;
    }
}