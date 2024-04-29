package hello.moc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@SpringBootApplication
public class MocApplication {


    public static void main(String[] args) {
        log.info("start");

        try {
            String server              = "bakal";
            String characterId         = "da3b06816b4f383e24a7e4c664c0559a";
            String characterName       = "밀헌";
            String characterNameEncode = URLEncoder.encode(characterName, StandardCharsets.UTF_8);
            String API_KEY             = "2nUx01AbJMxc9thjpsVUvrx7zKnaXL29";
            String urlString           = getUrl(server, characterId, characterNameEncode, API_KEY);

            URL url = new URL(urlString);

            // HTTP connection 설정
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // connection.setRequestProperty("x-nxopen-api-key", API_KEY);

            int responseCode = connection.getResponseCode();

            BufferedReader in;
            if (responseCode == 200) {
                // responseCode 200 정상응답
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                // responseCode 200 이외의 코드가 반환되었을 경우
                in = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String       inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            log.info(response.toString());
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    public static String getUrl(String server, String characterId, String characterNameEncode, String API_KEY) {
        String url = "https://api.neople.co.kr/df/servers/" +
                server +
                "/characters/" +
                characterId +
                "/equip/equipment?apikey=" +
                API_KEY;


        return url;
    }

    public static String encodeURIComponent(String component) {
        String result = null;
        try {
            result = URLEncoder.encode(component, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            result = component;
        }
        return result;
    }

}
