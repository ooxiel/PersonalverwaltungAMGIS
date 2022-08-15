package CONTROLLER.Appearance;

import VIEW.Login.Login;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DefaultFraming {

    public void show(JFrame frame, JPanel main, int width, int height, String exitType){

        frame.add(main);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        disposeType(frame, exitType);
    }

    private void disposeType(JFrame frame, String exitType){

        if (exitType.equals("EXIT")){
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }
        if(exitType.equals("DISPOSE")){
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        }
    }

    public void defaultLogout (JFrame frame){

        JMenuBar menu = new JMenuBar();
        JMenu logout = new JMenu("Logout");

        logout.setForeground(Color.white);

        menu.setBackground(Color.decode("#050a30"));

        menu.add(logout);
        logout.addMenuListener(new MenuListener() {

            @Override
            public void menuSelected(MenuEvent e) {
                try {
                    new Login();
                    frame.dispose();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void menuDeselected(MenuEvent e) {
            }

            @Override
            public void menuCanceled(MenuEvent e) {
            }
        });

        frame.setJMenuBar(menu);
    }
    public void defaultDispose (JFrame frame, JButton button){
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

}
