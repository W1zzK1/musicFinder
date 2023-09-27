package v.gorbunov.musicFinder.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import v.gorbunov.musicFinder.dto.TrackDto;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Slf4j
@Service
public class SpotifyService {
    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(SPOTIFY_CLIENT_ID)
            .setClientSecret(SPOTIFY_CLIENT_SECRET)
            .build();

    private String getAccessToken() throws Exception {
        URL url = new URL(SPOTIFY_TOKEN_LINK);
        String encodedCredentials = Base64.getEncoder().encodeToString((SPOTIFY_CLIENT_ID + ":" + SPOTIFY_CLIENT_SECRET).getBytes());

        //open http connection to url
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);

        //setup post function and request headers
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization",String.format("Basic %s", encodedCredentials));
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        //set body for posting
        String body = "grant_type=client_credentials";

        //calculate and set content length
        byte[] out = body.getBytes(StandardCharsets.UTF_8);
        int length = out.length;
        conn.setFixedLengthStreamingMode(length);

        //connect to http
        conn.connect();
        //}

        //send bytes to spotify
        try(OutputStream os = conn.getOutputStream()) {
            os.write(out);
        }

        //receive access token
        InputStream result = conn.getInputStream();


        JSONObject json = new JSONObject(new String(result.readAllBytes()));
        return json.getString("access_token");
    }

    public TrackDto getTrackByName(String trackName) throws Exception {
        String regex = "[а-яёА-ЯЁ]+";

        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(trackName);
        if (m.find()){
            trackName = URLEncoder.encode(trackName, StandardCharsets.UTF_8);
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization", "Bearer " + getAccessToken())
                .uri(URI.create("https://api.spotify.com/v1/search?q=" + trackName.replace(" ", "+") + "&type=track"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());

        String trackLink = jsonObject.getJSONObject("tracks")
                .getJSONArray("items")
                .getJSONObject(0)
                .getJSONObject("external_urls")
                .getString("spotify");

        return new TrackDto("Spotify", trackLink);
    }
}
