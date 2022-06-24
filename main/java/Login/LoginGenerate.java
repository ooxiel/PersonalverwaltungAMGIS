package Login;

import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.poifs.filesystem.POIFSDocument;

import java.util.List;
import java.util.Random;

public class LoginGenerate {

    public String usernameGenerate (){


        /*
            Nachname (kommplett)+ "_"+Vorname (Anfangsbuchstabe)+Personal ID

         */
        return null;
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

    public void printUserAndPassword (){

        System.out.println("Username: "+usernameGenerate());
        System.out.println("Passwort: "+passwordGenerate());

        System.out.println("Notieren Sie den Username und das Passwort und geben Sie diesen na die entsprechende Person weiter!");


    }
}
