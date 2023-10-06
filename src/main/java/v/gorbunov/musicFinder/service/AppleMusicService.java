package v.gorbunov.musicFinder.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import v.gorbunov.musicFinder.dto.TrackDto;
import v.gorbunov.musicFinder.dto.enums.ProviderEnum;

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

        return new TrackDto(ProviderEnum.APPLE_MUSIC, trackLink);
    }

    public TrackDto findPhotoLink(String url) throws Throwable{
        Element element = Jsoup.connect(url).get().getElementsByClass(APPLE_MUSIC_PHOTO_CLASS_NAME).first();
        String photoLink = "";
        try{
            assert element != null : "Element wasn't found or it was null";
            photoLink = Objects.requireNonNull(element.select(SOURCE).first()).attr(SRCSET);
        } catch (NullPointerException e){
            throw new Throwable("обшибка нахуй, на том кто это писал");
        }

        return new TrackDto(ProviderEnum.PHOTO_LINK, photoLink.substring(0, photoLink.indexOf(" ")));
    }

}
