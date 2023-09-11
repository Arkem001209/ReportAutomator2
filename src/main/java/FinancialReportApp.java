import com.fasterxml.jackson.databind.JsonNode;

public class FinancialReportApp {
    public static void main(String[] args) {
        try {
            JsonNode financialData = FinancialDataRetriever.fetchFinancialData();
            ExcelReportGenerator.generateReport(financialData);
            System.out.println("Financial report generated successfully.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
