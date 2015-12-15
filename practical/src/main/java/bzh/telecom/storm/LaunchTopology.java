package bzh.telecom.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

/**
 * Created by Clement on 13/12/15.
 */
public class LaunchTopology {


    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();


        //Define your topology here


        Config conf = new Config();

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("topology", conf, builder.createTopology());
    }
}
