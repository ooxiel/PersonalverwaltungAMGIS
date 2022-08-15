package CONTROLLER.UserInput.PullInput;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Pull {

    public ArrayList<JTextField> itemstoAdd(JTextField... args){

        return new ArrayList<>(Arrays.asList(args));
    }
}
