package v.gorbunov.musicFinder.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import v.gorbunov.musicFinder.dto.TrackDto;
import v.gorbunov.musicFinder.dto.enums.ProviderEnum;

import java.util.Objects;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Service
public class YandexMusicService {
    private String findYAMusic(String name) {
        return YA_MUSIC_FIND + name;
    }


    public TrackDto parserYM(String name) {
        String url = findYAMusic(name);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        WebDriver driver = new ChromeDriver();
        driver.get(url);
        String htmlCode = driver.getPageSource();

        Element element = Jsoup.parse(htmlCode).getElementsByClass(YA_MUSIC_DIV_NAME).first();
        String trackLink = "";
        try {
            assert element != null : "Element wasn't found or it was null";
            trackLink = Objects.requireNonNull(element.select(A).first()).attr(HREF);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        driver.close();
        return new TrackDto(ProviderEnum.YA_MUSIC, YA_MUSIC_HOME + trackLink);
    }

}
