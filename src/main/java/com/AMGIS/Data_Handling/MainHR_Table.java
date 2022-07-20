package com.AMGIS.Data_Handling;

import com.AMGIS.TableModels.LogindatenTableModel;
import com.AMGIS.TableModels.PersonalaktenTableModel;
import com.AMGIS.Akteure.Logindaten;
import com.AMGIS.Akteure.Personalakten;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainHR_Table {
    public Connection con=null;

    public MainHR_Table(){
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return;}
        try {con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");}catch(SQLException e){e.printStackTrace();}
    }

    public JTable simpleFilteredTablePersonalakte(JTable table,String w1, String w2){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            String sql= "SELECT  * FROM Personalakte WHERE "+w1+"='"+w2+"'";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            List<Personalakten> personalakten = new ArrayList<>();

            while (rs.next()){
                int id                      =rs.getInt(1);
                String anrede               =String.valueOf(rs.getString(2));
                String vorname              =String.valueOf(rs.getString(3));
                String zweitname            =String.valueOf(rs.getString(4));
                String nachname             =String.valueOf(rs.getString(5));
                String geburtsdatum         =String.valueOf(rs.getString(6));
                String telefon              =String.valueOf(rs.getString(7));
                String email                =String.valueOf(rs.getString(8));
                String strasse              =String.valueOf(rs.getString(9));
                String strassenNR           =String.valueOf(rs.getString(10));
                String strassenBuchstabe    =String.valueOf(rs.getString(11));
                String land                 =String.valueOf(rs.getString(12));
                String bundesland           =String.valueOf(rs.getString(13));
                String plz                  =String.valueOf(rs.getString(14));
                String jobname              =String.valueOf(rs.getString(15));
                String besGrad              =String.valueOf(rs.getString(16));
                String abteilung            =String.valueOf(rs.getString(17));
                String abtLeiter            =String.valueOf(rs.getString(18));
                String raum                 =String.valueOf(rs.getString(19));
                String standort             =String.valueOf(rs.getString(20));
                String erstelltDatum        =String.valueOf(rs.getString(21));
                String letzteAenderung      =String.valueOf(rs.getString(22));
                Personalakten pa = new Personalakten(id,anrede,vorname,zweitname,nachname,geburtsdatum,telefon,email,strasse,strassenNR,strassenBuchstabe,land,bundesland,plz,jobname,besGrad,abteilung,abtLeiter,raum,standort,erstelltDatum,letzteAenderung);
                personalakten.add(pa);
            }
            PersonalaktenTableModel patm = new PersonalaktenTableModel(personalakten);
            table.setModel(patm);
            rs.close();
            stmt.close();
            try{con.close();}catch(SQLException ex) {ex.printStackTrace();}
            return table;
        }catch (Exception e){e.printStackTrace();}
        return table;
    }


    public JTable defaultTableAccounts(JTable table){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            String sql= "SELECT  ID, Kontoname, Passwort, HRMitarbeiter FROM Accounts";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            List<Logindaten> logindaten = new ArrayList<>();

            while (rs.next()){
                int id = rs.getInt(1);
                String kontoname = String.valueOf(rs.getString(2));
                String passwort = String.valueOf(rs.getString(3));
                boolean hrmitarbeiter = rs.getBoolean(4);
                Logindaten ld = new Logindaten(id,kontoname,passwort,hrmitarbeiter);
                logindaten.add(ld);
            }
            LogindatenTableModel ldtm = new LogindatenTableModel(logindaten);
            table.setModel(ldtm);
            rs.close();
            stmt.close();
            try{con.close();}catch(SQLException ex) {ex.printStackTrace();}
            return table;
        }catch (Exception e){e.printStackTrace();}
    return table;
    }

    public JTable defaultTablePersonalakte(JTable table){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            String sql= "SELECT  * FROM Personalakte";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            List<Personalakten> personalakten = new ArrayList<>();

            while (rs.next()){
                int id                      =rs.getInt(1);
                String anrede               =String.valueOf(rs.getString(2));
                String vorname              =String.valueOf(rs.getString(3));
                String zweitname            =String.valueOf(rs.getString(4));
                String nachname             =String.valueOf(rs.getString(5));
                String geburtsdatum         =String.valueOf(rs.getString(6));
                String telefon              =String.valueOf(rs.getString(7));
                String email                =String.valueOf(rs.getString(8));
                String strasse              =String.valueOf(rs.getString(9));
                String strassenNR           =String.valueOf(rs.getString(10));
                String strassenBuchstabe    =String.valueOf(rs.getString(11));
                String land                 =String.valueOf(rs.getString(12));
                String bundesland           =String.valueOf(rs.getString(13));
                String plz                  =String.valueOf(rs.getString(14));
                String jobname              =String.valueOf(rs.getString(15));
                String besGrad              =String.valueOf(rs.getString(16));
                String abteilung            =String.valueOf(rs.getString(17));
                String abtLeiter            =String.valueOf(rs.getString(18));
                String raum                 =String.valueOf(rs.getString(19));
                String standort             =String.valueOf(rs.getString(20));
                String erstelltDatum        =String.valueOf(rs.getString(21));
                String letzteAenderung      =String.valueOf(rs.getString(22));
                Personalakten pa = new Personalakten(id,anrede,vorname,zweitname,nachname,geburtsdatum,telefon,email,strasse,strassenNR,strassenBuchstabe,land,bundesland,plz,jobname,besGrad,abteilung,abtLeiter,raum,standort,erstelltDatum,letzteAenderung);
                personalakten.add(pa);
            }
            PersonalaktenTableModel patm = new PersonalaktenTableModel(personalakten);
            table.setModel(patm);
            rs.close();
            stmt.close();
            try{con.close();}catch(SQLException ex) {ex.printStackTrace();}
            return table;
        }catch (Exception e){e.printStackTrace();}
        return table;
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
                String hausNR=res.getString(10);
                String hausB=res.getString(11);
                String land=res.getString(12);
                String bundesland=res.getString(13);
                String plz=res.getString(14);
                String jobname=res.getString(15);
                String beschgrad=res.getString(16);
                String abteilung=res.getString(17);
                String abtLeiter=res.getString(18);
                String raum=res.getString(19);
                String standort=res.getString(20);
                System.out.println(id+" "+anrede+" "+vorname+" "+zweitname+" "+nachname+" "+geburtstag+" "+telefon+" "+email+" "+strasse+" "+hausNR+" "+hausB+" "+land+" "+
                        bundesland+" "+plz+" "+jobname+" "+beschgrad+" "+abteilung+" "+abtLeiter+" "+raum+" "+standort);
            }
            res.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
