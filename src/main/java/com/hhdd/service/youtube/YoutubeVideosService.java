package com.hhdd.service.youtube;

import com.hhdd.core.constants.YoutubeConstants;
import com.hhdd.util.HttpUtilsPlus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * youtube 视频api 服务实现类
 * @Author huanghedidi
 * @Date 2023/1/17 22:39
 */
@Service
public class YoutubeVideosService {
    @Value("${video-porter.youtube.api-key}")
    private String apiKey;
    public String listMostPopularVideo(){
        Map<String, Object> params = new HashMap<>(101);
        params.put("key", apiKey);
        params.put("part", "snippet");
        params.put("chart", "mostPopular");
        params.put("fields", "items(id,snippet(publishedAt,channelId,title,description))");
        return HttpUtilsPlus.getByProxy(YoutubeConstants.VIDEOS_LIST_API_URL, params);
    }


}
