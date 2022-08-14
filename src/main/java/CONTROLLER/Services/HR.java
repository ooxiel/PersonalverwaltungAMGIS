package CONTROLLER.Services;

import MODEL.Personalakten.PA_bearbeiten;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HR {

    public void create(JPanel main, JButton button, JLabel pidField, JTextField vornameField, JTextField zweitNameField, JTextField nameField){

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane confirmCreate = new JOptionPane();

                int res = confirmCreate.showConfirmDialog(main, "Wollen Sie einen neuen HR-Mitarbeiter für " + vornameField.getText() + " " + zweitNameField.getText() + " " + nameField.getText() + " erstellen?");

                switch (res) {

                    // Yes
                    case 0:

                        PA_bearbeiten pab = new PA_bearbeiten();
                        pab.generateHR(Integer.parseInt(pidField.getText()), vornameField.getText(), nameField.getName(), main);
                        break;

                    // No
                    case 1:
                        confirmCreate.setVisible(false);
                        break;
                }
            }
        });
    }
}
