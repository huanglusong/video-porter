package com.hhdd.download.controller;

import com.hhdd.download.YtdlpDownloader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author huanghedidi
 * @Date 2023/1/15 23:31
 */
@RestController
@RequestMapping("/ytdlp")
public class YtdlpController {

    @Autowired
    private YtdlpDownloader ytdlpDownloader;
    @RequestMapping("/download")
    public String download(String url) {
        boolean res = ytdlpDownloader.download(url);
        return res ? "成功" : "失败";
    }


}
