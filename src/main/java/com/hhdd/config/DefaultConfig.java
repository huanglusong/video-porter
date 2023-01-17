package com.hhdd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * 默认配置
 *
 * @Author huanghedidi
 * @Date 2023/1/17 21:47
 */
@Configuration
public class DefaultConfig {
    @Value("${video-porter.proxy.type:http}")
    private String proxyType;
    @Value("${video-porter.proxy.host:localhost}")
    private String proxyHost;
    @Value("${video-porter.proxy.port:7890}")
    private int proxyPort;

    @Bean
    public Proxy defaultProxy() {
        if (Proxy.Type.HTTP.name().equalsIgnoreCase(proxyType)) {
            return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
        } else {
            return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(proxyHost, proxyPort));
        }
    }

}
