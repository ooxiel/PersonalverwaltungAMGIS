package controller.tablemodel.login.hr;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/** ====================================================================================================================
 *  Klasse wird genutzt um eine JTable mit den Logindaten der HR-Mitarbeiter anzuzeigen
 * ====================================================================================================================
 */
public class LogindatenTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "KONTONAME", "PASSWORT","Root-Zugang"};     //Ueberschriften der Spalten
    private List<Logindaten> logindaten;                                                    //Liste von HR-Logindatenobjekten
    private final Class[] columnClass = new Class[]{                                        //Klassen der einzelnen Spalten
            Integer.class, String.class, String.class, Boolean.class
    };

    /** ================================================================================================================
     * Konstruktor der Klasse LogindatenTableModel
     *
     * @param logindaten    Arraylist, welche alle Logindaten enthaelt
     */
    public LogindatenTableModel(List<Logindaten> logindaten) {
        this.logindaten = logindaten;
    }

    /*
        Getter-Methode für den Klassentyp der Spalte
     */
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }
    /*
        Getter-Methode für den Spaltenueberschrift
     */
    public String getColumnName(int column){
      return columnNames[column];
    };


    /*
        Getter-Methode fuer die Row-Groesse
     */
    @Override
    public int getRowCount() {
        return logindaten.size();
    }

    /*
        Getter-Methode fuer die Column-Laenge
     */
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    /*
        Getter-Methoden fuer den Wert in der Tabelle an festgelegter Koordinate ('row','column')
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Logindaten row = logindaten.get(rowIndex);

        switch (columnIndex){
            case 0:
                return row.getId();
            case 1:
                return row.getKontoname();
            case 2:
                return row.getPasswort();
            case 3:
                return row.isHrmitarbeiter();
            default:
                return null;
        }
    }
}
