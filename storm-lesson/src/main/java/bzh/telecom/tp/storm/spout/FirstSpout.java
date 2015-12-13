package bzh.telecom.tp.storm.spout;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.util.Map;

public class FirstSpout extends BaseRichSpout {

    private SpoutOutputCollector outputCollector;
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //Declare the fields in the tuples sent by this spout
        outputFieldsDeclarer.declare(new Fields("usefull"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        //Called at initializing, to open connections to a database for example
        this.outputCollector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        //Called internally by Nimbus to generate a new tuple
        outputCollector.emit(new Values("titi"));
    }
}
