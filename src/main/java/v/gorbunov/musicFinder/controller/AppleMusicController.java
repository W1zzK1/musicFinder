package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.AppleMusicService;

@RestController
public class AppleMusicController {
    @Autowired
    private AppleMusicService appleMusicService;

    @GetMapping("/findApple/{name}")
    public ResponseEntity findApple(@PathVariable String name) throws Throwable {
        String music = appleMusicService.findAppleMusic(name);
        return ResponseEntity.ok(appleMusicService.parseAppleMusic(music));
    }
}
