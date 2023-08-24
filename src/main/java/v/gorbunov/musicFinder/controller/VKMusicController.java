package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.VkMusicService;
import v.gorbunov.musicFinder.service.YandexMusicService;

import java.io.IOException;

@RestController
public class VKMusicController {

    @Autowired
    private VkMusicService vkMusicService;
    @GetMapping("/findVK/{name}")
    public ResponseEntity findVK(@PathVariable String name) throws IOException {
        String music = vkMusicService.findVKMusic(name);
        return ResponseEntity.ok(vkMusicService.parserVK(music));
    }
}
