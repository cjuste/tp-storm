package bzh.telecom.storm.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

/**
 * Created by Clement on 13/12/15.
 */
public class CompareGrowthBolt extends BaseBasicBolt {

	private static final long serialVersionUID = 6421090566082748270L;

	public void execute(Tuple tuple, BasicOutputCollector basicOutputCollector) {
		// TODO Auto-generated method stub
		System.out.println(tuple.getValue(0));
	}

	public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
		// TODO Auto-generated method stub
		
	}

   
}
