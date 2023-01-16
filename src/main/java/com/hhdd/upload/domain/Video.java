package com.hhdd.upload.domain;

import lombok.Data;

/**
 * @Author huanghedidi
 * @Date 2023/1/16 23:17
 */
@Data
public class Video {
    private String id;
    private Snippet snippet;

    @Data
    class Snippet {
        private String channelId;
        private String title;
        private String categoryId;
    }
}
