package GUI.Apperance;

import com.AMGIS.Akteure.Mitarbeiter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MitarbeiterTableModel extends AbstractTableModel {

    private final String[] COLUMNS = {"PERSONAL-ID", "NAME", "VORNAME"};
    private List<Mitarbeiter> mitarbeiter;

    private MitarbeiterTableModel(List<Mitarbeiter> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    @Override
    public int getRowCount() {
        return mitarbeiter.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
