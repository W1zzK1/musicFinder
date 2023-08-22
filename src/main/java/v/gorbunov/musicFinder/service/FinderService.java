package v.gorbunov.musicFinder.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class FinderService {

    public static final String YA_MUSIC = "https://music.yandex.ru/search?text=";

    public String findMusic(String name) {
        return YA_MUSIC + name;
    }

    public Element parser(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        Element trackBox = doc.getElementsByClass("d-track__name").first();
        return trackBox;
    }
}
