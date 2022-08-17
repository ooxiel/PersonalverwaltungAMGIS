package view.Personalakte.Login.CreateNew;

import java.util.Random;

public class AccountErzeugen {

    public AccountErzeugen(){

    }
        //Kontoname für Mitarbeiteraccount generieren mithilfe der id,vorname und nachname
        public static String kontoname_erzeugen(int id, String vorname, String nachname){
            return nachname.substring(0,1).toLowerCase()+"_"+vorname.toLowerCase()+id+"0";
        }


        //Kontoname für HR Account generieren mithilfe der id,vorname und nachname
        public static String hr_kontoname_erzeugen(int id, String vorname, String nachname){
            return "HR_"+nachname.substring(0,1).toUpperCase()+"_"+vorname.toUpperCase()+id;
        }

        public static String passwort_erzeugen(){
            int counter = 1;//Zähler
            int laenge  = 8;//Länge des Passwortes

            char [] chars = "abcdefghijklmnopqrstuvwxyz1234567890!?#+*&-_=".toCharArray();
            StringBuilder stringbuilder = new StringBuilder();
            Random random = new Random();//Zufallsgenerator initialisieren

            //Solange das Passwort kleiner als die laenge ist, werden ihm weiter Charakte
            while (counter < laenge){
                char c = chars [random.nextInt(chars.length)];//Auswahl eines zufälligen Zeichens aus dem CharArray
                stringbuilder.append(c);//anfügen des neuen Zeichens
                counter = counter+1;//Zähler um 1 erhöhen
            }

            return stringbuilder.toString();//zurückgeben des Passworts
        }

}

