package controller.functions;

import controller.userinput.checkinput.StaticInputProof;
import model.personalakten.PersonalakteEditModel;
import model.personalakten.PersonalakteCreateModel;
import view.personalakte.HRPersonalakteEditView;
import view.personalakte.RootPersonalakteEditView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class PersonalakteController {

    public void create(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                       JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,
                       JTextField hausnummerZusatzField, JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField) {

        StaticInputProof staticInput = new StaticInputProof();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                staticInput.setMaxInteger(beschaeftigungField, 100);

                if (staticInput.inputNotNull(lettersOnly) &&
                        staticInput.inputNotNull(numbersOnly) &&
                        staticInput.inputNotNull(specialChars) &&
                        staticInput.comboBoxFieldisEmpty(geschlecht)) {

                    JOptionPane.showMessageDialog(main, "Es fehlen notwendige Eingaben!");

                } else if(staticInput.dateValid(geburstagField) && staticInput.telefonValide(telefonField) && staticInput.mailValide(emailField)){

                        PersonalakteCreateModel pae = new PersonalakteCreateModel();

                        pae.einfuegen(geschlecht.getSelectedItem().toString(), vornameField.getText(), zweitNameField.getText(), nameField.getText(),
                                geburstagField.getText(), telefonField.getText(), emailField.getText(), strasseField.getText(), hausnummerField.getText(),
                                hausnummerZusatzField.getText(), landField.getText(), bundeslandField.getText(), plzField.getText(), jobnameField.getText(),
                                beschaeftigungField.getText(), abteilungField.getText(), abteilungsLeiterField.getText(), raumField.getText(), standortField.getText(), main);

                        try {
                            pae.con.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(main, "Eingabe Erfolgreich!");
                        frame.dispose();

                } else {
                        JOptionPane.showMessageDialog(main, "Ungueltige Angaben!");
                }
            }
        });
    }

    public void save(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JLabel pidField, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                     JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,JTextField hausnummerZusatzField,
                     JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField, JLabel erstelltDate) {

        StaticInputProof staticInput = new StaticInputProof();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                staticInput.setMaxInteger(beschaeftigungField, 100);

                if (staticInput.inputNotNull(lettersOnly) &&
                        staticInput.inputNotNull(numbersOnly) &&
                        staticInput.inputNotNull(specialChars) &&
                        staticInput.comboBoxFieldisEmpty(geschlecht)) {

                    JOptionPane.showMessageDialog(main, "Es fehlen notwendige Eingaben!");

                } else if(staticInput.dateValid(geburstagField) && staticInput.telefonValide(telefonField) && staticInput.mailValide(emailField)){

                    PersonalakteEditModel pab = new PersonalakteEditModel();
                    pab.speichernPA(Integer.parseInt(pidField.getText()), geschlecht.getSelectedItem().toString(), vornameField.getText(), zweitNameField.getText(), nameField.getText(),
                            geburstagField.getText(), telefonField.getText(), emailField.getText(), strasseField.getText(), hausnummerField.getText(),
                            hausnummerZusatzField.getText(), landField.getText(), bundeslandField.getText(), plzField.getText(), jobnameField.getText(),
                            beschaeftigungField.getText(), abteilungField.getText(), abteilungsLeiterField.getText(), raumField.getText(), standortField.getText(), erstelltDate.getText());
                    try {
                        pab.con.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    JOptionPane.showMessageDialog(main, "Eingabe Erfolgreich!");
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(main, "Ungueltige Angaben!");
                }
            }
        });
    }


    public void edit(JTable table, String caller) {

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (e.getClickCount() == 2) {
                    JTable selected = (JTable) e.getSource();
                    int row = selected.getSelectedRow();
                    int id_toEdit = Integer.parseInt(String.valueOf(selected.getModel().getValueAt(row, 0)));
                    Connection con;

                    try {
                        Class.forName("org.hsqldb.jdbcDriver");
                    } catch (ClassNotFoundException ee) {
                        return;
                    }

                    try {
                        con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");
                        String sql = "SELECT ms.person_id, ms.anrede, ms.vorname, ms.zweitname,ms.nachname, ms.geburtstag, ms.telefon, ms.email, ai.strasse, ai.strassen_nummer, ai.strassen_buchstabe, ai.land, ai.bundesland, ai.plz, ji.jobname, ji.beschaeftigungsgrad, ji.abteilung, ji.abteilungsleiter, ji.raum, ji.standort, ms.erstellt_datum, ms.aenderung_datum FROM mitarbeiterstamm ms, adressinfo ai, jobinfo ji WHERE ms.person_id=" + id_toEdit + " AND ms.person_id=ai.Adress_ID AND person_id=ji.job_ID";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            String anrede = String.valueOf(rs.getString(2));
                            String vorname = String.valueOf(rs.getString(3));
                            String zweitname = String.valueOf(rs.getString(4));
                            String nachname = String.valueOf(rs.getString(5));
                            String geburtsdatum = String.valueOf(rs.getString(6));
                            String telefon = String.valueOf(rs.getString(7));
                            String email = String.valueOf(rs.getString(8));
                            String strasse = String.valueOf(rs.getString(9));
                            String strassenNR = String.valueOf(rs.getString(10));
                            String strassenBuchstabe = String.valueOf(rs.getString(11));
                            String land = String.valueOf(rs.getString(12));
                            String bundesland = String.valueOf(rs.getString(13));
                            String plz = String.valueOf(rs.getString(14));
                            String jobname = String.valueOf(rs.getString(15));
                            String besGrad = String.valueOf(rs.getString(16));
                            String abteilung = String.valueOf(rs.getString(17));
                            String abtLeiter = String.valueOf(rs.getString(18));
                            String raum = String.valueOf(rs.getString(19));
                            String standort = String.valueOf(rs.getString(20));
                            String erstelltDatum = String.valueOf(rs.getString(21));
                            String letzteAenderung = String.valueOf(rs.getString(22));

                            if(caller.equals("ROOT")){
                                new RootPersonalakteEditView(id, anrede, vorname, zweitname, nachname, geburtsdatum, telefon, email, strasse, strassenNR, strassenBuchstabe, land, bundesland, plz, jobname, besGrad, abteilung, abtLeiter, raum, standort, erstelltDatum, letzteAenderung);
                            }else{
                                new HRPersonalakteEditView(id, anrede, vorname, zweitname, nachname, geburtsdatum, telefon, email, strasse, strassenNR, strassenBuchstabe, land, bundesland, plz, jobname, besGrad, abteilung, abtLeiter, raum, standort, erstelltDatum, letzteAenderung);
                            }
                        }

                        stmt.close();
                        rs.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void delete(JFrame frame, JPanel main, JButton button, String pid) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane confirmDelete = new JOptionPane();
                int res = confirmDelete.showConfirmDialog(main, "Sind Sie sicher, dass diese Personalakte endgültig gelöscht werden soll?");

                switch (res) {

                    case 0:

                        Connection con = null;
                        try {
                            Class.forName("org.hsqldb.jdbcDriver");
                        } catch (ClassNotFoundException eee) {
                            return;
                        }
                        try {con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");}catch (SQLException ee) {ee.printStackTrace();}

                        int id = Integer.parseInt(pid);
                        String sql = "DELETE FROM Mitarbeiterstamm WHERE Person_ID=" + id + "; DELETE FROM Aktenkennzeichen WHERE Akten_ID="+id+"; DELETE FROM adressinfo WHERE Adress_ID=" + id + ";DELETE FROM aktenstamm WHERE Akten_id=" + id + ";DELETE FROM jobinfo WHERE job_id=" + id + ";DELETE FROM aktenkennzeichen WHERE Akten_id=" + id + ";DELETE FROM mitarbeiterlogin  WHERE m_id=" + id + ";DELETE FROM hrroot WHERE hr_id=" + id + ";";
                        try {
                            Statement stmt = con.createStatement();
                            stmt.executeQuery(sql);


                            File file = new File( "src/main/resources/AktenFiles/"+id);
                            if ( file.isDirectory() )
                            {
                                File[] listFiles = file.listFiles();
                                for ( int i = 0; i < listFiles.length; i++ )
                                {
                                    file=( listFiles[ i ] );
                                    file.delete();
                                }
                            }

                            new File("src/main/resources/AktenFiles/"+id).delete();
                            frame.dispose();


                            JOptionPane.showMessageDialog(main,"Personalakte erfolgreich gelöscht");

                        }catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;
                    case 1:
                        confirmDelete.setVisible(false);
                        break;
                }
            }
        });
    }
}
