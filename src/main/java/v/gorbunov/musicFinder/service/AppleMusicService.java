package v.gorbunov.musicFinder.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Service
public class AppleMusicService {

    public String findAppleMusic(String name) {
        return APPLE_MUSIC_SEARCH + name;
    }

    public String parseAppleMusic(String url) throws IOException {
        Element element = Jsoup.connect(url).get().getElementsByClass(APPLE_MUSIC_DIV_NAME).first();;

        String trackLink = element.select(A).first().attr(HREF);

        return trackLink;
    }

}
