package bzh.telecom.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

/**
 * Created by Clement on 13/12/15.
 */
public class LaunchTopology {

    private static final String SPOUT = "first_spout";
    private static final String FIRST_BOLT = "first_bolt";

    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();


        //Define your topology here

        Config conf = new Config();

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("topology", conf, builder.createTopology());
    }
}
