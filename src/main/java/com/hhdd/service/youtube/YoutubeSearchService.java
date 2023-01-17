package com.hhdd.service.youtube;

import com.hhdd.core.constants.YoutubeConstants;
import com.hhdd.util.HttpUtilsPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * youtube 搜索服务
 *
 * @Author huanghedidi
 * @Date 2023/1/17 22:04
 */
@Service
@Slf4j
public class YoutubeSearchService {

    @Value("${video-porter.youtube.api-key}")
    private String apiKey;

    public String list(String keyword) {
        Map<String, Object> params = new HashMap<>(101);
        params.put("key", apiKey);
        params.put("part", "snippet");
        params.put("fields", "items(id(videoId),snippet(channelId,title))");
        params.put("q", keyword);
        return HttpUtilsPlus.getByProxy(YoutubeConstants.SEARCH_LIST_API_URL, params);
    }


}
