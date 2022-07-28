package com.AMGIS.TableModels;

import com.AMGIS.Akteure.Personalakten;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
        if(0==columnIndex){
            return row.getId();
        } else if (1==columnIndex) {
            return row.getAnrede();
        } else if (2==columnIndex) {
            return row.getVorname();
        } else if (3==columnIndex) {
            return row.getZweitname();
        }else if (4==columnIndex) {
            return row.getNachname();
        }else if (5==columnIndex) {
            return row.getGeburtsdatum();
        }else if (6==columnIndex) {
            return row.getTelefon();
        }else if (7==columnIndex) {
            return row.getEmail();
        }else if (8==columnIndex) {
            return row.getStrasse();
        }else if (9==columnIndex) {
            return row.getStrassenNR();
        }else if (10==columnIndex) {
            return row.getStrassenBuchstabe();
        }else if (11==columnIndex) {
            return row.getLand();
        }else if (12==columnIndex) {
            return row.getBundesland();
        }else if (13==columnIndex) {
            return row.getPlz();
        }else if (14==columnIndex) {
            return row.getJobname();
        }else if (15==columnIndex) {
            return row.getBesGrad();
        }else if (16==columnIndex) {
            return row.getAbteilung();
        }else if (17==columnIndex) {
            return row.getAbtLeiter();
        }else if (18==columnIndex) {
            return row.getRaum();
        }else if (19==columnIndex) {
            return row.getStandort();
        }else if (20==columnIndex) {
            return row.getErstelltDatum();
        }else if (21==columnIndex) {
            return row.getLetzteAenderung();
        }
        return null;
    }
}
