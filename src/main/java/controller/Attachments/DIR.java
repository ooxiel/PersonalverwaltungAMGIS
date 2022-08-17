package controller.Attachments;


import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class DIR {

    public void clearOnClose(JFrame frame, JButton button){

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);

                File dir = new File("src/main/resources/AktenFiles/Pending/");
                try {
                    FileUtils.cleanDirectory(dir);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();

                File dir = new File("src/main/resources/AktenFiles/Pending/");
                try {
                    FileUtils.cleanDirectory(dir);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
