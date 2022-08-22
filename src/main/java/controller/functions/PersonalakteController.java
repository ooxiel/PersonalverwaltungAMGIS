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

/** ====================================================================================================================
 *  PersonalaktenController fasst alle Standardfunktionen einer Personalakte:
 *  - erstellen
 *  - bearbeiten
 *  - speichern
 *  - loeschen
 *
 *  in einer Klasse zusammen
 * ====================================================================================================================
 */
public class PersonalakteController {

    /** ================================================================================================================
     * Mit der Methode kann eine neue Personalakte erstellt werden und die Datenbank übernommen werden.
     * Zudem werden die Angaben in den Feldern auf Gueltigkeit ueberprueft.
     *
     * @param frame                     Anzeigerahmen
     * @param main                      angezeigte Inhalte
     * @param button                    Button, welcher gedrueckt wird, um eine Personalakte zu erstellen
     * @param geschlecht                ComboBox zur Auswahl des Geschlechts
     * @param beschaeftigungField       Feld, um den Beschaeftigungsgrad festzulegen
     * @param lettersOnly               ArrayList, welche zur Ueberpruefung von Feldern, in welchen nur Buchstaben zulaessig sind genuztzt wird
     * @param numbersOnly               ArrayList, welche zur Ueberpruefung von Feldern, in welchen nur Zahlen zulaessig sind genuztzt wird
     * @param specialChars              ArrayList, welche zur Ueberpruefung von Feldern, in welchen Sonderzeichen verwendet werden genuztzt wird
     * @param geburstagField            Feld, um den Geburstag einzutragen
     * @param telefonField              Feld, um die Telefonnummer einzutragen
     * @param emailField                Feld, um die E-Mail-Adresse einzutragen
     * @param vornameField              Feld, um den Vornamen einzutragen
     * @param zweitNameField            Feld, um gegebenenfalls den Zweitnamen einzutragen
     * @param nameField                 Feld, um den Nachnamen einzutragen
     * @param strasseField              Feld, um die Wohnstrasse einzutragen
     * @param hausnummerField           Feld, um die Hausnummer
     * @param hausnummerZusatzField     Feld, um gegebenenfalls einen Zusatz zur Hausnummern (z.B. Buchstaben) einzutragen
     * @param landField                 Feld, um das wohnhafte Land einzutragen
     * @param bundeslandField           Feld, um das wohnhafte Bundesland einzutragen
     * @param plzField                  Feld, um die zur Stadt gehoerende Postleitzahl einzutragen
     * @param jobnameField              Feld, um die Berufsbezeichnung einzutragen
     * @param abteilungField            Feld, um die Abteilung einzutragen
     * @param abteilungsLeiterField     Feld, um den Vorgesetzten einzutragen
     * @param raumField                 Feld, um den Bueroraum einzutragen
     * @param standortField             Feld, um den Buerostandort einzutragen
     */
    public void create(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                       JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,
                       JTextField hausnummerZusatzField, JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField) {

        StaticInputProof staticInput = new StaticInputProof();      //Generierung des Objektes fuer Input-Pruefung

        /*
            Beim Betaetigen des "Personalakte erstellen"-Buttons wird der Pruefungsprozess ausgeloest
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                staticInput.setMaxInteger(beschaeftigungField, 100);        // BeschaeftigungsFeld wird auf maximal 100 gesetzt, falls Eingabe hoeher

                /*
                    1. Pruefung: Kein Pflichtfeld darf leer sein -> Pflichtfelder (*)
                    2. Pruefung: Geburstag muss ein reales Datum sein, Telefonnummer und Mail müssen bestimmter Syntax folgen
                 */

                if (staticInput.inputNotNull(lettersOnly) &&
                        staticInput.inputNotNull(numbersOnly) &&
                        staticInput.inputNotNull(specialChars) &&
                        staticInput.comboBoxFieldisEmpty(geschlecht)) {

                    JOptionPane.showMessageDialog(main, "Es fehlen notwendige Eingaben!");      // Pop-Up, wenn leere Felder

                } else if(staticInput.dateValid(geburstagField) && staticInput.telefonValide(telefonField) && staticInput.mailValide(emailField)){

                        PersonalakteCreateModel pae = new PersonalakteCreateModel();

                        /*
                            Methodeaufruf bei der die Daten in die Datenbank eingetragen werden
                         */
                        pae.einfuegen(geschlecht.getSelectedItem().toString(), vornameField.getText(), zweitNameField.getText(), nameField.getText(),
                                geburstagField.getText(), telefonField.getText(), emailField.getText(), strasseField.getText(), hausnummerField.getText(),
                                hausnummerZusatzField.getText(), landField.getText(), bundeslandField.getText(), plzField.getText(), jobnameField.getText(),
                                beschaeftigungField.getText(), abteilungField.getText(), abteilungsLeiterField.getText(), raumField.getText(), standortField.getText(), main);

                        try {
                            pae.con.close();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }

                        JOptionPane.showMessageDialog(main, "Eingabe Erfolgreich!");        //Erfolgsmeldung bei erfolgreicher Eingabe
                        frame.dispose();

                } else {
                        JOptionPane.showMessageDialog(main, "Ungueltige Angaben!");         // Pop-Up, wenn Geburstag, Telefon oder Mail-Adresse ungueltig
                }
            }
        });
    }

    /** ================================================================================================================
     * Mit der 'save'-Methode wird die Bearbeitung einer Personalakte ueberprueft und bei erfolgreichen Angaben
     * anschliessend in die Datenbank uebertragen.
     *
     * @param frame                     Anzeigerahmen
     * @param main                      angezeigte Inhalte
     * @param button                    Button, welcher gedrueckt wird, um eine Personalakte zu erstellen
     * @param geschlecht                ComboBox zur Auswahl des Geschlechts
     * @param beschaeftigungField       Feld, um den Beschaeftigungsgrad festzulegen
     * @param lettersOnly               ArrayList, welche zur Ueberpruefung von Feldern, in welchen nur Buchstaben zulaessig sind genuztzt wird
     * @param numbersOnly               ArrayList, welche zur Ueberpruefung von Feldern, in welchen nur Zahlen zulaessig sind genuztzt wird
     * @param specialChars              ArrayList, welche zur Ueberpruefung von Feldern, in welchen Sonderzeichen verwendet werden genuztzt wird
     * @param geburstagField            Feld, um den Geburstag einzutragen
     * @param telefonField              Feld, um die Telefonnummer einzutragen
     * @param emailField                Feld, um die E-Mail-Adresse einzutragen
     * @param vornameField              Feld, um den Vornamen einzutragen
     * @param zweitNameField            Feld, um gegebenenfalls den Zweitnamen einzutragen
     * @param nameField                 Feld, um den Nachnamen einzutragen
     * @param strasseField              Feld, um die Wohnstrasse einzutragen
     * @param hausnummerField           Feld, um die Hausnummer
     * @param hausnummerZusatzField     Feld, um gegebenenfalls einen Zusatz zur Hausnummern (z.B. Buchstaben) einzutragen
     * @param landField                 Feld, um das wohnhafte Land einzutragen
     * @param bundeslandField           Feld, um das wohnhafte Bundesland einzutragen
     * @param plzField                  Feld, um die zur Stadt gehoerende Postleitzahl einzutragen
     * @param jobnameField              Feld, um die Berufsbezeichnung einzutragen
     * @param abteilungField            Feld, um die Abteilung einzutragen
     * @param abteilungsLeiterField     Feld, um den Vorgesetzten einzutragen
     * @param raumField                 Feld, um den Bueroraum einzutragen
     * @param standortField             Feld, um den Buerostandort einzutragen
     * @param erstelltDate              Feld, in welchem das Erstellungsdatum der Personalakte festgehalten wird
     */

    public void save(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JLabel pidField, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                     JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,JTextField hausnummerZusatzField,
                     JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField, JLabel erstelltDate) {

        StaticInputProof staticInput = new StaticInputProof();      //Generierung des Objektes fuer Input-Pruefung

        /*
            Beim Betaetigen des "Aenderungen uebernehmen"-Buttons wird der Pruefungsprozess ausgeloest
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Input der Felder pruefen
                staticInput.setMaxInteger(beschaeftigungField, 100);        // BeschaeftigungsFeld wird auf maximal 100 gesetzt, falls Eingabe hoeher

                /*
                    1. Pruefung: Kein Pflichtfeld darf leer sein -> Pflichtfelder (*)
                    2. Pruefung: Geburstag muss ein reales Datum sein, Telefonnummer und Mail müssen bestimmter Syntax folgen
                 */

                if (staticInput.inputNotNull(lettersOnly) &&
                        staticInput.inputNotNull(numbersOnly) &&
                        staticInput.inputNotNull(specialChars) &&
                        staticInput.comboBoxFieldisEmpty(geschlecht)) {

                    JOptionPane.showMessageDialog(main, "Es fehlen notwendige Eingaben!");

                } else if(staticInput.dateValid(geburstagField) && staticInput.telefonValide(telefonField) && staticInput.mailValide(emailField)){

                    //Methode bei der die Daten in der Datenbank geändert werden
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

                    JOptionPane.showMessageDialog(main, "Eingabe Erfolgreich!");        //Erfolgsmeldung bei erfolgreicher Eingabe
                    frame.dispose();

                } else {
                    JOptionPane.showMessageDialog(main, "Ungueltige Angaben!");         // Pop-Up, wenn Geburstag, Telefon oder Mail-Adresse ungueltig
                }
            }
        });
    }

    /**
     * Mit der 'edit' Methode koennen Personalakten mit einem Doppelklick aus den HR- und Root-Uebersichtstabllen
     * ausgewaehlt werden und es oeffnet sich der Bearbeiten-Bildschirm mit der entsprechenden Personalakte
     *
     * @param table     Tabelle, zur Uebersicht aller Personalakten im HR- und Root-Bildschirm
     * @param caller    Unterscheidung zwischen HR und Root, da Bearbeitenmodus unterschiedlichen Funktionsumfang
     */
    public void edit(JTable table, String caller) {

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);    // Es kann nur eine Auswahl in der Tablle zeitgleich getroffen werden

        /*
            Tabelle erhällt einen Aktionslistener
         */
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                /*
                    Bei Doppelklick in der Tabelle wird die ID der gewählten Personalakte in die Datenbankabfrage uebergeben
                    und der Bearbeitenbildschirm mit den Informationen aus der Personalakte erscheinen
                 */

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

                    /*
                        Laden der ausgewählten Personalakte aus der Datenbank
                     */
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

                            /*
                                Unterscheidung zwischen Admin und HR-Mitarbeiter -> unterschiedliche Views
                             */

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

    /** ================================================================================================================
     * Mit der Methode 'delete' wird eine Personalakte ueber das Programm aus der Datenbank geloescht
     *
     * @param frame     Anzeigerahmen
     * @param main      angezeigte Inhalte
     * @param button    Loeschen-Button
     * @param pid       Personal-ID
     */
    public void delete(JFrame frame, JPanel main, JButton button, String pid) {

        /*
            Beim Betaetigen des Loeschen-Buttons wird die Loeschung ausgeloest
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                /*
                    Kontrollabfrage, ob wirklich geoeffnete Personalakte geloescht werden soll

                    Ergebnis-Aktionen werden mit Hilfe eines switch-case verarbeitet
                 */
                JOptionPane confirmDelete = new JOptionPane();
                int res = confirmDelete.showConfirmDialog(main, "Sind Sie sicher, dass diese Personalakte endgültig gelöscht werden soll?");

                switch (res) {

                    // Ja
                    case 0:

                        //Verbindung zur Datenbank
                        Connection con = null;
                        try {
                            Class.forName("org.hsqldb.jdbcDriver");
                        } catch (ClassNotFoundException eee) {
                            return;
                        }
                        try {con = DriverManager.getConnection("jdbc:hsqldb:file:src/main/resources/Datenbank/AMGISDatenbank", "amgis", "amgis");}catch (SQLException ee) {ee.printStackTrace();}

                        int id = Integer.parseInt(pid);

                        //Löschen alle Datenbankeinträge
                        String sql = "DELETE FROM Mitarbeiterstamm WHERE Person_ID=" + id + "; DELETE FROM Aktenkennzeichen WHERE Akten_ID="+id+"; DELETE FROM adressinfo WHERE Adress_ID=" + id + ";DELETE FROM aktenstamm WHERE Akten_id=" + id + ";DELETE FROM jobinfo WHERE job_id=" + id + ";DELETE FROM aktenkennzeichen WHERE Akten_id=" + id + ";DELETE FROM mitarbeiterlogin  WHERE m_id=" + id + ";DELETE FROM hrroot WHERE hr_id=" + id + ";";
                        try {//Statement erstellung und ausfuehren
                            Statement stmt = con.createStatement();
                            stmt.executeQuery(sql);

                            /*
                            Der Aktenordner der Personalakte wird durchlaufen und jede Datei wird gelöscht
                             */
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

                            //Aktenordner wird gelöscht
                            new File("src/main/resources/AktenFiles/"+id).delete();
                            frame.dispose();

                            //Erfolgsmeldung+
                            JOptionPane.showMessageDialog(main,"Personalakte erfolgreich gelöscht");

                        }catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        break;

                    // Nein
                    case 1:
                        //PopUp-Fenster wird geschlossen
                        confirmDelete.setVisible(false);
                        break;
                }
            }
        });
    }
}
