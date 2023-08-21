package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.FinderService;

@RestController
public class FinderController {

    @Autowired
    private FinderService finderService;

    @GetMapping("/find/{name}")
    public ResponseEntity findMusic(@PathVariable String name) {
        String music = finderService.findMusic(name);
        return ResponseEntity.ok(music);
    }
}
