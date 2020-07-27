package com.github.xuchen93.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.github.xuchen93.web.handler.RequestHandler;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestHandler())
                .addPathPatterns("/**")
                .excludePathPatterns("/xuchen/hello","/login");
    }
}

