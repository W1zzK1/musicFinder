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

    @GetMapping("/findYA/{name}")
    public ResponseEntity findMusic(@PathVariable String name) throws IOException {
        String music = finderService.findYAMusic(name);
        var title = finderService.parserYM(music);
        System.out.println(title);
        return ResponseEntity.ok("You was looking for : " + title);
    }

    @GetMapping("/findVK/{name}")
    public ResponseEntity findVK(@PathVariable String name) throws IOException{
        String music = finderService.findVKMusic(name);
        return ResponseEntity.ok(finderService.parserVK(music));
    }
}
