package bzh.telecom.tp.storm.service;

/**
 * Created by Clement on 28/11/15.
 */
public interface RedisService {

    void publishEvent(String key, String message);
}
