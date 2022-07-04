package com.AMGIS.Login;




public class LoginProof {

    String userName = "get.Username aus Login-Feld";
    String password = "get.Password aus Login-Feld";

    public boolean checkNull(String userName, String password){

        return false;
    }

    public boolean username (String userName){

        // Ueberpruefung von Username mit Datenbank

            /*
                For-Schleife zum Iterativen durchgehen der Zeilen,  Reihe = 1
             */
        return true;
    }

    public boolean passwordProof(String password){
        // Ueberpruefung von Password mit Datenbank

            /*
                For-Schleife zum Iterativen durchgehen der Zeilen,  Reihe = 2
             */
        return true;
    }

    public boolean personalIDProof(String userName, String CellNumber){
        // Ueberprufung ob Username + Password mit Mitarbeiter ID verbunden sind

            /*
                Überprüfung zur Festlegung des UserTyp
             */
        return true;
    }
}




