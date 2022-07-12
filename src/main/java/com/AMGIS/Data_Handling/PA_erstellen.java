package com.AMGIS.Data_Handling;

import java.sql.*;
import com.AMGIS.Data_Handling.AccountErzeugen;
public class PA_erstellen {
    public Connection con=null;
    public PA_erstellen(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        }catch(ClassNotFoundException e) {
            return;
        }
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:C:\\Users\\Public\\Documents\\AmgisDatenbank; shutdown=true", "amgis", "amgis"); //url,user,pw
        }catch(SQLException e){
            e.printStackTrace();
        }
}

    //Konstruktor

    public void einfuegenPA(String anrede, String vorname,String zweitname, String nachname,String geburtsdatum, String telefon, String email,String strasse,String strNR,
                            String land,String bundesland,String plz,String jobname,String besGrad,String abteilung,String abtLeiter,String raum,String region){
        int newID=generateID();

        String sqlPA= "INSERT INTO Personalakte VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prepPA = null;

        String sqlAcc= "INSERT INTO Accounts VALUES(?,?,?)";
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
            prepPA.setString(10,strNR);
            prepPA.setString(11,land);
            prepPA.setString(12,bundesland);
            prepPA.setString(13,plz);
            prepPA.setString(14,jobname);
            prepPA.setString(15,besGrad);
            prepPA.setString(16,abteilung);
            prepPA.setString(17,abtLeiter);
            prepPA.setString(18,raum);
            prepPA.setString(19,region);
            prepPA.executeUpdate();

            //Account erzeugen
            AccountErzeugen aE=new AccountErzeugen();
            prepAcc.setInt(1,newID);
            prepAcc.setString(2,aE.kontoname_erzeugen(newID,vorname,nachname));
            prepAcc.setString(3,aE.passwort_erzeugen(newID,vorname,nachname));
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

    public void selectALL(){//ganzen Inhalt der Personalakten auslesen in der Konsole
        try {
            Statement stmt=con.createStatement();
            String sql= "SELECT * FROM Personalakte";
            ResultSet res= stmt.executeQuery(sql);
            while(res.next()){
                String id=res.getString(1);
                String anrede=res.getString(2);
                String vorname=res.getString(3);
                String zweitname=res.getString(4);
                String nachname=res.getString(5);
                String geburtstag=res.getString(6);
                String telefon=res.getString(7);
                String email=res.getString(8);
                String strasse=res.getString(9);
                String strNR=res.getString(10);
                String land=res.getString(11);
                String bundesland=res.getString(12);
                String plz=res.getString(13);
                String jobname=res.getString(14);
                String beschgrad=res.getString(15);
                String abteilung=res.getString(16);
                String abtLeiter=res.getString(17);
                String raum=res.getString(18);
                String region=res.getString(19);
                System.out.println(id+" "+anrede+" "+vorname+" "+zweitname+" "+nachname+" "+geburtstag+" "+telefon+" "+email+" "+strasse+" "+strNR+" "+land+" "+
                        bundesland+" "+plz+" "+jobname+" "+beschgrad+" "+abteilung+" "+abtLeiter+" "+raum+" "+region);
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}