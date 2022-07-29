package com.AMGIS.Data_Handling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.stream.Stream;

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
                            String hausB,String land,String bundesland,String plz,String jobname,String besGrad,String abteilung,String abtLeiter,String raum,String standort){
        int newID=generateID();

        String sql_Mstamm = "INSERT INTO Mitarbeiterstamm VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement prep_Mstamm=null;

        String sql_Adr = "INSERT INTO Adressinfo VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prep_Adr=null;

        String sql_MLogin = "INSERT INTO Mitarbeiterlogin VALUES(?,?,?)";
        PreparedStatement prep_MLogin=null;

        String sql_AStamm = "INSERT INTO Aktenstamm VALUES(?,?)";
        PreparedStatement prep_AStamm=null;

        String sql_JobInfo = "INSERT INTO Jobinfo VALUES(?,?,?,?,?,?,?)";
        PreparedStatement prep_JobInfo=null;

        try {
            prep_Mstamm = con.prepareStatement(sql_Mstamm);
            prep_Adr = con.prepareStatement(sql_Adr);
            prep_MLogin=con.prepareStatement(sql_MLogin);
            prep_AStamm=con.prepareStatement(sql_AStamm);
            prep_JobInfo=con.prepareStatement(sql_JobInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            //Mitarbeiterlogin
            AccountErzeugen aE=new AccountErzeugen();
            prep_MLogin.setInt(1,newID);
            prep_MLogin.setString(2,aE.kontoname_erzeugen(newID,vorname,nachname));
            prep_MLogin.setString(3,aE.passwort_erzeugen());
            prep_MLogin.executeUpdate();
            //AdressInfo
            prep_Adr.setInt(1,newID);
            prep_Adr.setString(2,strasse);
            prep_Adr.setString(3,hausNR);
            prep_Adr.setString(4,hausB);
            prep_Adr.setString(5,land);
            prep_Adr.setString(6,bundesland);
            prep_Adr.setString(7,plz);
            prep_Adr.executeUpdate();

            //Jobinfo
            prep_JobInfo.setInt(1,newID);
            prep_JobInfo.setString(2,jobname);
            prep_JobInfo.setString(3,besGrad);
            prep_JobInfo.setString(4,abteilung);
            prep_JobInfo.setString(5,abtLeiter);
            prep_JobInfo.setString(6,raum);
            prep_JobInfo.setString(7,standort);
            prep_JobInfo.executeUpdate();

            //Aktenstamm
            prep_AStamm.setInt(1,newID);
            prep_AStamm.setInt(2,newID);
            prep_AStamm.executeUpdate();

            //Mitarbeiterstamm
            prep_Mstamm.setInt(1,newID);
            prep_Mstamm.setString(2,anrede);
            prep_Mstamm.setString(3,vorname);
            prep_Mstamm.setString(4,zweitname);
            prep_Mstamm.setString(5,nachname);
            prep_Mstamm.setString(6,geburtsdatum);
            prep_Mstamm.setString(7,telefon);
            prep_Mstamm.setString(8,email);
            prep_Mstamm.setString(9,String.valueOf(LocalDateTime.now()));
            prep_Mstamm.setString(10,String.valueOf(LocalDateTime.now()));
            prep_Mstamm.setInt(11,newID);
            prep_Mstamm.setInt(12,newID);
            prep_Mstamm.setInt(13,newID);
            prep_Mstamm.setInt(14,-1);
            prep_Mstamm.executeUpdate();


            try {
                System.out.println(isEmpty(Path.of("src/main/resources/AktenFiles/Pending")));
                if (!isEmpty(Path.of("src/main/resources/AktenFiles/Pending"))){
                    new File("src/main/resources/AktenFiles/"+newID).mkdirs();
                    Path targetPath = Paths.get("src/main/resources/AktenFiles/"+newID);
                    Path sourcePath = Paths.get("src/main/resources/AktenFiles/Pending");
                    Files.walk(sourcePath).forEach(path -> showFile(path.toFile(),targetPath,newID));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                prep_Mstamm.close();
                prep_Adr.close();
                prep_MLogin.close();
                prep_AStamm.close();
                prep_JobInfo.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void showFile(File file, Path targetPath,int newID) {
        if (file.isDirectory()) {
            System.out.println("idk first if ??");
        } else {
            System.out.println("File: " + file.getAbsolutePath());
            try {
                Files.move(Path.of(file.getAbsolutePath()), targetPath.resolve(file.getName()));

                String sql_insertFile = "INSERT INTO AKTENKENNZEICHEN ( 'POS_NR', 'DATEIPFAD', 'AKTEN_ID' ) VALUES ("+nextPOS_NR()+" ,"+targetPath.resolve(file.getName())+","+newID+")";
                Statement stmt = con.createStatement();
                stmt.executeQuery(sql_insertFile);
                stmt.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public int nextPOS_NR(){
        int nextid=1;
        try{
            Statement stmt=con.createStatement();
            String sql= "SELECT * FROM Aktenkennzeichen";
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

    public int generateID(){
        int nextid=0;
        try{
            Statement stmt=con.createStatement();
            String sql= "SELECT * FROM Mitarbeiterlogin";
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
    //chekc if the directory is empty
    public boolean isEmpty(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            try (Stream<Path> entries = Files.list(path)) {
                return !entries.findFirst().isPresent();
            }
        }
        return false;
    }


}