package v.gorbunov.musicFinder.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.VK_MUSIC;

@Service
public class VkMusicService {

    public String findVKMusic(String name) {
        return VK_MUSIC + name;
    }
    public Element parserVK(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
                .execute();

        Document doc = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
                .cookies(response.cookies())
                .get();

        Element trackBox = doc.getElementsByClass("audio_row__title _audio_row__title").first();
        return trackBox;
    }
}
