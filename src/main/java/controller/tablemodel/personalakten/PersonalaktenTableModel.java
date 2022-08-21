package controller.tablemodel.personalakten;

import javax.swing.table.AbstractTableModel;

import java.util.List;

/** ====================================================================================================================
 *  lasse wird genutzt um eine JTable mit alle Personalakten aus der Datenbank anzuzeigen
 * ====================================================================================================================
 */

public class PersonalaktenTableModel extends AbstractTableModel {
    private final String[] columnNames = {"PERSONAL-ID","ANREDE","VORNAME","ZWEITNAME","NAME","JOBNAME","ABTEILUNG","STANDORT"};    //Ueberschriften der Spalten
    private List<Personalakten> personalakten;                                                                                       //Liste von personalaktenobjekten
    private final Class[] columnClass = new Class[]{                                                                                //Klassen der einzelnen Spalten
            Integer.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class
    };

    /** ================================================================================================================
     * Konstruktor der Klasse
     *
     * @param personalakten     Arraylist, welche alle Personalakten enthaelt
     */
    public PersonalaktenTableModel(List<Personalakten> personalakten){
        this.personalakten=personalakten;
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
        return personalakten.size();
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
        Personalakten row = personalakten.get(rowIndex);

        switch (columnIndex){
            case 0:
                return row.getId();
            case 1:
                return row.getAnrede();
            case 2:
                return row.getVorname();
            case 3:
                return row.getZweitname();
            case 4:
                return row.getNachname();
            case 5:
                return row.getGeburtsdatum();
            case 6:
                return row.getTelefon();
            case 7:
                return row.getEmail();
            case 8:
                return row.getStrasse();
            case 9:
                return row.getStrassenNR();
            case 10:
                return row.getStrassenBuchstabe();
            case 11:
                return row.getLand();
            case 12:
                return row.getBundesland();
            case 13:
                return row.getPlz();
            case 14:
                return row.getJobname();
            case 15:
                return row.getBesGrad();
            case 16:
                return row.getAbteilung();
            case 17:
                return row.getAbtLeiter();
            case 18:
                return row.getRaum();
            case 19:
                return row.getStandort();
            case 20:
                return row.getErstelltDatum();
            case 21:
                return row.getLetzteAenderung();
            default:
                return null;
        }
    }
}
