package com.AMGIS;

import javax.swing.*;
import javax.swing.text.DocumentFilter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class Proof extends DocumentFilter {

    public void jTextFieldisNull (JComboBox geschlecht, JLabel hintAnrede, JTextField nameField, JTextField vornameField, JTextField geburstagField, JTextField emailField, JTextField telefonField,
                                  JTextField strasseField, JTextField hausnummerField, JTextField landField, JTextField bundeslandField, JTextField plzField,
                                  JTextField jobnameField, JTextField beschaeftigungField, JTextField positionField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField regionField){

        // ArrayList wird erstellt und beinhaltet alle Felder der Form

        ArrayList<JTextField> parametersNotNull = new ArrayList<>();


        // Felder des personalInfo-Panels

        parametersNotNull.add(nameField);
        parametersNotNull.add(vornameField);
        parametersNotNull.add(geburstagField);
        parametersNotNull.add(emailField);
        parametersNotNull.add(telefonField);

        // Felder des adress-Panels

        parametersNotNull.add(strasseField);
        parametersNotNull.add(hausnummerField);
        parametersNotNull.add(landField);
        parametersNotNull.add(bundeslandField);
        parametersNotNull.add(plzField);

        // Felder des jobInformation-Panels

        parametersNotNull.add(jobnameField);
        parametersNotNull.add(beschaeftigungField);
        parametersNotNull.add(positionField);
        parametersNotNull.add(abteilungField);
        parametersNotNull.add(abteilungsLeiterField);
        parametersNotNull.add(raumField);
        parametersNotNull.add(regionField);

        // Ueberpruefung, ob eine Eingabe vorgenommen wurde

        if(geschlecht.getSelectedIndex() == 0){
            hintAnrede.setText("Notwendige Angabe fehlt!!!");

            geschlecht.addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {
                    hintAnrede.setVisible(false);
                }

                @Override
                public void focusLost(FocusEvent e) {
                    geschlecht.removeFocusListener(this);
                }
            });
        }

        // Ueberpruefung, ob eine Eingabe vorgenommen wurde

        for (JTextField k: parametersNotNull) {

            if(k.getText().isEmpty()){                          // False: keine Fehlermeldung
                // True: Fehlermeldung wird geworfen
                k.setText("Notwendige Angabe fehlt!!!");        // Fehlermeldung = "Notwendige Angabe fehlt"

                k.addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        k.setText(null);                        // Fehlermeldung wird gelöscht, sobald das Feld angeklickt wird
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        k.removeFocusListener(this);          // Sobald eine neue Eingabe erfolgt ist, kann diese "normal" bearbeteitet werden, ohne, dass das komplette Field gelöscht wird

                    }
                });
            }
        }
    }

    @Override
    public void insertString()
}

