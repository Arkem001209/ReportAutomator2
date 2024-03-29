import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Base64;



public class FinancialDataRetriever {
    private static final String XPLAN_API_URL = "https://londonwall.xplan.iress.co.uk/resourceful/";
    private static final String API_KEY = "pGQsWd3FYdyJOnyno5Ng";
    private static final String USERNAME = "APIUser";
    private static final String PASSWORD = "WelcomeBack!1";

    private static String getBasicAuthenticationHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(valueToEncode.getBytes());
    }
    static JsonNode fetchFinancialData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;


    try {
        request = HttpRequest.newBuilder()
                .GET()
                .uri(new URI(XPLAN_API_URL))
                .header("Authorization", getBasicAuthenticationHeader(USERNAME, PASSWORD))
                .header("X-Xplan-App-id", API_KEY)
                .header("X-Username", USERNAME)
                .header("X-Password", PASSWORD)
                .header("Cookie", "dummyCookie=1")
                .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    // Add headers for authorization
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());


    int responseCode = response.statusCode();
    String reasonPhrase = response.body();
        System.out.println(responseCode);
        System.out.println(reasonPhrase);

    if (responseCode == 200) {
        String jsonResponse = response.body();
        ObjectMapper objectMapper = new ObjectMapper();
        //JsonNode jsonResponse = objectMapper.readTree(response.getInputStream());
        return objectMapper.readTree(jsonResponse);

    } else {
        throw new Exception("Failed to fetch financial data from XPLAN");
        }
    }
}


