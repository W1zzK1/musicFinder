package v.gorbunov.musicFinder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import v.gorbunov.musicFinder.service.SearchTextService;

import java.io.IOException;

@RestController
public class SongTextController {
    @Autowired
    private SearchTextService searchTextService;

    @GetMapping("/findText/{name}")
    public ResponseEntity findText(@PathVariable String name) throws IOException {
        String trackUrl = searchTextService.textRequest(name);
        return ResponseEntity.ok(searchTextService.parseText("https://genius.com/search?q=%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%20%D0%BF%D0%BE%D0%B4%20%D0%BF%D0%BE%D0%B4%D0%BE%D1%88%D0%B2%D0%BE%D0%B9"));
    }
}
