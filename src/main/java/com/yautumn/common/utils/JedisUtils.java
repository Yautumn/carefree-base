package com.yautumn.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class JedisUtils {
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;


    public boolean expire(String key, long time){
        try {
            if (time>0){
                redisTemplate.expire(key,time, TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /*
        判断某个KEY在redis中是否存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /*
        获取redis缓存数据
     */
    public Object getObject(String key){
        return key == null?null:redisTemplate.opsForValue().get(key);
    }

    /*
        存储数据
     */
    public boolean setObject(String key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /*
        拼接获取redis key的字符串
     */
    public String getKeyStr(String pre,String id){
        StringBuffer sb = new StringBuffer();
        sb.append(pre);
        sb.append(id);
        return sb.toString();
    }

    /*
        根据id删除redis中的数据
     */
    public boolean delete(String id){
        boolean flag = redisTemplate.delete(id);
        return flag;
    }

    private String getRedisKey(String keyPre , String id){
        return this.getKeyStr(keyPre,id);
    }

}
