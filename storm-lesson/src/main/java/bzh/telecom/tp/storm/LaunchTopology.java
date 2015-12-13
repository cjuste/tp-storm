package bzh.telecom.tp.storm;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import bzh.telecom.tp.storm.bolt.FirstBolt;
import bzh.telecom.tp.storm.spout.FirstSpout;

public class LaunchTopology {

    private static final String SPOUT = "first_spout";
    private static final String FIRST_BOLT = "first_bolt";

    public static void main(String[] args) {

        TopologyBuilder builder = new TopologyBuilder();
        FirstSpout firstSpout = new FirstSpout();

        builder.setSpout(SPOUT, firstSpout, 1);

        builder.setBolt(FIRST_BOLT, new FirstBolt(), 5)
                .fieldsGrouping(SPOUT, new Fields("usefull"));

        Config conf = new Config();

        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("topology", conf, builder.createTopology());

        System.exit(0);
    }
}
