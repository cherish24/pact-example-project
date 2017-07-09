import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeathEatersClient {

    private String baseUrl;

    public DeathEatersClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Object[] getHarryPotter() throws IOException {
        URL requestUrl = new URL(baseUrl + "/harryyy");
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("name", "He-Who-Must-Not-Be-Named");
        connection.connect();

        int code = connection.getResponseCode();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();

        String output;
        while ((output = bufferedReader.readLine()) != null) {
            stringBuilder.append(output);
        }

        String body = stringBuilder.toString();

        return new Object[]{code, body};
    }

    public int killHarryPotter() throws IOException {
        URL requestUrl = new URL(baseUrl + "/killHarry");
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        connection.setRequestMethod("POST");
        connection.connect();
        return connection.getResponseCode();
    }


}
