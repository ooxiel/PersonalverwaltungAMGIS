package GUI.Apperance.Personalakte;

import com.AMGIS.Akteure.HR_Mitarbeiter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class HR_erstellen extends JFrame {

    private JTextField txtName;
    private JTextField txtZweitname;
    private JTextField txtVorname;
    private JPanel main;
    private JButton abbrechenButton;
    private JButton erstellenButton;

    public static void main(String[] args) {
        new HR_erstellen();
    }

    public HR_erstellen(){

        JFrame          frame       = new JFrame();


        frame.add(main);
        frame.setVisible(true);

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        frame.setResizable(false);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);


        abbrechenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        erstellenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<JTextField> parametersNotNull = new ArrayList<>();

                // Felder des personalInfo-Panels

                    parametersNotNull.add(txtName);
                    parametersNotNull.add(txtVorname);


                for (JTextField k : parametersNotNull) {

                    if (k.getText().isEmpty()) {                          // False: keine Fehlermeldung
                        // True: Fehlermeldung wird geworfen
                        k.setText("Notwendige Angabe fehlt!!!");        // Fehlermeldung = "Notwendige Angabe fehlt"

                        k.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                k.setText(null);                        // Fehlermeldung wird gelöscht, sobald das Feld angeklickt wird
                            }

                            @Override
                            public void focusLost(FocusEvent e) {
                                k.removeFocusListener(this);          // Sobald eine neue Eingabe erfolgt ist, kann diese "normal" bearbeteitet werden, ohne, dass das komplette Field gelöscht wird

                            }
                        });
                    }
                }

                if(txtZweitname.getText().length() == 0){
                    txtZweitname.setText(null);
                }

                HR_Mitarbeiter hrMitarbeiter = new HR_Mitarbeiter("Admin",txtName.getText(), txtZweitname.getText(), txtVorname.getText());

                System.out.println(hrMitarbeiter.getNachName());
                System.out.println(hrMitarbeiter.getVorName());
                System.out.println(hrMitarbeiter.getZweitName());


            }
        });
    }
}
