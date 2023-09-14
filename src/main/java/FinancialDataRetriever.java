import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
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
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec("ignore")
                .build();

        HttpClient httpClient;
        httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();



        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(XPLAN_API_URL))
                .header("Authorization", getBasicAuthenticationHeader(USERNAME, PASSWORD))
                .build();

        HttpResponse response;
        response = httpClient.execute((HttpUriRequest) request);

        if (response.getStatusLine().getStatusCode() == 200) {
            String jsonResponse = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(jsonResponse);
        } else {
            throw new Exception("Failed to fetch financial data from XPLAN API.");
        }
    }
}

