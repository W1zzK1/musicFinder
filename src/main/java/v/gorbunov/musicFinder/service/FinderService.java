package v.gorbunov.musicFinder.service;

import org.springframework.stereotype.Service;

@Service
public class FinderService {

//    search?text=город%20по%20подошевой

    public static final  String YA_MUSIC = "https://music.yandex.ru/search?text=";

    public String findMusic(String name){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(YA_MUSIC).append(name);
        return stringBuilder.toString();
    }
}
