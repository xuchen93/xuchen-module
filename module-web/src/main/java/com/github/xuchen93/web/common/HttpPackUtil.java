package com.github.xuchen93.web.common;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.github.xuchen93.web.common.model.PackHttpRequest;
import lombok.Data;

@Data
public final class HttpPackUtil {
	public static String localUrl = "http://localhost:8080/";
	public static String header = "Authorization";
	public static String token = null;

	public static HttpRequest createRequest(Method method, String url) {
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		HttpRequest request = new PackHttpRequest(localUrl + url).method(method);
		return setToken(request);
	}

	public static HttpRequest createGet(String url) {
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		HttpRequest request = new PackHttpRequest(localUrl + url).method(Method.GET);
		return setToken(request);
	}

	public static HttpRequest createPost(String url) {
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		HttpRequest request = new PackHttpRequest(localUrl + url).method(Method.POST);
		return setToken(request);
	}

	public static String hello() {
		HttpResponse response = createGet("xuchen/hello").execute();
		return response.body();
	}

	private static HttpRequest setToken(HttpRequest request) {
		if (token != null) {
			request.header(header, token);
		}
		return request;
	}
}
