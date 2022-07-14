package GUI.Apperance;

import org.apache.poi.ss.usermodel.DataFormat;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Formatter;

public class MainHR {
    private JPanel main;
    private JButton dashboardButton;
    private JButton personalakteErstellenButton;
    private JTabbedPane tabbedPane1;
    private JPanel dash;
    private JTable mainView;
    private JButton neuePersonalakteAnlegenButton;
    private JPanel dashboardPanel;
    private JPanel personalakteErstellen;

    public static void main(String[] args) {
        new MainHR();
    }

    public MainHR(){
        JFrame          frame           = new JFrame();
        FrameLocation   frameLocation   = new FrameLocation();

        frame.add(main);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setSize(1000,1000);
            int widthLayout     = (int) frame.getSize().getWidth();
            int heightLayout    = (int) frame.getSize().getHeight();

        frame.setLocation(frameLocation.center(widthLayout,heightLayout));



        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedComponent(dashboardPanel);
            }
        });


        personalakteErstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(1);
            }
        });
        neuePersonalakteAnlegenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Personalakte_erstellen();
            }
        });
    }
}
