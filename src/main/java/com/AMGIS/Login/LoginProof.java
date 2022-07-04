package com.AMGIS.Login;


import java.lang.reflect.Array;

public class LoginProof {

    private String username;
    private char[] password;


    public boolean checkNull(String username, char[] password){

            if (username == null || username.length() == 0){
                return false;
            }

            if(password == null || password.length == 0){
                return false;
            }

        return true;
    }

    public boolean username (String username){

        // Ueberpruefung von Username mit Datenbank

            /*
                For-Schleife zum Iterativen durchgehen der Zeilen,  Reihe = 1
             */
        return true;
    }

    public boolean passwordProof(char[] password){
        // Ueberpruefung von Password mit Datenbank

            /*
                For-Schleife zum Iterativen durchgehen der Zeilen,  Reihe = 2
             */
        return true;
    }

    public boolean personalIDProof(String username, String CellNumber){
        // Ueberprufung ob Username + Password mit Mitarbeiter ID verbunden sind

            /*
                Überprüfung zur Festlegung des UserTyp
             */
        return true;
    }
}




