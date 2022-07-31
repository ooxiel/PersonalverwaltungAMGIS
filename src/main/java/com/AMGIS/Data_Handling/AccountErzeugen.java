package com.AMGIS.Data_Handling;

import java.util.Random;

public class AccountErzeugen {

    protected AccountErzeugen(){

    }

        protected static String kontoname_erzeugen(int id, String vorname, String nachname){
            return nachname.substring(0,1).toLowerCase()+"_"+vorname.toLowerCase()+id+"0";
        }


        //unique über Rowcount oder so
        protected static String HR_kontoname_erzeugen(int id,String vorname, String nachname){
            return "HR_"+nachname.substring(0,1).toUpperCase()+"_"+vorname.toUpperCase()+id;
        }

        protected static String passwort_erzeugen(){
            int counter = 1;
            int laenge  = 8;

            char [] chars = "abcdefghijklmnopqrstuvwxyz1234567890!?#+*&-_=".toCharArray();
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

