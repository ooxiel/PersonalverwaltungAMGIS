package view.MainScreen;

import controller.Appearance.DefaultFraming;

import javax.swing.*;

public interface INT_Screen {

    void show(JFrame frame, JPanel main, String id);

    default void logout(JFrame frame){
        new DefaultFraming().defaultLogout(frame);
    }
}
