import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class FinancialDataRetriever {
    private static final String XPLAN_API_URL = "https://londonwall.xplan.iress.co.uk/resourceful/entity/client-v4";
//To do add correct URL, make sure that it works with 2FA

    public static JsonNode fetchFinancialData() throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(XPLAN_API_URL);

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

