package com.hhdd.upload.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhdd.download.YtdlpDownloader;
import com.hhdd.service.youtube.YoutubeSearchService;
import com.hhdd.service.youtube.YoutubeVideosService;
import com.hhdd.upload.domain.Video;
import com.hhdd.util.HttpUtilsPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author huanghedidi
 * @Date 2023/1/16 22:45
 */
@RestController
public class YoutubeController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private YoutubeSearchService youtubeSearchService;
    @Autowired
    private YoutubeVideosService youtubeVideosService;

    @Autowired
    private YtdlpDownloader ytdlpDownloader;

    @RequestMapping("/test")
    public List<Video> test() throws JsonProcessingException {
        String resp = HttpUtilsPlus.getByProxy("https://youtube.googleapis.com/youtube/v3/videos?part=snippet&id=e6BvdD7c2tg&key=AIzaSyCylY17tDV2Pgk3BCyocojEd9c8nO0bMSo&fields=items(id,snippet(channelId,title,categoryId))"
        );
        JsonNode jsonNode = objectMapper.readTree(resp);
        JsonNode items = jsonNode.get("items");
        List<Video> videos = objectMapper.readValue(items.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Video.class));
        return videos;
    }

    @RequestMapping("/searchYoutube")
    public String searchYoutube(String keyword) {
        return youtubeSearchService.list(keyword);
    }

    @RequestMapping("/listMostPopularVideo")
    public String listMostPopularVideo() throws JsonProcessingException {
        String s = youtubeVideosService.listMostPopularVideo();
        return s;
    }
    @RequestMapping("/downloadMostPopularVideo")
    public String downloadMostPopularVideo() throws JsonProcessingException {
        String s = youtubeVideosService.listMostPopularVideo();
        JsonNode jsonNode = objectMapper.readTree(s);
        List<String> idList = jsonNode.findValuesAsText("id");
        for (String id : idList) {
            boolean flag = ytdlpDownloader.downloadByVideoId(id);
            if (!flag) {
                return "下载失败！！！";
            }
        }
        return "下载成功！！！";
    }


}
