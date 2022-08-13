package CONTROLLER.Services;

import javax.swing.*;

public interface INTPersonalakte {

    void create();
    void save();
    void edit(JTable table);
    void delete(JFrame frame, JPanel main, JButton button, String pid);

}
