package v.gorbunov.musicFinder.service;

import core.GLA;
import genius.LyricsParser;
import genius.SongSearch;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.GENIUS_API_TOKEN;
import static v.gorbunov.musicFinder.storage.ConstantsStorage.GENIUS_SEARCH_LINK;

@Slf4j
@Service
public class SearchTextService {
    private static final GLA gla = new GLA();

    private String getTrackId(String trackName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer " + GENIUS_API_TOKEN)
                .uri(URI.create(GENIUS_SEARCH_LINK + trackName.replace(" ", "+")))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());

        String s = jsonObject.getJSONObject("response")
                .getJSONArray("hits")
                .getJSONObject(0)
                .getJSONObject("result")
                .getString("api_path");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }

    public String getTrackLyrics(String trackName) throws IOException, InterruptedException {
//        SongSearch songId = gla.search(trackName);

        LyricsParser lyricsParser = new LyricsParser(gla);

        return lyricsParser.get(getTrackId(trackName));
    }
}
