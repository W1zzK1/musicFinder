package v.gorbunov.musicFinder.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Service
public class FinderService {
    public String findYAMusic(String name) {
        return YA_MUSIC_FIND + name;
    }

    public String findVKMusic(String name) {
        return VK_MUSIC + name;
    }

    public String parserYM(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        String htmlCode = driver.getPageSource();

        Element element = Jsoup.parse(htmlCode).getElementsByClass(YA_MUSIC_DIV_NAME).first();
        String trackLink = "";
        try {
            trackLink = element.select(A).first().attr(HREF);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
        driver.close();
        return YA_MUSIC_HOME + trackLink;
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
