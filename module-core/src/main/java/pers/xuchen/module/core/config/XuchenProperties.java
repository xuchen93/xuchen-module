package pers.xuchen.module.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

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
}
