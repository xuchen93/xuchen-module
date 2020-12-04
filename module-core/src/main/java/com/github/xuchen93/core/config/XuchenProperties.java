package com.github.xuchen93.core.config;

import lombok.Data;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
@ConfigurationProperties(prefix = "xuchen.module")
public class XuchenProperties {
    private RequestModel request = new RequestModel();
    private RedisModel redis = new RedisModel();
    private JwtModel jwt = new JwtModel();

    @Data
    public static class RequestModel{
        /**
         * 校验token信息
         */
        private boolean checkToken = true;
        /**
         * 请求日志
         */
        private boolean log = false;
        /**
         * 请求参数校验，需依赖spring-boot-starter-validation
         */
        private boolean valid = false;
        /**
         * 请求详情
         */
        private boolean detail = false;
        /**
         * redis限流
         */
        private boolean limit = false;
    }

    @Data
    public static class RedisModel{
        /**
         * key值的前缀
         */
        private String prefix;
    }

    @Data
    public static class JwtModel{
        private String tokenKey = "Authorization";

        private String secret = "defaultSecret";
        /**
         * token过期时长
         */
        private int expiresMin = 120;
    }

    @PostConstruct
    public void init(){
        String banner = "\n" +
                "                 _                                          _       _      \n" +
                "__  ___   _  ___| |__   ___ _ __        _ __ ___   ___   __| |_   _| | ___ \n" +
                "\\ \\/ / | | |/ __| '_ \\ / _ \\ '_ \\ _____| '_ ` _ \\ / _ \\ / _` | | | | |/ _ \\\n" +
                " >  <| |_| | (__| | | |  __/ | | |_____| | | | | | (_) | (_| | |_| | |  __/\n" +
                "/_/\\_\\\\__,_|\\___|_| |_|\\___|_| |_|     |_| |_| |_|\\___/ \\__,_|\\__,_|_|\\___|\n" +
                "                                                                           \n";
        System.out.println(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, banner));
        System.out.print(AnsiOutput.toString(AnsiColor.BLUE, "project version:\t\t"));
        System.out.println(AnsiOutput.toString(AnsiColor.GREEN, XuchenModuleVersion.getVersion()));
    }
}
