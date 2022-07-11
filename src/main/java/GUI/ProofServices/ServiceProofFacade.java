package GUI.ProofServices;

import javax.swing.*;

public class ServiceProofFacade {

    PersonalakteErstellenProof proof = new PersonalakteErstellenProof();

    public boolean proofLetter(JTextField textField){

        if(proof.inputNotNull(textField) && proof.onyLetterField(textField)){

            return true;
        }

        return false;
    }

    public boolean proofNumber(JTextField textfield){

        if(proof.inputNotNull(textfield) && proof.onyLetterField(textfield)){
            return true;
        }

        return false;
    }

    public boolean proofdateField(JTextField textField){

        if(proof.inputNotNull(textField) && proof.dateField(textField)){

            return true;
        }

        return false;
    }

    public boolean proofComboBox (JComboBox comboBox){



        return false;
    }




}
