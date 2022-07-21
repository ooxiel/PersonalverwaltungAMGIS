package com.AMGIS.Data_Handling;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;

public class PA_bearbeiten {
    public Connection con=null;
    public PA_bearbeiten(){
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

    public void speichernPA(int id, String anrede, String vorname,String zweitname, String nachname,String geburtsdatum, String telefon, String email,String strasse,String hausNR,
                            String hausB,String land,String bundesland,String plz,String jobname,String besGrad,String abteilung,String abtLeiter,String raum,String standort,String erstelltDatum){

        String letzteAenderung=String.valueOf(LocalDateTime.now());
        String sqlPA= "UPDATE Personalakte SET anrede='"+anrede+"',vorname='"+vorname+"',zweitname='"+zweitname+"',nachname='"+nachname+"',geburtstag='"+geburtsdatum+"',telefon='"+telefon+"',email='"+email+"', strasse='"+strasse+"', strassennr='"+hausNR+"',Straßenbuchstabe ='"+hausB +"',land='"+land+"',bundesland='"+bundesland+"',plz='"+plz+"',jobname='"+jobname+"',Beschäftigungsgrad ='"+besGrad +"', Abteilung='"+abteilung +"',Abteilungsleiter ='"+abtLeiter +"',Raum ='"+raum +"',Standort ='"+standort +"',erstelltDatum ='"+erstelltDatum +"',letzteAenderung ='"+letzteAenderung+"' WHERE id="+id;
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sqlPA);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}