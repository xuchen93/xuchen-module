package com.github.xuchen93.core.util;

import cn.hutool.core.util.StrUtil;
import com.github.xuchen93.core.config.XuchenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@ConditionalOnBean(RedisTemplate.class)
@SuppressWarnings("all")
public class RedisStore {
	private static RedisTemplate<String, Object> redisTemplate;
	private static String prefix = "";

	@Autowired
	XuchenProperties xuchenProperties;

	@Autowired
	RedisTemplate<String, Object> redisTemplateBean;

	public static Set<String> getKeys(String key) {
		return redisTemplate.keys(prefix + key);
	}

	public static void setValue(String key, Object value) {
		redisTemplate.opsForValue().set(prefix + key, value);
	}

	public static void setValue(String key, Object value, long timeout, TimeUnit timeType) {
		redisTemplate.opsForValue().set(prefix + key, value, timeout, timeType);
	}

	public static <T> T getValue(String key) {
		return (T) redisTemplate.opsForValue().get(key);
	}

	public static void delKey(String key) {
		redisTemplate.delete(prefix + key);
	}

	public static long getExpire(String key) {
		return redisTemplate.getExpire(prefix + key);
	}

	public static void delAllKey(String key) {
		Set<String> keys = redisTemplate.keys(prefix + key + "*");
		redisTemplate.delete(keys);
	}

	public static void expire(String key, long timeout, TimeUnit timeType) {
		redisTemplate.expire(prefix + key, timeout, timeType);
	}

	public static boolean hasKey(String key) {
		return redisTemplate.hasKey(prefix + key).booleanValue();
	}

	@PostConstruct
	public void init() {
		RedisStore.redisTemplate = redisTemplateBean;
		if (StrUtil.isNotBlank(xuchenProperties.getRedis().getPrefix())) {
			RedisStore.prefix = xuchenProperties.getRedis().getPrefix() + ":";
		}
		log.info("【xuchen-module-core】初始化【redisStore】");
	}
}
