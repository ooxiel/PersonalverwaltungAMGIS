package com.AMGIS.TableModelling;

import javax.swing.table.AbstractTableModel;
import java.util.List;





public class TableModel extends AbstractTableModel {

    List mitarbeiterlist;
    String headerList[] = new String[]{"Personal-ID", "Name", "Vorname"};


    public TableModel(List list){
        mitarbeiterlist = list;
    }

    public String getColumnName (int col){
        return headerList[col];
    }

    @Override
    public int getRowCount() {
        return mitarbeiterlist.size();
    }

    @Override
    public int getColumnCount() {
        return 10;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
