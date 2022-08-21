package model.login.create;

import java.util.Random;

/** ====================================================================================================================
 * Klasse enthaelt alle Funktionen um Logindaten, bestehend aus Username und Passwort, zu generieren
 * =====================================================================================================================
 */
public class AccountErzeugen {

    /** ================================================================================================================
     * Konstruktor der Methode AccountErzeugen
     */
    public AccountErzeugen(){

    }


    /** ================================================================================================================
     * Methode generiert einen Kontonamen für einen Mitarbeiteraccount mit Hilfe der Personal-ID und dem Vor- sowie Nachnamen
     *
     * @param id            Personal-ID
     * @param vorname       Vorname
     * @param nachname      Nachname
     * @return              Username
     */
        public static String kontoname_erzeugen(int id, String vorname, String nachname){
            return nachname.substring(0,1).toLowerCase()+"_"+vorname.toLowerCase()+id+"0";
        }


        //Kontoname für HR Account generieren mithilfe der id,vorname und nachname

    /** ================================================================================================================
     * Methode generiert einen Kontonamen für einen HR-Account mit Hilfe der Personal-ID und dem Vor- sowie Nachnamen
     *
     * @param id        Personal-ID
     * @param vorname   Vorname
     * @param nachname  Nachname
     * @return          Username
     */
        public static String hr_kontoname_erzeugen(int id, String vorname, String nachname){
            return "HR_"+nachname.substring(0,1).toUpperCase()+"_"+vorname.toUpperCase()+id;
        }

    /** ================================================================================================================
     * Methode geniert ein zufaelliges Passwort mit einer Laenge von acht Zeichen.
     *
     * @return Passwort
     */

        public static String passwort_erzeugen(){
            int counter = 1;            //Zaehler
            int laenge  = 8;            //Laenge des Passworts

            char [] chars = "abcdefghijklmnopqrstuvwxyz1234567890!?#+*&-_=".toCharArray();
            StringBuilder stringbuilder = new StringBuilder();
            Random random = new Random();       //Zufallsgenerator initialisieren

            //Solange das Passwort kleiner als die laenge ist, werden ihm weiter Charakter

            while (counter < laenge){
                char c = chars [random.nextInt(chars.length)];  //Auswahl eines zufaelligen Zeichens aus dem CharArray
                stringbuilder.append(c);                        //anfuegen des neuen Zeichens
                counter = counter+1;                            //Zaehler um 1 erhöhen
            }

            return stringbuilder.toString();                    //zurueckgeben des Passworts
        }

}

