package GUI;

import com.AMGIS.Akteure.Logindaten;
import com.AMGIS.Akteure.Mitarbeiter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LogindatenTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "KONTONAME", "PASSWORT","HR Mitarbeiter"};
    private List<Logindaten> logindaten;

    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class, Boolean.class
    };

    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }

    public String getColumnName(int column){
      return columnNames[column];
    };

    public LogindatenTableModel(List<Logindaten> logindaten) {
        this.logindaten = logindaten;
    }

    @Override
    public int getRowCount() {
        return logindaten.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Logindaten row = logindaten.get(rowIndex);
        if(0==columnIndex){
            return row.getId();
        } else if (1==columnIndex) {
            return row.getKontoname();
        } else if (2==columnIndex) {
            return row.getPasswort();
        } else if (3==columnIndex) {
            return row.isHrmitarbeiter();
        }
        return null;
    }
}
