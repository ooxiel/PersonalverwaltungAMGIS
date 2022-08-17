package controller.functions;

import model.personalakten.PersonalakteEditModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRController {

    public void create(JPanel main, JButton button, JLabel pidField, JTextField vornameField, JTextField zweitNameField, JTextField nameField){

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane confirmCreate = new JOptionPane();

                int res = confirmCreate.showConfirmDialog(main, "Wollen Sie einen neuen HR-Zugang für " + vornameField.getText() + " " + zweitNameField.getText() + " " + nameField.getText() + " erstellen?");

                switch (res) {

                    // Yes
                    case 0:
                        int res2 = confirmCreate.showConfirmDialog(main, "Wollen Sie dem Account Adminrechte geben?");
                        switch (res2) {
                            // Yes
                            case 0:
                                PersonalakteEditModel pab = new PersonalakteEditModel();
                                pab.generateHR(Integer.parseInt(pidField.getText()), vornameField.getText(), nameField.getText(), true, main);
                                break;
                            // No
                            case 1:
                                PersonalakteEditModel p = new PersonalakteEditModel();
                                p.generateHR(Integer.parseInt(pidField.getText()), vornameField.getText(), nameField.getText(), false, main);
                                break;
                        }
                    // No
                    case 1:
                        confirmCreate.setVisible(false);
                        break;
                }
            }
        });
    }
}
