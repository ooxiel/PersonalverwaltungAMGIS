package com.AMGIS.Data_Handling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    DBConnect(){
    }
    public Connection getCon(){
        try {Class.forName("org.hsqldb.jdbcDriver");}catch(ClassNotFoundException e) {return null;
        }
        try {
            Connection con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis"); return con;}catch(
                SQLException e){e.printStackTrace();}
        return null;
    }
    public void closeCon(Connection con){
        try {con.close();}
        catch (SQLException ex) {ex.printStackTrace();}
    }
}
