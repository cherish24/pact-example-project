import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DeathEatersConsumerTests {
    private final String MOCK_SERVER_HOST = "localhost";
    private final int MOCK_SERVER_PORT = 9090;

    @Pact(provider = "wizardsProvider", consumer = "deathEatersConsumer")
    public PactFragment createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("name", "He-Who-Must-Not-Be-Named");

        return builder
                .given("Harry Potter is alive") // NOTE: Using provider states are optional, you can leave it out
                .uponReceiving("get harry interaction")
                .path("/harryyy")
                .method("GET")
                .headers(headers)
                .willRespondWith()
                .status(200)
                .body("{\"name\": \"Harry Potter\", \"height\": 1.7, \"weight\": 60.0}")
                .given("Harry Potter is alive")
                .uponReceiving("kill harry interaction")
                .method("POST")
                .path("/killHarry")
                .body("")
                .willRespondWith()
                .status(200)
                .toFragment();
    }

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("wizardsProvider", MOCK_SERVER_HOST, MOCK_SERVER_PORT, this);

    //todo: separate this test to multiple tests with aaa
    @Test
    @PactVerification
    public void deathEatersClient_ValidRequests_OkResponses() throws IOException {
        DeathEatersClient deathEatersClient =
                new DeathEatersClient(String.format("http://%s:%d", MOCK_SERVER_HOST, MOCK_SERVER_PORT));

        //todo: change object[] to response
        Object[] responseResults = deathEatersClient.getHarryPotter();
        int responseCode = (Integer) responseResults[0];
        assertEquals(200, responseCode);

        String body = (String) responseResults[1];
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> actualResponse = mapper.readValue(body, new TypeReference<Map<String, String>>() {
        });
        Map expectedResponse = new LinkedHashMap<String, String>();
        expectedResponse.put("name", "Harry Potter");
        expectedResponse.put("height", "1.7");
        expectedResponse.put("weight", "60.0");
        assertEquals(actualResponse, expectedResponse);

        assertEquals(200, deathEatersClient.killHarryPotter());
    }
}