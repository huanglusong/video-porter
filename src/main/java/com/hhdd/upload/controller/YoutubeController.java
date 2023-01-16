package com.hhdd.upload.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @RequestMapping("/test")
    public List<Video> test() throws JsonProcessingException {
        String resp = HttpUtilsPlus.getByProxy("https://youtube.googleapis.com/youtube/v3/videos?part=snippet&id=e6BvdD7c2tg&key=AIzaSyCylY17tDV2Pgk3BCyocojEd9c8nO0bMSo&fields=items(id,snippet(channelId,title,categoryId))"
                , null);
        JsonNode jsonNode = objectMapper.readTree(resp);
        JsonNode items = jsonNode.get("items");
        List<Video> videos = objectMapper.readValue(items.toString(), objectMapper.getTypeFactory().constructCollectionType(List.class, Video.class));
        return videos;
    }

}
