package com.AMGIS.Data_Handling;

import java.util.Random;

public class AccountErzeugen {

    protected AccountErzeugen(){

    }

        protected static String kontoname_erzeugen(int id, String vorname, String nachname){
            return nachname.substring(0,1).toLowerCase()+"_"+vorname.toLowerCase()+id+"0";
        }

        protected static String passwort_erzeugen(int id, String vorname, String nachname){
            int counter = 1;
            int laenge  = 8;

            char [] chars = "abcdefghijklmnopqrstuvwxyz1234567890!?#+*".toCharArray();
            StringBuilder stringbuilder = new StringBuilder();
            Random random = new Random();

            while (counter < laenge){

                char c = chars [random.nextInt(chars.length)];
                stringbuilder.append(c);
                counter = counter+1;
            }

            return stringbuilder.toString();
        }

}

