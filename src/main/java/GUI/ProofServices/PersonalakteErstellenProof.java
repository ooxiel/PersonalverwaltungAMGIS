package GUI.ProofServices;

import javax.swing.*;

public class PersonalakteErstellenProof {

    public boolean inputNotNull(JTextField textField){

        if(textField.getText().isEmpty()){
            return false;
        }
        return true;
    }


    public boolean onyLetterField(JTextField textField){


        return true;
    }

    /*
        Hier alle zu überprüfenden Felder Eingeben
     */

    public boolean dateField(JTextField textField){



        return true;
    }

    public boolean comboBoxField(JComboBox comboBox){

        return true;
    }
}
