package v.gorbunov.musicFinder.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import v.gorbunov.musicFinder.dto.TrackDto;

import java.io.IOException;
import java.util.Objects;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Service
public class AppleMusicService {

    public String findAppleMusic(String name) {
        return APPLE_MUSIC_SEARCH + name;
    }

    public TrackDto parseAppleMusic(String url) throws Throwable {
        Element element = Jsoup.connect(url).get().getElementsByClass(APPLE_MUSIC_DIV_NAME).first();
        String trackLink = "";
        try{
            assert element != null : "Element wasn't found or it was null";
            trackLink = Objects.requireNonNull(element.select(A).first()).attr(HREF);
        } catch (NullPointerException e){
            throw new Throwable("обшибка нахуй, на том кто это писал");
        }

        return new TrackDto("Apple Music", trackLink);
    }

}
