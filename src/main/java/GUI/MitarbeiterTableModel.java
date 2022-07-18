package GUI;

import com.AMGIS.Akteure.Mitarbeiter;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MitarbeiterTableModel extends AbstractTableModel {

    private final String[] COLUMNS = {"PERSONAL-ID", "NAME", "VORNAME","HR Mitarbeiter"};
    private List<Mitarbeiter> mitarbeiter;

    public MitarbeiterTableModel(List<Mitarbeiter> mitarbeiter) {
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
