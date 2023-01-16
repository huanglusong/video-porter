package com.hhdd.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @Author huanghedidi
 * @Date 2023/1/16 22:55
 */
public class HttpUtilsPlus {

    public static String getByProxy(String url, Proxy proxy) {
        if (proxy == null) {
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("localhost", 7890));
        }
        return HttpRequest.get(url).timeout(-1).setProxy(proxy).execute().body();
    }
}
