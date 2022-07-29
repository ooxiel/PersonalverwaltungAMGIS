package com.AMGIS.Files;

import com.AMGIS.Files.Anlagen;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AnlagenTableModel extends AbstractTableModel {

    private final String[] columnNames = {"ANLAGE"};
    private List<Anlagen> anlagen;

    private final Class[] columnClass = new Class[]{
            String.class
    };
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }

    public String getColumnName(int column){
        return columnNames[column];
    };

    public AnlagenTableModel(List<Anlagen> anlagen){this.anlagen=anlagen;}

    @Override
    public int getRowCount() {
        return anlagen.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Anlagen row = anlagen.get(rowIndex);

        switch(columnIndex){
            case 0:
                return row.getFileName();
            default:
                return null;
        }
    }
}
