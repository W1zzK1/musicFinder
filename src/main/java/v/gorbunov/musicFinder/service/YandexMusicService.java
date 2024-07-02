package v.gorbunov.musicFinder.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import v.gorbunov.musicFinder.dto.TrackDto;
import v.gorbunov.musicFinder.dto.enums.ProviderEnum;

import static v.gorbunov.musicFinder.storage.ConstantsStorage.*;

@Slf4j
@Service
public class YandexMusicService {
    private String findYAMusic(String name) {
        return YA_MUSIC_FIND + name;
    }

    private void pressX(WebDriver driver) {
        WebElement clickable = driver.findElement(By.xpath(YA_MUSIC_KREST_NAME));
        clickable.click();
    }

    public TrackDto parserYM(String name) throws InterruptedException {
        String url = findYAMusic(name);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");

        WebDriver driver = new ChromeDriver(options);
        driver.get(url);

//        WebDriverWait webDriverWait = new WebDriverWait(driver, );

        pressX(driver);

        String htmlCode = driver.getPageSource();

        Element element = Jsoup.parse(htmlCode).getElementsByClass(YA_MUSIC_DIV_NAME).first();
        String trackLink = "";
        if (element != null) {
            Element aElement = element.select(A).first();
            if (aElement != null) {
                trackLink = aElement.attr(HREF);
            } else {
                throw new RuntimeException("Failed to find the link");
            }
        } else {
            throw new RuntimeException("Element wasn't found or it was null");
        }
        driver.quit();
        return new TrackDto(ProviderEnum.YA_MUSIC, YA_MUSIC_HOME + trackLink);
    }

}
