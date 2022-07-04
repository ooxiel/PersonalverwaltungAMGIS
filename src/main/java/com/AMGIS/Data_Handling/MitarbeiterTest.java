package com.AMGIS.Data_Handling;


import com.AMGIS.Akteure.Mitarbeiter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


/*
    https://de.acervolima.com/lesen-und-schreiben-von-daten-in-eine-excel-datei-mit-apache-poi/
 */


public class MitarbeiterTest {
    public static void main(String[] args) throws IOException {


        // Daten speichern mit Sezialisierung

        Mitarbeiter mitarbeiter = new Mitarbeiter("NormalUser", "Farnz","","Whey","Geschlecht","Straße","12A","12345","Land","Job","PID","Buero","Abteilung","Bereich","Position","Vorgesetzer","Abteilung");



        FileOutputStream fos = new FileOutputStream("test.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(mitarbeiter);

        // Daten speichern mit Excel

        XSSFWorkbook workbook   = new XSSFWorkbook();
        XSSFSheet sheet         = workbook.createSheet("Test");

        Map<String, Object[]> data = new TreeMap<String, Object[]>();


        data.put("1",new Object[]{"User-Typ", "Vorname","Zweitname","Nachname","Geschlecht","Straße","Hausnummer","Postleizahl","Land","Jobbezeichnung","Personal-ID","Buero","Abteilung","Bereich","Position","Vorgesetzer","Abteilung"});
        data.put("2",new Object[]{"NormalUser", "Farnz","","Whey","Geschlecht","Straße","12A","12345","Land","Job","PID","Buero","Abteilung","Bereich","Position","Vorgesetzer","Abteilung"});
        data.put("3",new Object[]{"NormalUser", "Farnz","","Whey","Geschlecht","Straße","12A","12345","Land","Job","PID","Buero","Abteilung","Bereich","Position","Vorgesetzer","Abteilung"});

        Set<String> keyset = data.keySet();

        int rowNum = 0;
        int cellnum = 0;

        for (String key : keyset){
            Row row = sheet.createRow(rowNum++);

            Object[] mitarbeiterARR = data.get(key);

            for ( Object obj: mitarbeiterARR){
                Cell cell = row.createCell(cellnum++);

                if (obj instanceof String){
                    cell.setCellValue((String)obj);

                }else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }

        }

        try {
            // this Writes the workbook gfgcontribute
            FileOutputStream out = new FileOutputStream(new File("gfgcontribute.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("gfgcontribute.xlsx written successfully on disk.");
        }
        catch (Exception e) {
            e.printStackTrace();
        }







    }
}
