package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.SpotifyService;

@RestController
public class SpotifyController {
    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/findSpotify/{name}")
    public ResponseEntity findMusic(@PathVariable String name){
        String music = spotifyService.getTrackByName(name);
        return ResponseEntity.ok("You was looking for : " + music);
    }
}
