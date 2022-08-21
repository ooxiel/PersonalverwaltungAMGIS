package controller.tablemodel.login.mitarbeiter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/** ====================================================================================================================
 *  Klasse wird genutzt um eine JTable mit den Logindaten der Mitarbeiter anzuzeigen
 * ====================================================================================================================
 */
public class MitarbeiterlogindatenTableModel extends AbstractTableModel {
    //Überschriften der Spalten
    private final String[] columnNames = {"ID", "KONTONAME", "PASSWORT"};
    //Liste von mitarbeierlogindatenobjekten
    private List<Mitarbeiterlogindaten> mitarbeiterlogindaten;
    //Klassen der einzelnen Spalten
    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class
    };

    /** ================================================================================================================
     * Konstruktor der MitarbeiterlogindatenTableModel-Klasse
     *
     * @param mitarbeiterlogindaten     Arraylist, welche alle Mitarbeiterlogindaten enthaelt
     */
    public MitarbeiterlogindatenTableModel(List<Mitarbeiterlogindaten> mitarbeiterlogindaten) {
        this.mitarbeiterlogindaten = mitarbeiterlogindaten;
    }


    /*
        Getter-Methode für den Klassentyp der Spalte
     */
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }
    /*
        Getter-Methode fuer den Spaltenueberschrift
     */
    public String getColumnName(int column){
      return columnNames[column];
    }

    /*
        Getter-Methode für die Row-Groesse
     */

    @Override
    public int getRowCount() {
        return mitarbeiterlogindaten.size();
    }

    /*
        Getter-Methode fuer die Column-Laenge
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /*
        Getter-Methode fuer den Wert in der Tablle anhand der Koordianten ('row','column')
    */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mitarbeiterlogindaten row = mitarbeiterlogindaten.get(rowIndex);

        switch (columnIndex){
            case 0:
                return row.getId();
            case 1:
                return row.getKontoname();
            case 2:
                return row.getPasswort();
            default:
                return null;
        }
    }
}
