package CONTROLLER.UserInput;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PullInput {

    public ArrayList<JTextField> itemstoAdd(JTextField... args){

        return new ArrayList<>(Arrays.asList(args));
    }
}
