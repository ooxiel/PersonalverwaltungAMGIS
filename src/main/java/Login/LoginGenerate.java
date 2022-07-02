package Login;

import Akteure.Mitarbeiter;
import java.util.Random;



public class LoginGenerate {

    public String generateUsername (Mitarbeiter mitarbeiter){

        // UsernameGenerate gilt hier für normale User

        /*
            Aufbau Username = "Nachname"+"_"+"Anfangsbuchstabe des Vornamens"

         */

        String nachN    = mitarbeiter.getNachName().toLowerCase();
        String vorN     = mitarbeiter.getVorName();


        char [] getFirstL = vorN.toCharArray();
        vorN = String.valueOf(getFirstL[0]);


        return vorN+"_"+nachN;
    }

    public String generateHRUsername(Mitarbeiter mitarbeiter){

        // UsernameGenerate gilt hier für HR User

        /*
            Aufbau Username = "Nachname"+"_"+"Anfangsbuchstabe des Vornamens"+"_"+"Anwender-Typ"

         */

        String nachN    = mitarbeiter.getNachName().toLowerCase();
        String vorN     = mitarbeiter.getVorName();
        String type     = "adm";


        char [] getFirstL = vorN.toCharArray();
        vorN = String.valueOf(getFirstL[0]);


        return vorN+"_"+nachN+"_"+type;

    }

    public String passwordGenerate(){
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
