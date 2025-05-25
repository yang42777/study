package com.example.ecommerce.utils;

import com.example.ecommerce.config.SpringContext;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    private static final StringRedisTemplate redisTemplate = SpringContext.getBean(StringRedisTemplate.class);

    public static void set(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, String.valueOf(value), seconds, TimeUnit.SECONDS);
    }

    public static String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 尝试获取锁
     * @param lockKey 锁的key
     * @param requestId 请求标识（防止误删别人的锁）
     * @param expireTime 锁过期时间，单位：秒
     * @return 是否获取成功
     */
    public static boolean tryLock(String lockKey, String requestId, long expireTime) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        Boolean success = ops.setIfAbsent(lockKey, requestId, expireTime, TimeUnit.SECONDS);
        return success != null && success;
    }

    /**
     * 释放锁（使用Lua脚本保证原子操作）
     * @param lockKey 锁的key
     * @param requestId 请求标识（只有持有锁的客户端才能释放）
     * @return 是否释放成功
     */
    public static boolean releaseLock(String lockKey, String requestId) {
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                "return redis.call('del', KEYS[1]) else return 0 end";

        RedisCallback<Long> callback = connection -> {
            Object result = connection.eval(
                    luaScript.getBytes(StandardCharsets.UTF_8),
                    ReturnType.INTEGER,
                    1,
                    lockKey.getBytes(StandardCharsets.UTF_8),
                    requestId.getBytes(StandardCharsets.UTF_8)
            );
            return (Long) result;
        };

        Long released = redisTemplate.execute(callback);
        return released != null && released > 0;
    }
}
