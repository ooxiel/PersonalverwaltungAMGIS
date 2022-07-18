package com.AMGIS.Data_Handling;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.File;
import java.sql.*;
public class MainHR_Table {
    public Connection con=null;

    public MainHR_Table(){
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        }catch(ClassNotFoundException e) {
            return;
        }
        try {
            con = DriverManager.getConnection("jdbc:hsqldb:file:"+new File("src/main/resources/Datenbank/AMGISDatenbank")+"; shutdown=true", "amgis", "amgis"); //url,user,pw

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    public JTable defaultTable(JTable table){//ganzen Inhalt der Personalakten auslesen in der Konsole
        try {
            //TableModel definieren
            String[] tableColumnsName = {"ID","Vorname","Zweitname","Nachname","Geburtstag","2","3","3","3","3","3","3","3","3","3","3","3","3","19","20"};
            DefaultTableModel aModel = (DefaultTableModel) table.getModel();
            aModel.setColumnIdentifiers(tableColumnsName);
            //query
            String sql= "SELECT * FROM Personalakte";
            Statement stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            //
            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int colNo = rsmd.getColumnCount();
            while(rs.next()){
                Object[] objects = new Object[colNo];
                for(int i=0;i<colNo;i++){
                    objects[i]=rs.getObject(i+1);
                }
                aModel.addRow(objects);
            }
            table.setModel(aModel);
            rs.close();
            stmt.close();
            return table;
        } catch (SQLException e) {
            e.printStackTrace();return table;
        }

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
