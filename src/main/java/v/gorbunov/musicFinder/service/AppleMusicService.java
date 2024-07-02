package v.gorbunov.musicFinder.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import v.gorbunov.musicFinder.dto.TrackDto;
import v.gorbunov.musicFinder.dto.enums.ProviderEnum;

import java.io.IOException;
import java.util.Objects;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Slf4j
@Service
public class AppleMusicService {

    private String findAppleMusic(String name) {
        return APPLE_MUSIC_SEARCH + name;
    }

    private Element findFirstElementByClass(String url, String elementClass) throws IOException {
        return Jsoup.connect(url).get().getElementsByClass(elementClass).first();
    }

    public TrackDto parseAppleMusic(String name) throws Throwable {
        String url = findAppleMusic(name);
        Element element = findFirstElementByClass(url, APPLE_MUSIC_CLASS_NAME);
        String trackLink = "";
        try{
//            assert element != null : "Element wasn't found or it was null";
            Element aElement = element.select(A).first();
            trackLink = aElement.attr(HREF);
        } catch (NullPointerException e){
            //throw new Throwable("обшибка нахуй, на том кто это писал");
            throw new Throwable("F*cking mistake, on the one who wrote this.");
        }

        return new TrackDto(ProviderEnum.APPLE_MUSIC, trackLink);
    }

    public TrackDto findPhotoLink(String name) throws Throwable{
        String url = findAppleMusic(name);
        Element element = findFirstElementByClass(url, APPLE_MUSIC_PHOTO_CLASS_NAME);
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
