package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.AppleMusicService;
import v.gorbunov.musicFinder.service.YandexMusicService;

import java.io.IOException;

@RestController
public class YandexMusicController {

    @Autowired
    private YandexMusicService yandexMusicService;

    @GetMapping("/findYA/{name}")
    public ResponseEntity findMusic(@PathVariable String name){
        String music = yandexMusicService.findYAMusic(name);
        var title = yandexMusicService.parserYM(music);
        System.out.println(title);
        return ResponseEntity.ok("You was looking for : " + title);
    }
}
