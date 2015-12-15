package bzh.telecom.tp.storm.service.impl;

import bzh.telecom.tp.storm.service.RedisService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * Created by Clement on 28/11/15.
 */
@Service
public class RedisServiceImpl extends JedisPubSub implements RedisService {

    private JedisPool jedisPool;
    @Value("${redis.url}")
    private String redisUrl;

    @PostConstruct
    public void initRedis(){
        jedisPool = new JedisPool(new JedisPoolConfig(), redisUrl);
        Jedis jedis = jedisPool.getResource();
        jedis.subscribe(this, "is_break");
    }

    @Override
    public void publishEvent(String key, String message) {
        if(StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(message)) {
            Jedis jedis = jedisPool.getResource();
            jedis.publish(key, message);
            jedisPool.returnResource(jedis);
        }
    }

    @PreDestroy
    public void closeRedis(){
        jedisPool.destroy();
    }

    @Override
    public void onMessage(String s, String s1) {
        DateTime now = DateTime.now(DateTimeZone.UTC).plusMinutes(1);
        List<Integer> feedIds = Lists.newArrayList(7, 11, 36, 37, 57);
        List<Integer> values = Lists.newArrayList(15, 8, 15, 20, 12);
        for (int j=0; j<5; j++) {
            for (int i=0; i<feedIds.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", feedIds.get(i));
                jsonObject.put("value", values.get(i)+j);
                jsonObject.put("ts", now.plusSeconds(j).toString());
                publishEvent("feed", jsonObject.toString());
            }
        }
    }

    @Override
    public void onPMessage(String s, String s1, String s2) {

    }

    @Override
    public void onSubscribe(String s, int i) {

    }

    @Override
    public void onUnsubscribe(String s, int i) {

    }

    @Override
    public void onPUnsubscribe(String s, int i) {

    }

    @Override
    public void onPSubscribe(String s, int i) {

    }
}
