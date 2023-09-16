package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.SearchTextService;

import java.io.IOException;

@RestController
public class SongTextController {
    @Autowired
    private SearchTextService searchTextService;

    @GetMapping("/findText/{name}")
    public ResponseEntity findText(@PathVariable String name) throws IOException, InterruptedException {
        String trackLyrics = searchTextService.getTrackLyrics(name);
        return ResponseEntity.ok(trackLyrics);
    }
}
