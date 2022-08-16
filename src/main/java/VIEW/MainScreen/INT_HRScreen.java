package VIEW.MainScreen;

import CONTROLLER.Functions.Personalakte;
import VIEW.Personalakte.Personalakte_erstellen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface INT_HRScreen {

    default void createPersonalakte(JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Personalakte_erstellen();
            }
        });
    };

    default void editPersonalakte(JTable table, String caller){
        new Personalakte().edit(table, caller);
    };
    void searchPersonalakte();
}
