package bzh.telecom.tp.storm.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

public class FirstBolt extends BaseBasicBolt {

    @Override
    public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
        //To process a tuple
        Object value = tuple.getValueByField("usefull");
        //...

        //To emit to the next bolt
        basicOutputCollector.emit(new Values("toto"));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        //Declare the fields in the tuples sent by this bolt
        outputFieldsDeclarer.declare(new Fields("useless"));
    }
}
