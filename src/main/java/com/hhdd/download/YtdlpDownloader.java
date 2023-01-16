package com.hhdd.download;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * yt dlp 下载器
 *
 * @Author huanghedidi
 * @Date 2023/1/15 23:23
 */
@Component
@Slf4j
public class YtdlpDownloader {
    /**
     * ytdlp可执行程序的全路径
     */
    @Value("${download.ytdlp.binPath}")
    private String ytdlpFullPath;

    @Value(("${download.ytdlp.downloadPath}"))
    private String ytdlpDownloadPath;

    public boolean downloadByUrl(String url) {
        return download(url);
    }
    public boolean downloadByVideoId(String videoId) {
        return download(videoId);
    }

    public boolean download(String... params) {
        // 固定参数
        ArrayList<String> paramList = new ArrayList<>(Arrays.asList(
                ytdlpFullPath,
                "-P",
                ytdlpDownloadPath
        ));
        for (String param : params) {
            paramList.add(param);
        }
        ProcessBuilder processBuilder = new ProcessBuilder(paramList).redirectErrorStream(true);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "gbk"));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }
            return true;
        } catch (IOException e) {
            log.error("下载出错", e);
            return false;
        }
    }

}
