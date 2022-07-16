package com.AMGIS.Login;

import GUI.Apperance.MainHR;

import javax.swing.*;
import java.sql.SQLException;

public class AnmeldungAusfuehren {

    public void anmeldungAusfuehren(JFrame frame, JPanel main, JTextField usernameField, JPasswordField passwordField){
        if(usernameField.getText().isEmpty() && passwordField.getPassword().length==0){
            JOptionPane.showMessageDialog(main,"Username oder Passwort nicht aufgefuellt!");
        }else{
            LoginCheck lc = new LoginCheck();
            if(lc.validateKontoname(usernameField.getText()) && lc.validatePasswort(usernameField.getText(), String.valueOf(passwordField.getPassword()))){
                frame.dispose();
                new MainHR();
            }else{
                JOptionPane.showMessageDialog(main,"Username oder Passwort ist falsch!");
                passwordField.setText("");
                try {lc.c.close();System.out.println("closing in else");} catch (SQLException ex) {ex.printStackTrace();}
            }
        }
    }
}
