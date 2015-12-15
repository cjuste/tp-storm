package bzh.telecom.storm.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Tuple;

/**
 * Created by Clement on 13/12/15.
 */
public class IsBreakBolt extends BaseBasicBolt {
    
	private static final long serialVersionUID = -6746462569240240356L;

	public void execute(Tuple arg0, BasicOutputCollector arg1) {
		boolean isBreak = false;
		String date = "";
		//Calculate if it's the break and fill the date field with corresponding ts.


		if (isBreak) {
			System.out.println("It's the break at " + date);
		}
	}

	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}


}
