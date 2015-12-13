package bzh.telecom.tp.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class SecondSpout extends BaseRichSpout {

    @Override
    public void ack(Object msgId) {
        LOGGER.info("Everything worked fine for {}", msgId);
    }

    @Override
    public void fail(Object msgId) {
        LOGGER.error("Seems something didn't work for {}", msgId);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondSpout.class);
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
    }

    @Override
    public void nextTuple() {
    }
}
