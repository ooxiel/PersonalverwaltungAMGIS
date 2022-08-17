package controller.tablemodel.personalakten;

import javax.swing.table.AbstractTableModel;

import java.util.List;

public class PersonalaktenTableModel extends AbstractTableModel {
    //Überschriften der Spalten
    private final String[] columnNames = {"PERSONAL-ID","ANREDE","VORNAME","ZWEITNAME","NAME","JOBNAME","ABTEILUNG","STANDORT"};
    //Liste von personalaktenobjekten
    private List<Personalakten> personalakten;
    //Klassen der einzelnen Spalten
    private final Class[] columnClass = new Class[]{
            Integer.class,String.class,String.class,String.class,String.class,String.class,String.class,String.class
    };
    //getter Methode für den Klassentyp der Spalte
    public Class<?> getColumnClass(int columnIndex){
        return columnClass[columnIndex];
    }
    //getter Methode für den Spaltenüberschrift
    public String getColumnName(int column){
        return columnNames[column];
    };

    public PersonalaktenTableModel(List<Personalakten> personalakten){this.personalakten=personalakten;}

    @Override//getter Methode für Row-größe
    public int getRowCount() {
        return personalakten.size();
    }

    @Override//getter Methode für Column-länge
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override //getter Methode für den Wert in der Tabelle an Koordinate ('row','column')
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
