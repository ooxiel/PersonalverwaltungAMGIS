package view.personalakte;

import controller.appearance.DefaultFraming;
import controller.appearance.IconDesign;
import controller.attachments.AnlagenTree;
import controller.attachments.PendingDirectory;
import controller.functions.PersonalakteController;
import controller.userinput.checkinput.DynamicInputProof;
import controller.userinput.deleteinput.DeleteUserInput;
import controller.userinput.convertinput.ConvertUserInputToArrayList;

import javax.swing.*;
import java.util.ArrayList;

/** ====================================================================================================================
 * Abstrakte Klasse, welche den gesamten Funktionsumfang einer Personalakte abbildet
 * =====================================================================================================================
 */
abstract class AbstractPersonalakte {

    /** ================================================================================================================
     * Methode implementiert alle Standardfunktionen, welche fuer die Darstellung einer Personalakte notwendig sind
     *
     * @param frame         Anzeigefenster
     * @param main          anzuzeigende Inhalt
     * @param pendingTree   Jtree-Darstellung des Pending-Ordners
     * @param fileTree      Jtree-Darstellung des Ordners mit dem Titel der Personal-ID
     * @param button        Abbrechen-Button
     * @param id            Personal-ID
     */
    public void show(JFrame frame, JPanel main, JTree pendingTree, JTree fileTree, JButton button, String id) {

        new DefaultFraming().show(frame, main, 1000, 1000, "DISPOSE");
        new PendingDirectory().clearOnClose(frame, button);

        AnlagenTree anlagenTree = new AnlagenTree();

        /*
            If-Bedingung, um abzufragen, ob bereits ein Ordner mit dem Titel Personal-ID existiert
         */

        if(fileTree == null){
            anlagenTree.show(pendingTree, main, null, "HR");
        }else{
            anlagenTree.show(pendingTree, main, null, "HR");
            anlagenTree.show(fileTree, main, id, "HR");
        }
    }

    /** ================================================================================================================
     * Methode erstelle eine ArrayList fuer die optionalen Eingaben eines Users waehrend dem Erstellen und Bearbeiten
     * von Personalakten.
     *
     * @param zweitNameField            Feld, welches den Zweitnahmen enthaelt
     * @param hausnummerZusatzField     Feld, welches den Hausnummerzusatz (z.B. Buchstaben) enthaelt
     * @param abteilungsLeiterField     Feld, welches den Abteilungsleiter enthaelt
     * @return                          Arraylist, mit den optionalen Inputfeldern
     */

    public ArrayList<JTextField> createOptionalInput(JTextField zweitNameField, JTextField hausnummerZusatzField, JTextField abteilungsLeiterField) {
        return new ConvertUserInputToArrayList().itemstoAdd(zweitNameField, hausnummerZusatzField, abteilungsLeiterField);
    }

    /** ================================================================================================================
     * Methode erstelle eine ArrayList fuer die Felder, welche nur Buchstaben enthaelten duerfen beim Erstellen und Bearbeiten
     * von Personalakten.
     *
     * @param nameField         Feld, welches den Nachnamen enthaelt
     * @param vornameField      Feld, welches den Vornamen enthaelt
     * @param strasseField      Feld, welches den Strasse enthaelt
     * @param landField         Feld, welches das Land  enthaelt
     * @param bundeslandField   Feld, welches den Bundesland enthaelt
     * @param jobnameField      Feld, welches den Jobname enthaelt
     * @param standortField     Feld, welches den Buerostandort enthaelt
     * @return                  ArrayList, mit den Feldern, welche nur Buchstaben enthalten duerfen
     */

    public ArrayList<JTextField> createLettersOnly(JTextField nameField, JTextField vornameField, JTextField strasseField, JTextField landField, JTextField bundeslandField, JTextField jobnameField, JTextField standortField){
        return new ConvertUserInputToArrayList().itemstoAdd(nameField, vornameField, strasseField, landField, bundeslandField, jobnameField, standortField);
    }

    /** ================================================================================================================
     * Methode erstelle eine ArrayList fuer die Felder, welche nur Zahlen enthaelten duerfen beim Erstellen und Bearbeiten
     * von Personalakten.
     *
     * @param plzField              Feld, welches die Postleitzahl enthaelt
     * @param beschaeftigungField   Feld, welches den Beschaeeftigungsgrad enthaelt
     * @param hausnummerField       Feld, welches die Hausnummer enthaelt
     * @return                      ArrayList, mit den Feldern, welche nur Zahlen enthalten duerfen
     */

    public ArrayList<JTextField> createNumbersOnly(JTextField plzField, JTextField beschaeftigungField, JTextField hausnummerField) {
        return new ConvertUserInputToArrayList().itemstoAdd(plzField, beschaeftigungField, hausnummerField);
    }

    /** ================================================================================================================
     * Methode erstelle eine ArrayList fuer die Felder, welche nur Zahlen enthaelten duerfen beim Erstellen und Bearbeiten
     * von Personalakten.
     *
     * @param emailField        Feld, welches die E-Mail-Adresse enthaelt
     * @param geburstagField    Feld, welches den Geburstag enthaelt
     * @param telefonField      Feld, welches die Telefonnummer enthaelt
     * @param abteilungField    Feld, welches die Abteilung enthaelt
     * @return                  ArrayList, mit den Feldern die Sonderzeichen enthaelten
     */
    public ArrayList<JTextField> createSpecialChars(JTextField emailField, JTextField geburstagField, JTextField telefonField, JTextField abteilungField) {
        return new ConvertUserInputToArrayList().itemstoAdd(emailField, geburstagField, telefonField, abteilungField);
    }

    /** ================================================================================================================
     * Methoden wird genutzt, um Dateianhaenge fuer Personalakten zu ersetllen und anzuzeigen
     *
     * @param button            Button, um Anhaenge anzufuegen
     * @param main              anzuzeigende Inhalte
     * @param pendingTree       JTree des Zwischenspeicher-Ordners names "Pending"
     */

    public void createAttachements(JButton button, JPanel main, JTree pendingTree) {
        new AnlagenTree().addAttachements(button, pendingTree, main, null, "HR");
    }

    /** ================================================================================================================
     * Methode stellt die bidlichen Designelemente der Personalakten zur Verfuegung
     *
     * @param frame         Anzeigefenster
     * @param logoIconLeft  Icon auf der linken Seite des Fensters
     * @param logoIconRight Icon auf der rechten Seite des Fensters
     */

    public void design(JFrame frame, JLabel logoIconLeft, JLabel logoIconRight) {

        IconDesign design = new IconDesign();
        design.setIcon(frame, logoIconLeft, "src/main/resources/icons/LogoKlein80x80.png");
        design.setIcon(frame, logoIconRight, "src/main/resources/icons/noLogoKlein80x80.png");
    }

    /** ================================================================================================================
     * Methoden buendlet Methoden-Aufrufe zur dynamischen Ueberpruefung von User-Eingaben
     *
     * @param optionalInput             ArrayList, welche nur Felder mit optionale Eingaben enthaelt
     * @param lettersOnly               ArrayList, welche nur Felder mit Buchstaben enthaelt
     * @param numbersOnly               ArrayList, welche nur Felder mit Zahlen enthaelt
     * @param telefonField              Feld, welches die Telefonnummer enthaelt
     * @param hausnummerZusatzField     Feld, welches den Hausnummerzusatz (z.B. Buchstaben) enthaelt
     * @param plzField                  Feld, welches die Postleitzahl enthaelt
     * @param beschaeftigungField       Feld, welches den Beschaeftigungsgrad enthaelt
     * @param geburstagField            Feld, welches das Geburstdatum enthaelt
     */
    public void proofInputDynamic(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly,
                                  JTextField telefonField, JTextField hausnummerZusatzField, JTextField plzField, JTextField beschaeftigungField,
                                  JTextField geburstagField) {

        DynamicInputProof dynamicInput = new DynamicInputProof();
        dynamicInput.onlyLetterField(optionalInput);
        dynamicInput.onlyLetterField(lettersOnly);
        dynamicInput.onlyNumberField(numbersOnly);

        dynamicInput.setAmountofCharacterAllowed(telefonField, 15);
        dynamicInput.setAmountofCharacterAllowed(hausnummerZusatzField, 1);
        dynamicInput.setAmountofCharacterAllowed(plzField, 5);
        dynamicInput.setAmountofCharacterAllowed(beschaeftigungField, 3);

        dynamicInput.dateField(geburstagField);
    }

    /** ================================================================================================================
     * Methode ruft weitere Methode auf, um den gesamten User-Input auf einmal zu loeschen
     *
     * @param optionalInput     ArrayList, welche nur Felder mit optionale Eingaben enthaelt
     * @param lettersOnly       ArrayList, welche nur Felder mit Buchstaben enthaelt
     * @param numbersOnly       ArrayList, welche nur Felder mit Zahlen enthaelt
     * @param specialChars      ArrayList, welche nur Felder mit Sonderzeichen enthaelt
     * @param button            Button, welcher alle Eingaben loescht
     * @param raumField         Feld, welches die Bueronummer enthaelt
     * @param geschlecht        ComboBox, welche die Auswahl des Geschlechts erlaubt
     */

    public void deleteAll(ArrayList<JTextField> optionalInput, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                          JButton button, JTextField raumField, JComboBox geschlecht) {

        new DeleteUserInput().all(button, optionalInput, lettersOnly, numbersOnly, specialChars, raumField, geschlecht);
    }

    /** ================================================================================================================
     *  Methode ruft weitere Methode auf, um eine Personalakte zu erstellen
     *
     * @param frame                     Anzeigefenster
     * @param main                      anzuzeigende Inhalte
     * @param button                    Button, um eine Personalakte zu erstellen
     * @param geschlecht                ComboBox zur Auswahl des Geschlechts
     * @param beschaeftigungField       Feld, welches den Beschaeftigungsgrad enthaelt
     * @param lettersOnly               ArrayList, welche nur Felder mit Buchstaben enthaelt
     * @param numbersOnly               ArrayList, welche nur Felder mit Zahlen enthaelt
     * @param specialChars              ArrayList, welche nur Felder mit Sonderzeichen enthaelt
     * @param geburstagField            Feld, welches den Geburstag enthaelt
     * @param telefonField              Feld, welches die Telefonnummer enthaelt
     * @param emailField                Feld, welches die E-Mail-Adresse enthaelt
     * @param vornameField              Feld, welches den Vornamen enthaelt
     * @param zweitNameField            Feld, welches den Zweitnamen enthaelt
     * @param nameField                 Feld, welches den Nachnamen enthaelt
     * @param strasseField              Feld, welches die wohnhafte Strasse enthaelt
     * @param hausnummerField           Feld, welches die wohnhafte Hausnummer enthaelt
     * @param hausnummerZusatzField     Feld, welches den Hausnummerzusatz (z.B. Buchstaben) enthaelt
     * @param landField                 Feld, welches die wohnhafte Land enthaelt
     * @param bundeslandField           Feld, welches die wohnhafte Bundesland enthaelt
     * @param plzField                  Feld, welches die Postleitzahl enthaelt
     * @param jobnameField              Feld, welches die Berufsbezeichnung enthaelt
     * @param abteilungField            Feld, welches die Abteilung enthaelt
     * @param abteilungsLeiterField     Feld, welches den Abteilungsleiter enthaelt
     * @param raumField                 Feld, welches die Bueronummer enthaelt
     * @param standortField             Feld, welches den Buerostandort enthaelt
     */


    public void createPersonalakte(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                                 JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,
                                 JTextField hausnummerZusatzField, JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField) {

        new PersonalakteController().create(frame, main, button, geschlecht, beschaeftigungField, lettersOnly, numbersOnly, specialChars, geburstagField,
                telefonField, emailField, vornameField, zweitNameField, nameField, strasseField, hausnummerField, hausnummerZusatzField, landField, bundeslandField,
                plzField, jobnameField, abteilungField, abteilungsLeiterField, raumField, standortField);
    }

    /** ================================================================================================================
     * Methode ruft weitere Methode auf, welche eine ueberarbeteitet Personalakte speichert
     *
     * @param frame                     Anzeigefenster
     * @param main                      anzuzeigende Inhalte
     * @param button                    Button, um eine Personalakte zu erstellen
     * @param geschlecht                ComboBox zur Auswahl des Geschlechts
     * @param beschaeftigungField       Feld, welches den Beschaeftigungsgrad enthaelt
     * @param lettersOnly               ArrayList, welche nur Felder mit Buchstaben enthaelt
     * @param numbersOnly               ArrayList, welche nur Felder mit Zahlen enthaelt
     * @param specialChars              ArrayList, welche nur Felder mit Sonderzeichen enthaelt
     * @param geburstagField            Feld, welches den Geburstag enthaelt
     * @param telefonField              Feld, welches die Telefonnummer enthaelt
     * @param emailField                Feld, welches die E-Mail-Adresse enthaelt
     * @param vornameField              Feld, welches den Vornamen enthaelt
     * @param zweitNameField            Feld, welches den Zweitnamen enthaelt
     * @param nameField                 Feld, welches den Nachnamen enthaelt
     * @param strasseField              Feld, welches die wohnhafte Strasse enthaelt
     * @param hausnummerField           Feld, welches die wohnhafte Hausnummer enthaelt
     * @param hausnummerZusatzField     Feld, welches den Hausnummerzusatz (z.B. Buchstaben) enthaelt
     * @param landField                 Feld, welches die wohnhafte Land enthaelt
     * @param bundeslandField           Feld, welches die wohnhafte Bundesland enthaelt
     * @param plzField                  Feld, welches die Postleitzahl enthaelt
     * @param jobnameField              Feld, welches die Berufsbezeichnung enthaelt
     * @param abteilungField            Feld, welches die Abteilung enthaelt
     * @param abteilungsLeiterField     Feld, welches den Abteilungsleiter enthaelt
     * @param raumField                 Feld, welches die Bueronummer enthaelt
     * @param standortField             Feld, welches den Buerostandort enthaelt
     * @param erstelltDate
     */
    public void savePersonalakte(JFrame frame, JPanel main, JButton button, JComboBox geschlecht, JLabel pidField, JTextField beschaeftigungField, ArrayList<JTextField> lettersOnly, ArrayList<JTextField> numbersOnly, ArrayList<JTextField> specialChars,
                                 JTextField geburstagField, JTextField telefonField, JTextField emailField, JTextField vornameField, JTextField zweitNameField, JTextField nameField, JTextField strasseField, JTextField hausnummerField,JTextField hausnummerZusatzField,
                                 JTextField landField, JTextField bundeslandField, JTextField plzField, JTextField jobnameField, JTextField abteilungField, JTextField abteilungsLeiterField, JTextField raumField, JTextField standortField, JLabel erstelltDate) {

        new PersonalakteController().save(frame, main, button, geschlecht, pidField, beschaeftigungField, lettersOnly, numbersOnly, specialChars, geburstagField,
                telefonField, emailField, vornameField, zweitNameField, nameField, strasseField, hausnummerField, hausnummerZusatzField, landField, bundeslandField,
                plzField, jobnameField, abteilungField, abteilungsLeiterField, raumField, standortField, erstelltDate);
    }

    /** ================================================================================================================
     * Methode ruft eine weitere Methode auf, um eine Personalakte zu löschen
     *
     * @param frame     Anzeigefenster
     * @param main      anzuzeigende Inhalte
     * @param button    Button, um eine Personalakte zu loeschen
     * @param pid       Personal-ID
     */
    public void deletePersonalakte(JFrame frame, JPanel main, JButton button, String pid){
        new PersonalakteController().delete(frame, main, button, pid);
    }
}
