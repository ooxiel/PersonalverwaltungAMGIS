package DatenHandling;


import Akteure.Mitarbeiter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

        HSSFWorkbook datei = new HSSFWorkbook();

        Sheet blatt = datei.createSheet("Sheet 1");



    }

    }


