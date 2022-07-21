package com.AMGIS.Data_Handling;

import com.AMGIS.TableModels.LogindatenTableModel;
import com.AMGIS.TableModels.PersonalaktenTableModel;
import com.AMGIS.Akteure.Logindaten;
import com.AMGIS.Akteure.Personalakten;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainHR_Table {
    public Connection con=null;

    public MainHR_Table(){
        }

    private AbstractTableModel resultSQL_PA(String sql){
        try{
            con=getCon();
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
            rs.close();
            stmt.close();
            try{con.close();}catch(SQLException ex) {ex.printStackTrace();}
            return patm;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public JTable simpleFilteredTablePersonalakte(JTable table,String w1, String w2){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        String sql= "SELECT  * FROM Personalakte WHERE "+w1+" ='"+w2+"'";//euqals
        table.setModel(resultSQL_PA(sql));
        return table;
    }
    public JTable simpleFilteredTablePersonalakteContains(JTable table,String w1, String w2){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        String sql= "SELECT  * FROM Personalakte WHERE "+w1+" LIKE '%"+w2+"%'";//contains
        table.setModel(resultSQL_PA(sql));
        return table;
    }

    public JTable doubleFilteredTablePersonalakte(JTable table,String w1, String w2,String v1,String v2 ){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        String sql= "SELECT  * FROM Personalakte WHERE "+w1+"='"+w2+"' AND "+v1+"='"+v2+"'";
        table.setModel(resultSQL_PA(sql));
        return table;
    }
    public JTable doubleFilteredTablePersonalakteContains(JTable table,String w1, String w2,String v1,String v2 ){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        String sql= "SELECT  * FROM Personalakte WHERE "+w1+" LIKE'%"+w2+"%' AND "+v1+" LIKE '%"+v2+"%'";
        table.setModel(resultSQL_PA(sql));
        return table;
    }
    public JTable defaultTableAccounts(JTable table){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            con=getCon();
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
        String sql= "SELECT  * FROM Personalakte";
        table.setModel(resultSQL_PA(sql));
        return table;
    }
    public JTable threeFilteredTablePersonalakte(JTable table, String one, String oneV, String two, String twoV, String three, String threeV) {
        String sql= "SELECT  * FROM Personalakte WHERE "+one+"='"+oneV+"' AND "+two+"='"+twoV+"' AND "+three+"='"+threeV+"'";
        table.setModel(resultSQL_PA(sql));
        return table;
    }
    public JTable fourFilteredTablePersonalakte(JTable table, String one, String oneV, String two, String twoV, String three, String threeV, String four, String fourV) {
        String sql= "SELECT  * FROM Personalakte WHERE "+one+"='"+oneV+"' AND "+two+"='"+twoV+"' AND "+three+"='"+threeV+"' AND "+four+"='"+fourV+"'";
        table.setModel(resultSQL_PA(sql));
        return table;
    }
    public JTable allFilteredTablePersonalakte(JTable table, String id, String idV, String anrede, String anredeV, String vorname, String vornameV, String zweitname, String zweitnameV, String nachname, String nachnameV) {
        String sql= "SELECT  * FROM Personalakte WHERE "+id+"='"+idV+"' AND "+anrede+"='"+anredeV+"' AND "+vorname+"='"+vornameV+"' AND "+zweitname+"='"+zweitnameV+"' AND "+nachname+"='"+nachnameV+"'";
        table.setModel(resultSQL_PA(sql));
        return table;
    }

    public Connection getCon(){
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return null;}
        try {Connection con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");return con;}catch(SQLException e){e.printStackTrace();}
        return null;
    }
    public void closeCon(Connection con){
        try {con.close();}
        catch (SQLException ex) {ex.printStackTrace();}
    }
}
