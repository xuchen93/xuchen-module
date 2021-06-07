package com.github.xuchen93.web.common;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.github.xuchen93.web.common.model.PackHttpRequest;
import lombok.Data;

import java.net.HttpCookie;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public final class HttpPackUtil {
	public static String localUrl = "http://localhost:8080/";
	public static String header = "Authorization";
	public static String token = null;
	public static List<HttpCookie> cookieList = new LinkedList<>();
	public static Map<String, String> headerMap = new LinkedHashMap<>();

	public static HttpRequest createRequest(Method method, String url) {
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		HttpRequest request = new PackHttpRequest(localUrl + url).method(method);
		return doCustomer(request);
	}

	public static HttpRequest createGet(String url) {
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		HttpRequest request = new PackHttpRequest(localUrl + url).method(Method.GET);
		return doCustomer(request);
	}

	public static HttpRequest createPost(String url) {
		if (url.startsWith("/")) {
			url = url.substring(1);
		}
		HttpRequest request = new PackHttpRequest(localUrl + url).method(Method.POST);
		return doCustomer(request);
	}

	public static String hello() {
		HttpResponse response = createGet("xuchen/hello").execute();
		return response.body();
	}

	public static HttpRequest doCustomer(HttpRequest request) {
		setToken(request);
		setCookie(request);
		setHeader(request);
		return request;
	}

	private static void setCookie(HttpRequest request) {
		if (cookieList.size() > 0) {
			request.cookie(ArrayUtil.toArray(cookieList, HttpCookie.class));
		}
	}

	private static void setToken(HttpRequest request) {
		if (token != null) {
			request.header(header, token);
		}
	}

	private static void setHeader(HttpRequest request) {
		if (headerMap.size() > 0) {
			request.addHeaders(headerMap);
		}
	}
}
