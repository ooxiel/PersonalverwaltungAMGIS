package VIEW.TableModel.Login;

import VIEW.TableModel.Login.Logindaten;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LogindatenTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "KONTONAME", "PASSWORT","Root-Zugang"};
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
