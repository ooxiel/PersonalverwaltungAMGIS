package view.mainview;

import controller.functions.PersonalakteController;
import view.personalakte.PersonalakteCreateView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface InterfaceHRScreen {
    //Interface fuer die HRView und RootView
    default void createPersonalakte(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PersonalakteCreateView();
            }
        });
    };

    default void editPersonalakte(JTable table, String caller){
        new PersonalakteController().edit(table, caller);
    };
    void searchPersonalakte();
}
