package org.niray.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class RedisMapper {

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    /**
     * 插入Reids
     *
     * @param key
     * @param value
     */
    public void save(final String key, final String value, final long time) {
        this.redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(redisTemplate.getStringSerializer().serialize(key),
                        redisTemplate.getStringSerializer().serialize(value));
                connection.expire(redisTemplate.getStringSerializer().serialize(key), time);
                return true;
            }
        });
    }

    /**
     * 取出Redis
     *
     * @param key
     */
    public String get(final String key) {
        return (String) this.redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte bytes[] = connection.get(redisTemplate.getStringSerializer().serialize(key));
                if (bytes == null)
                    return "";
                return new String(bytes);
            }
        });
    }

    /**
     * 刷新
     *
     * @param key
     */
    public void refresh(final String key, final long time) {
        this.redisTemplate.execute(new RedisCallback<Object>() {
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                try {
                    connection.expire(redisTemplate.getStringSerializer().serialize(key), time);
                } catch (Exception ce) {

                }
                return true;
            }
        });
    }
}
