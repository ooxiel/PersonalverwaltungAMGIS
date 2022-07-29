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
        String sql_Mstamm= "UPDATE Mitarbeiterstamm SET anrede='"+anrede+"',vorname='"+vorname+"',zweitname='"+zweitname+"',nachname='"+nachname+"',geburtstag='"+geburtsdatum+"',telefon='"+telefon+"',email='"+email+"',Aenderung_Datum ='"+letzteAenderung+"' WHERE person_id="+id;
        String sql_adresse="UPDATE Adressinfo SET strasse='" +strasse+"', strassen_nummer='"+hausNR+"',Strassen_buchstabe ='"+hausB +"',land='"+land+"',bundesland='"+bundesland+"',plz='"+plz+"' WHERE adress_id="+id;
        String sql_jobinfo="UPDATE JOBINFO SET jobname='"+jobname+"',Beschaeftigungsgrad ='"+besGrad +"', Abteilung='"+abteilung +"',Abteilungsleiter ='"+abtLeiter+"',Raum ='"+raum +"',Standort ='"+standort+"' WHERE job_ID="+id;
        try {
            Statement stmt = con.createStatement();
            stmt.executeQuery(sql_Mstamm);
            stmt.executeQuery(sql_adresse);
            stmt.executeQuery(sql_jobinfo);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}