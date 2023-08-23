package v.gorbunov.musicFinder.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import javax.sound.midi.Track;

import java.io.IOException;
import java.text.ParseException;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.SPOTIFY_MUSIC_ACCESS_TOKEN;

@Service
public class SpotifyService {
    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(SPOTIFY_MUSIC_ACCESS_TOKEN)
            .build();

//    public static void searchTracks_Sync(String trackName) {
//
//        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(trackName).
//                .build();
//        try {
//            final Paging<Track> trackPaging = spotifyApi.execute;
//
//            System.out.println("Total: " + trackPaging.getTotal());
//        } catch (IOException | SpotifyWebApiException | ParseException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
}
