package utils;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public Integer getRowCount() {
        Integer rowCount = null;
        try {
            String excelPath = "./data/data/To Do Actions.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(excelPath);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            rowCount = sheet.getPhysicalNumberOfRows();
        } catch (Exception exp) {
            System.out.println(exp.getCause());
            System.out.println(exp.getMessage());
            exp.printStackTrace();
        }
        return rowCount;
    }

    public List<String> getCellData() {
        List<String> actionList = new ArrayList<>();
        String excelPath = "./data/To_Do_Actions.xlsx";
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(excelPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        actionList.add(sheet.getRow(1).getCell(1).getStringCellValue());
        actionList.add(sheet.getRow(3).getCell(1).getStringCellValue());
        actionList.add(sheet.getRow(5).getCell(1).getStringCellValue());
        return actionList;
    }

}
