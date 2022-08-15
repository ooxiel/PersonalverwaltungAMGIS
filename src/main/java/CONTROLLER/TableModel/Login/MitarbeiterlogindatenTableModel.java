package CONTROLLER.TableModel.Login;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MitarbeiterlogindatenTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ID", "KONTONAME", "PASSWORT"};
    private List<Mitarbeiterlogindaten> mitarbeiterlogindaten;

    private final Class[] columnClass = new Class[]{
            Integer.class, String.class, String.class
    };

    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }

    public String getColumnName(int column){
      return columnNames[column];
    }

    public MitarbeiterlogindatenTableModel(List<Mitarbeiterlogindaten> mitarbeiterlogindaten) {
        this.mitarbeiterlogindaten = mitarbeiterlogindaten;
    }

    @Override
    public int getRowCount() {
        return mitarbeiterlogindaten.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }


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
