package v.gorbunov.musicFinder.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

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

    public String getTrackByName(String trackName) throws Exception {
        URL urlObj = new URL("https://api.spotify.com/v1/search?q=" + trackName.replace(" ", "+") + "&type=track");
        HttpURLConnection connection = (HttpURLConnection) urlObj.openConnection();
        connection.setRequestMethod("GET");
//        connection.setRequestProperty("q", trackName);
//        connection.setRequestProperty("type", "track");
        connection.setRequestProperty("Authorization", "Bearer " + getAccessToken());

        int responseCode = connection.getResponseCode();
        StringBuilder sb = new StringBuilder();

        if (responseCode == HttpURLConnection.HTTP_OK){
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNext()){
                sb.append(scanner.nextLine());
            }
        } else {
            log.info("response code - " + responseCode);
        }
        return sb.toString();
    }
}
