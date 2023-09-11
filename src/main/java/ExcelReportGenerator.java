import com.fasterxml.jackson.databind.JsonNode;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReportGenerator {
    public static void generateReport(JsonNode financialData) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Financial Report");
        Row headerRow = sheet.createRow(0);

        // Create headers
        headerRow.createCell(0).setCellValue("Date");
        headerRow.createCell(1).setCellValue("Value");

        // Process the JSON data and populate the Excel sheet
        int rowNum = 1;
        for (JsonNode dataPoint : financialData) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dataPoint.get("date").asText());
            row.createCell(1).setCellValue(dataPoint.get("value").asDouble());
        }

        // Save the Excel file
        FileOutputStream fileOut = new FileOutputStream("FinancialReport.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}

