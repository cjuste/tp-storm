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

	private static final long serialVersionUID = -7939372277566290828L;
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
    
    public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector spoutOutputCollector) {
		// TODO Auto-generated method stub
		this.outputCollector = spoutOutputCollector;
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

    public void nextTuple() {
		// TODO Auto-generated method stub
		
	}
    

    @Override
    public void onMessage(String channel, String message) {
        //Called whenever a new message arrives in the Redis channel.
    }

    public void close() {
		if (jedisPool != null) {
			jedisPool.close();
		}
	}


    //You won't need any of the next methods
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

	public void ack(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	public void activate() {
		// TODO Auto-generated method stub
		
	}

	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	public void fail(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
}
