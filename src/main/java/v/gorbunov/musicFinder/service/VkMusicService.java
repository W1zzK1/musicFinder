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
    public Integer parserVK(String url) throws IOException {
        return 0;
    }
}
