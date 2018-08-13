package com.potevio.xacp.api.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Description: Reedis操作工具类
 * Author: peng.zhang
 * Date: 2018/8/8  18:02
 */
public final class RedisUtil {

    @Autowired
    private static RedisTemplate<String, Object> redisTemplate;

    /**
     * 判断key是否存在
     * @param key 键
     * @return 存在返回true，不存在返回false
     */
    public static boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key删除缓存
     * @param key 键
     */
    @SuppressWarnings("unchecked")
    public static boolean delete(String... key) {
        try {
            if (key != null && key.length > 0) {
                if (key.length == 1) {
                    redisTemplate.delete(key[0]);
                } else {
                    redisTemplate.delete(CollectionUtils.arrayToList(key));
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取缓存
     * @param key 键
     * @return 与key对应的内容
     */
    public static Object get(String key) {
        try {
            return key == null ? null : redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加缓存内容
     * @param key 键
     * @param value 值
     */
    public static boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 查询hash表中是否有该项的值
     * @param key 键
     * @param item 项
     * @return 如果存在，返回true，不存在返回false
     */
    public static boolean hHasKey(String key, String item) {
        try {
            return redisTemplate.opsForHash().hasKey(key, item);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中添加数据，如果不存在就创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return 如果成功，返回true，否则返回false
     */
    public static boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key查询hash表的所有列的键
     * @param key 键
     * @return 键的集合
     */
    public static Set<Object> hkeys(String key) {
        try {
            return redisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除hash表中的值
     * @param key 键
     * @param items 项
     * @return 删除成功，返回true，否则返回false
     */
    public static boolean hdel(String key, String... items) {
        try {
            redisTemplate.opsForHash().delete(key, items);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
