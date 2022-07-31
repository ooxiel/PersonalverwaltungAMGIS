package MODEL.Update;

import VIEW.TableModel.Login.LogindatenTableModel;
import VIEW.TableModel.Personalakten.PersonalaktenTableModel;
import VIEW.TableModel.Login.Logindaten;
import VIEW.TableModel.Personalakten.Personalakten;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainHR_Table {
    public Connection con=null;

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
                String jobname              =String.valueOf(rs.getString(6));
                String abteilung            =String.valueOf(rs.getString(7));
                String standort             =String.valueOf(rs.getString(8));
                Personalakten pa = new Personalakten(id,anrede,vorname,zweitname,nachname,jobname,abteilung,standort);
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
    public JTable filterTable(JTable table,String anrede, String vorname, String nachname,String jobname, String abteilung, String standort){
        String sql= "SELECT  ms.person_id, ms.anrede, ms.vorname, ms.zweitname,ms.nachname,ji.jobname,  ji.abteilung,ji.standort FROM mitarbeiterstamm ms, adressinfo ai, jobinfo ji  WHERE  ms.anrede LIKE '%"+anrede+"%' AND ms.vorname LIKE '%"+vorname+"%' AND ms.nachname LIKE '%"+nachname+"%' AND ji.jobname LIKE '%"+jobname+"%' AND ji.abteilung LIKE'%"+abteilung+"%' AND ji.standort LIKE '%"+standort+"%' AND ms.person_id=ai.Adress_ID AND person_id=ji.job_ID";
        table.setModel(resultSQL_PA(sql));
        return table;
    }

    public JTable defaultTableAccounts(JTable table){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            con=getCon();
            String sql= "SELECT  * FROM HRroot";
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

    public JTable defaultTableAccounts_HR(JTable table){//ganzen Inhalt der Accounts auslesen und in einer Table darstellen
        try{
            con=getCon();
            String sql= "SELECT * FROM HRroot";
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


    public Connection getCon(){
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return null;}
        try {Connection con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");return con;}catch(SQLException e){e.printStackTrace();}
        return null;
    }
}
