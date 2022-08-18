package view.mainview;

import controller.appearance.DefaultFraming;

import javax.swing.*;

public interface InterfaceScreen {
    //Interface fuer das Fenster
    void show(JFrame frame, JPanel main, String id);

    default void logout(JFrame frame){
        new DefaultFraming().defaultLogout(frame);
    }
}
