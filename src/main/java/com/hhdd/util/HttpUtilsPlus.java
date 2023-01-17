package com.hhdd.util;

import cn.hutool.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Proxy;
import java.util.Map;

/**
 * @Author huanghedidi
 * @Date 2023/1/16 22:55
 */
@Component
public class HttpUtilsPlus {

    private static Proxy proxy;



    @Autowired
    public void setProxy(Proxy proxy) {
        HttpUtilsPlus.proxy = proxy;
    }

    public static String getByProxy(String url) {
        return HttpRequest.get(url).timeout(-1).setProxy(proxy).execute().body();
    }

    public static String getByProxy(String urlString, Map<String, Object> paramMap) {
        return HttpRequest.get(urlString).timeout(-1).setProxy(proxy).form(paramMap).execute().body();
    }
}
