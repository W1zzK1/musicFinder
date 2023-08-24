package v.gorbunov.musicFinder.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Service
public class SpotifyService {
    SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(SPOTIFY_CLIENT_ID)
            .setClientSecret(SPOTIFY_CLIENT_SECRET)
            .setAccessToken(SPOTIFY_ACCESS_TOKEN)
            .build();

    public String getTrackByName(String trackName) {
        return spotifyApi.searchTracks(trackName).toString();
    }
}
