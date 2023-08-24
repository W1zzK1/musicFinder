package v.gorbunov.musicFinder.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.GENIUS_SEARCH_LINK;

@Service
public class SearchTextService {

    public String textRequest (String name){
        return GENIUS_SEARCH_LINK + name;
    }

    public Element parseText(String url) throws IOException {
        Element element = Jsoup.connect(url).get();
        return element;
    }
}
