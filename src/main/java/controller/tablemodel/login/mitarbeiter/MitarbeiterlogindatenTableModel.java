package controller.tablemodel.login.mitarbeiter;

import controller.tablemodel.login.mitarbeiter.Mitarbeiterlogindaten;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MitarbeiterlogindatenTableModel extends AbstractTableModel {
    //Überschriften der Spalten
    private final String[] columnNames = {"ID", "KONTONAME", "PASSWORT"};
    //Liste von mitarbeierlogindatenobjekten
    private List<Mitarbeiterlogindaten> mitarbeiterlogindaten;
    //Klassen der einzelnen Spalten
    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class
    };
    //getter Methode für den Klassentyp der Spalte
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }
    //getter Methode für den Spaltenüberschrift
    public String getColumnName(int column){
      return columnNames[column];
    }

    public MitarbeiterlogindatenTableModel(List<Mitarbeiterlogindaten> mitarbeiterlogindaten) {
        this.mitarbeiterlogindaten = mitarbeiterlogindaten;
    }
    //Getter Methoden
    @Override//getter Methode für Row-größe
    public int getRowCount() {
        return mitarbeiterlogindaten.size();
    }

    @Override//getter Methode für Column-länge
    public int getColumnCount() {
        return columnNames.length;
    }

    //getter Methode für den Wert in der Tabelle an Koordinate ('row','column')
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
