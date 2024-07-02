package v.gorbunov.musicFinder.controller;

import org.openqa.selenium.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.dto.TrackDto;
import v.gorbunov.musicFinder.dto.enums.ProviderEnum;
import v.gorbunov.musicFinder.service.AppleMusicService;
import v.gorbunov.musicFinder.service.SearchTextService;
import v.gorbunov.musicFinder.service.SpotifyService;
import v.gorbunov.musicFinder.service.YandexMusicService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AllTrackController {
    @Autowired
    private AppleMusicService appleMusicService;
    @Autowired
    private YandexMusicService yandexMusicService;
    @Autowired
    private SpotifyService spotifyService;
    @Autowired
    private SearchTextService searchTextService;

    @GetMapping("/findAll/{name}")
    public ResponseEntity findMusic(@PathVariable String name) throws Throwable {
        List<TrackDto> allTracks = new ArrayList<>();
        try {
            allTracks.add(yandexMusicService.parserYM(name));
        } catch (NoSuchElementException exception){
            allTracks.add(new TrackDto(ProviderEnum.YA_MUSIC, "ХУЙ ТЕБЕ ПИДОРАСИНА, ТЫ БОТЯРА. Янжекс таких не ценит"));
        }
        allTracks.add(appleMusicService.parseAppleMusic(name));
        allTracks.add(spotifyService.getTrackByName(name));
//        allTracks.add(appleMusicService.findPhotoLink(name));
//        allTracks.add(searchTextService.getTrackLyrics(name));
        return ResponseEntity.ok(allTracks);
    }
}
