package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainHR {
    private JPanel main;
    private JButton dashboardButton;
    private JButton button2;
    private JButton personalakteErstellenButton;
    private JButton button4;
    private JTabbedPane tabbedPane1;
    private JPanel dashboardPanel;

    public static void main(String[] args) {
        new MainHR();
    }

    public MainHR(){
        JFrame frame = new JFrame();

        frame.add(main);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setSize(1000,1000);



        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(0);
            }
        });
    }
}
