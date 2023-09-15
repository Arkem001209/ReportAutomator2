import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.util.Base64;

public class FinancialDataRetriever {
    private static final String XPLAN_API_URL = "https://londonwall.xplan.iress.co.uk/resourceful";
    private static final String API_KEY = "pGQsWd3FYdyJOnyno5Ng";
    private static final String USERNAME = "APIUser";
    private static final String PASSWORD = "WelcomeBack!1";

    private static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
    public static JsonNode fetchFinancialData() throws Exception {
        // Create an HTTP URL connection
        URL url = new URL(XPLAN_API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Disable cookie handling
        connection.setRequestProperty("Cookie", "dummyCookie=1"); // Set a dummy cookie to disable automatic cookie handling

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(XPLAN_API_URL))
                .header("Authorization", getBasicAuthenticationHeader(USERNAME, PASSWORD))
                .header("X-Username", USERNAME)
                .header("X-Password", PASSWORD)
                .build();

        // Add headers for authorization

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(connection.getInputStream());
            return jsonResponse;
        } else {
            throw new Exception("Failed to fetch financial data from XPLAN API. Response code: " + responseCode);
        }
    }

    public static void main(String[] args) {
        try {
            JsonNode financialData = fetchFinancialData();
            // Process the retrieved financial data as needed
            System.out.println(financialData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

