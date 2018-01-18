package micevisualization;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mouseProximity {
    private Stage stage;
    private double [][] proxCoef;
    private final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss.SSS");
    public final String EXCELFILE = "Excel File";
    public final ArrayList<String> XLSFILES = new ArrayList<>();
    public final int OPEN = 1;
    public final int SAVE = 2;

    public void setStage(Stage stage){
        this.stage = stage;
    }
    public void updateChasing(){

    }
    public mouseProximity(){
        XLSFILES.add("*.xlsx");
        XLSFILES.add("*.xls");
    };
    public void fillProxArray(File f){
        if (f == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("File Error");
            alert.setHeaderText(null);
            alert.setContentText("Unable to continue without Proximity data.");
            alert.showAndWait();
            Platform.exit();
        }
        else {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(f);
                proxCoef = new double[25][25]; //make extra row/column to keep numbers matching grid IDs
                //junk data for row zero and column zero

                for (int i = 0; i < proxCoef[0].length; i++) {
                    proxCoef[0][i] = -1d;
                    proxCoef[i][0] = -1d;
                }

                Sheet sheet = workbook.getSheetAt(0);
                for (int i = 1; i < 25; i++) {
                    Row r = sheet.getRow(i);
                    for (int j = 1; j < 25; j++) {
                        proxCoef[i][j] = r.getCell(j).getNumericCellValue();
                    }
                }
                workbook.close();
                System.out.println("Proximities loaded into array.");
            } catch (IOException | InvalidFormatException e) {
                System.out.printf(e.getLocalizedMessage());
            } finally {
            /*
            Testing array and lookup
            System.out.println(lookupProx(14,15));
            for (int i = 1; i < proxCoef.length ; i++) {
                for (int j = 1; j < proxCoef[i].length; j++) {
                    System.out.println("[" + i + "," + j + "] " + proxCoef[i][j]);
                }
            }
            */
            }
        }
    }
    private double lookupProx(int gridOne, int gridTwo) {
        if ((gridOne == 0 || gridTwo == 0) || (gridOne > 24 || gridTwo > 24))
            return 0.00;
        else
        {
            if (gridOne < gridTwo)
                return proxCoef[gridOne][gridTwo];
            else
                return proxCoef[gridTwo][gridOne];
        }
    }
    public void makeProxXLSX(Mice meeces, ArrayList<MouseByTotalTime> miceTimes){
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet;
            ArrayList<Mouse> mice = meeces.mice;
            for (int i=0;i<mice.size();i++) {
                sheet = workbook.createSheet(mice.get(i).IdLabel);
                sheet.createRow(0);
                sheet.getRow(0).createCell(0).setCellValue("Date Time Stamp");
                for (int q=0;q<mice.get(i).locTimeData.size();q++) {
                    sheet.createRow(q+1);
                    sheet.getRow(q+1).createCell(0).setCellValue(sdf.format(mice.get(i).locTimeData.get(q).timestamp));
                    if(!mice.get(i).locTimeData.get(q).neighbors.isEmpty()) Collections.sort(mice.get(i).locTimeData.get(q).neighbors);
                    for (int j=0; j<mice.get(i).locTimeData.get(q).neighbors.size(); j++) {
                        sheet.getRow(0).createCell(j+1).setCellValue(meeces.getMouseByIdRFID(mice.get(i).locTimeData.get(q).neighbors.get(j).getMouseID()).IdLabel);
                        double l = lookupProx(mice.get(i).locTimeData.get(q).unitLabel, mice.get(i).locTimeData.get(q).neighbors.get(j).getGrid());
                        sheet.getRow(q+1).createCell(j+1).setCellValue(l);
                    }
                }
            }
            sheet = workbook.createSheet("Total Mouse Times");
            Row firstRow = sheet.createRow(0);
            for (int i = 0; i < 26; i++) {
                if (i == 0) {
                    firstRow.createCell(0).setCellValue("Mouse Name");
                } else {
                    firstRow.createCell(i).setCellValue("RFID" + i + " Time");
                }
            }
            for (int i = 0; i < miceTimes.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(miceTimes.get(i).getName());
                for (int j = 0; j < 25; j++) {
                    row.createCell(j + 1).setCellValue(miceTimes.get(i).getReader(j));
                }
            }

            File f = showFileDialog(SAVE, "Save Mouse Proximity & Total Time Data", "Excel File", XLSFILES);
            if (f != null){
                FileOutputStream out = new FileOutputStream(f);
                workbook.write(out);
                out.close();
                System.out.println("Proximities & Times File Written.");
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public File showFileDialog(int dialogType, String title, String extensionDescription , List<String> extensions){
        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(extensionDescription, extensions)
        );

        // show the file explorer window:
        File file = null;
        if (dialogType == OPEN) file = fileChooser.showOpenDialog(stage);
        if (dialogType == SAVE) file = fileChooser.showSaveDialog(stage);
        return file;
    }

}
