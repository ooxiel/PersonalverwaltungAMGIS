package Akteure;


import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class MitarbeiterTest {
    public static void main(String[] args) throws IOException {

        Mitarbeiter mitarbeiter = new Mitarbeiter("Farnz", "","Whey","weib","Farnz-Street","19a",243545, "Dutschland", "Theoretisch WI","12345", "1.2.3.4.5.6", "Abteilung","Bereich","Position","Vorgesetzter","Leiter");

        ObjectOutputStream oos = null;
        FileOutputStream fos = null;

        FileWriter writer = new FileWriter("test.ser");

        try{
            fos = new FileOutputStream("C:\\Users\\Lennard\\IdeaProjects\\Grundlagen der Programmierung\\test.ser");
            oos = new ObjectOutputStream(fos);

        }catch (IOException e){

            e.printStackTrace();
        }







    }
}
