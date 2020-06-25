package pers.xuchen.module.web.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.Method;
import lombok.Setter;

public final class HttpPackUtil {
    private static String localUrl = "http://localhost:8080/";
    private static String domain = "localhost";
    private static int port = 8080;
    private static final String exp = "http://{}:{}/";
    private static String header = "Authorization";
    private static String token = null;

    public static void setPort(int port) {
        HttpPackUtil.port = port;
        localUrl = StrUtil.format(exp,domain,port);
    }

    public static void setDomain(String domain) {
        HttpPackUtil.domain = domain;
        localUrl = StrUtil.format(exp,domain,port);
    }

    public static void setHeader(String header){
        HttpPackUtil.header = header;
    }

    public static void setToken(String token){
        HttpPackUtil.token = token;
    }

    public static HttpRequest createRequest(Method method, String url) {
        HttpRequest request = new HttpRequest(localUrl + url).method(method);
        return setToken(request);
    }

    public static HttpRequest createGet(String url) {
        HttpRequest request = HttpRequest.get(localUrl + url);
        return setToken(request);
    }

    public static HttpRequest createPost(String url) {
        HttpRequest request = HttpRequest.post(localUrl + url);
        return setToken(request);
    }

    private static HttpRequest setToken(HttpRequest request){
        if (token != null){
            request.header(header,token);
        }
        return request;
    }
}
