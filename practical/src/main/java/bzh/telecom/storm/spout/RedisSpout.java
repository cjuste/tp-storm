package bzh.telecom.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.util.Map;

/**
 * Created by Clement on 13/12/15.
 */
public class RedisSpout extends JedisPubSub implements IRichSpout{

    private String redisUrl;
    private JedisPool jedisPool;
    private SpoutOutputCollector outputCollector;

    public RedisSpout(String redisUrl) {
        this.redisUrl = redisUrl;
    }

    //You need to call it in the right place
    private void initializeRedisConnection() {
        jedisPool = new JedisPool(new JedisPoolConfig(), redisUrl);
        Jedis jedis = jedisPool.getResource();
        jedis.subscribe(this, "feed");
        jedisPool.returnResource(jedis);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.outputCollector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
    }



    @Override
    public void onMessage(String channel, String message) {
        //Called whenever a new message arrives in the Redis channel.
    }





    //Don't worry about what's after this line
    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }

    @Override
    public void close() {
        jedisPool.close();
    }

    @Override
    public void ack(Object o) {

    }

    @Override
    public void fail(Object o) {

    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {

    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {

    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {

    }
    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }
}
