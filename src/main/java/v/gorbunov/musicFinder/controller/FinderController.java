package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.FinderService;

import java.io.IOException;

@RestController
public class FinderController {

    @Autowired
    private FinderService finderService;

    @GetMapping("/find/{name}")
    public ResponseEntity findMusic(@PathVariable String name) throws IOException {
        String music = finderService.findMusic(name);
        var title = finderService.parser(music);
        return ResponseEntity.ok(title);
    }
}
