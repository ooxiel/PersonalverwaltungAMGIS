package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.*;
import org.jfree.data.general.DefaultPieDataset;

public class MainHR {
    private JPanel main;
    private JButton dashboardButton;
    private JButton personalakteErstellenButton;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JPanel dash;
    private JPanel dashboardPanel;
    private JPanel personalakteErstellen;

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
                tabbedPane1.setSelectedComponent(dashboardPanel);
            }
        });


        personalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedComponent(personalakteErstellen);
            }
        });
    }
}
