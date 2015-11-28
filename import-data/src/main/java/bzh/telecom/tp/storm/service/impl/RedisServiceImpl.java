package bzh.telecom.tp.storm.service.impl;

import bzh.telecom.tp.storm.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;

/**
 * Created by Clement on 28/11/15.
 */
@Service
public class RedisServiceImpl implements RedisService {

    private JedisPool jedisPool;
    @Value("${redis.url}")
    private String redisUrl;

    @PostConstruct
    public void initRedis(){
        jedisPool = new JedisPool(new JedisPoolConfig(), redisUrl);
    }

    @Override
    public void publishEvent(String key, String message) {
        if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(message)) {
            Jedis jedis = jedisPool.getResource();
            jedis.publish(key, message);
            jedisPool.returnResource(jedis);
        }
    }
}
