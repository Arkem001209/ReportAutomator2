import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class FinancialDataRetriever {
    private static final String XPLAN_API_URL = "https://your-xplan-api-url/financial-data-endpoint";
    private static final String API_KEY = "pGQsWd3FYdyJOnyno5Ng";
    private static final String USERNAME = "APIUser";
    private static final String PASSWORD = "WelcomeBack!1";

//To do add correct URL, make sure that it works with 2FA

    public static JsonNode fetchFinancialData() throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(XPLAN_API_URL);

        // Add headers for authorization
        request.addHeader("Authorization", "Basic " + API_KEY);
        request.addHeader("X-Username", USERNAME);
        request.addHeader("X-Password", PASSWORD);

        HttpResponse response = httpClient.execute(request);

        if (response.getStatusLine().getStatusCode() == 200) {
            String jsonResponse = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(jsonResponse);
        } else {
            throw new Exception("Failed to fetch financial data from XPLAN API.");
        }
    }
}

