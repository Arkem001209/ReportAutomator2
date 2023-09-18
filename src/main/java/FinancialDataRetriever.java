import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    //public static JsonNode fetchFinancialData() throws Exception {
        // Create an HTTP URL connection
        //URL url = new URL(XPLAN_API_URL);
        //HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Disable cookie handling
        //connection.setRequestProperty("Cookie", "dummyCookie=1"); // Set a dummy cookie to disable automatic cookie handling
        static JsonNode fetchFinancialData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;

    {
        try {
            request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI(XPLAN_API_URL))
                    .header("Authorization", getBasicAuthenticationHeader(USERNAME, PASSWORD))
                    .header("X-Username", USERNAME)
                    .header("X-Password", PASSWORD)
                    .header("Cookie", "dummyCookie=1")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    // Add headers for authorization
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    HttpEntity entity = response.notify();

    if (entity != null) {
        String financialData = EntityUtils.toString(entity);
        JSONObject json = new JSONObject(financialData);
    }

    return response.body();
    }


        //if (responseCode == HttpURLConnection.HTTP_OK) {
            //ObjectMapper objectMapper = new ObjectMapper();
            //JsonNode jsonResponse = objectMapper.readTree(connection.getInputStream());
            //return jsonResponse;
        //} //else {
            //throw new Exception("Failed to fetch financial data from XPLAN API. Response code: " + responseCode);
       // }

    //public static void main(String[] args) {
        //try {
            //JsonNode financialData = fetchFinancialData();
            // Process the retrieved financial data as needed
            //System.out.println(financialData);
        } //catch (Exception e) {
           // e.printStackTrace();
      // }
  // }
//}

