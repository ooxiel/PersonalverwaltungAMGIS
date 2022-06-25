package DatenHandling;


import Akteure.Mitarbeiter;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;


/*
    https://www.java-blog-buch.de/0805-die-map-schnittstelle/
    https://mkyong.com/java/apache-poi-reading-and-writing-excel-file-in-java/
 */



public class ExcelDatenSpeichern {

    public static void main(String[] args) {


        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Employee Data");


        List<Mitarbeiter> mitarbeiterList = new ArrayList<>();

        mitarbeiterList.add(new Mitarbeiter("Farnz", "", "Whey", "weib", "Farnz-Street", "19a", 243545, "Dutschland", "Theoretisch WI", "12345", "1.2.3.4.5.6", "Abteilung", "Bereich", "Position", "Vorgesetzter", "Leiter"));
        mitarbeiterList.add(new Mitarbeiter("Farnz", "", "Whey", "weib", "Farnz-Street", "19a", 243545, "Dutschland", "Theoretisch WI", "12345", "1.2.3.4.5.6", "Abteilung", "Bereich", "Position", "Vorgesetzter", "Leiter"));


        System.out.println(mitarbeiterList);

        int rowNum = 0;
        int colNum = 0;

        sheet.createRow(rowNum);







        try {
            FileOutputStream outputStream = new FileOutputStream("");
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        System.out.println("Done");
    }

    }


