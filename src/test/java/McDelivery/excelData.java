package McDelivery;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class excelData {
    @DataProvider(name = "testDataDetails")
    public Object[][] getData() throws IOException {
        File excelFile = new File("C:\\Users\\david.doggala\\IdeaProjects\\McDelivery\\src\\test\\java\\McDelivery\\testData\\testDataMcD.xlsx");
        System.out.println(excelFile.exists());
        FileInputStream fis = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("TEST");
        int noOfRows = sheet.getPhysicalNumberOfRows();
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        System.out.println(noOfRows);
        System.out.println(noOfColumns);

        Object[][] data = new Object[noOfRows - 1][noOfColumns];
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < noOfRows; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                for (int j = 0; j < noOfColumns; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        if (j == 7) { // Assuming column 7 is the Quantity column
                            data[i - 1][j] = Integer.parseInt(formatter.formatCellValue(cell)); // Convert to Integer
                        } else {
                            data[i - 1][j] = formatter.formatCellValue(cell);
                        }
                    }
                }
            }
        }

        workbook.close();
        fis.close();
        return data;
    }


}

