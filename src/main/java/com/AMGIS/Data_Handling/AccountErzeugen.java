package com.AMGIS.Data_Handling;

public class AccountErzeugen {
    protected AccountErzeugen() {

    }
    protected static String kontoname_erzeugen(int id, String vorname, String nachname){
        String kontoname=nachname.substring(0,1).toLowerCase()+"_"+vorname.toLowerCase()+id+"0";
        return kontoname;
    }
    protected static String passwort_erzeugen(int id, String vorname, String nachname){
        String[] x = {"!", "?", "/","=",">","-","<","$","&"};
        String passwort=nachname.substring(1,3)+x[(int)(Math.random() * 9)]+"_"+id*420/69+x[(int)(Math.random() * 9)]+vorname.substring(0,1).toUpperCase()+x[(int)(Math.random() * 9)];
        return passwort;
    }

}
