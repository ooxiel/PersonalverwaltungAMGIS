package controller.userinput.convertinput;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ConvertUserInputToArrayList {
    //Erstellt ArrayList vom Typ JTextField mit variabler Anzahl an Parametern
    public ArrayList<JTextField> itemstoAdd(JTextField... args){

        return new ArrayList<>(Arrays.asList(args));
    }
}
