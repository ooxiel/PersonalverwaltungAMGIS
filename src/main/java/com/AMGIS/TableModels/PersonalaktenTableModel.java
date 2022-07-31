package com.AMGIS.TableModels;

import com.AMGIS.Akteure.Personalakten;

import javax.swing.table.AbstractTableModel;

import java.util.List;

public class PersonalaktenTableModel extends AbstractTableModel {

    private final String[] columnNames = {"PERSONAL-ID","ANREDE","VORNAME","ZWEITNAME","NAME","JOBNAME","ABTEILUNG","STANDORT"};
    private List<Personalakten> personalakten;

    private final Class[] columnClass = new Class[]{
            Integer.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class
    };
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }

    public String getColumnName(int column){
        return columnNames[column];
    };

    public PersonalaktenTableModel(List<Personalakten> personalakten){this.personalakten=personalakten;}

    @Override
    public int getRowCount() {
        return personalakten.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

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
