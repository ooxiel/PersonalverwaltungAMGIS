package com.AMGIS.Data_Handling;

import java.sql.*;

public class PA_erstellen {
    Connection con=null;
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

        PA_erstellen m = new PA_erstellen();

        String sql= "INSERT INTO Personalakte VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prep = null;
        try {
            prep = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            prep.setInt(1,generateID());
            prep.setString(2,anrede);
            prep.setString(3,vorname);
            prep.setString(4,zweitname);
            prep.setString(5,nachname);
            prep.setString(6,geburtsdatum);
            prep.setString(7,telefon);
            prep.setString(8,email);
            prep.setString(9,strasse);
            prep.setString(10,strNR);
            prep.setString(11,land);
            prep.setString(12,bundesland);
            prep.setString(13,plz);
            prep.setString(14,jobname);
            prep.setString(15,besGrad);
            prep.setString(16,abteilung);
            prep.setString(17,abtLeiter);
            prep.setString(18,raum);
            prep.setString(19,region);
            prep.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            m.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
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