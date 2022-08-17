package controller.tablemodel.login.hr;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LogindatenTableModel extends AbstractTableModel {
    //Überschriften der Spalten
    private final String[] columnNames = {"ID", "KONTONAME", "PASSWORT","Root-Zugang"};
    //Liste von HR logindatenobjekten
    private List<Logindaten> logindaten;
    //Klassen der einzelnen Spalten
    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class, Boolean.class
    };
    //getter Methode für den Klassentyp der Spalte
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }
    //getter Methode für den Spaltenüberschrift
    public String getColumnName(int column){
      return columnNames[column];
    };

    public LogindatenTableModel(List<Logindaten> logindaten) {
        this.logindaten = logindaten;
    }
    //Getter Methoden
    @Override//getter Methode für Row-größe
    public int getRowCount() {
        return logindaten.size();
    }

    @Override//getter Methode für Column-länge
    public int getColumnCount() {
        return columnNames.length;
    }


    @Override//getter Methode für den Wert in der Tabelle an Koordinate ('row','column')
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
