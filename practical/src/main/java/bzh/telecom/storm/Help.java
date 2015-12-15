package bzh.telecom.storm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Clement on 15/12/15.
 */
public class Help {

    public Help() {
    }

    public static void main(String[] args) {
        //A key value object. The first element is a key
        Map<String, Integer> map = new HashMap<String, Integer>();
        //To add an element
        map.put("key", 3);
        //To remove an element
        map.remove("key");
        //To get an element
        Integer value = map.get("key");

        //A queue to store the messages before treating them
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
        //To add an element
        queue.add("new element");
        //To get the first element and remove it
        String element = queue.poll();

        //A list of values with no size limit
        List<String> list = new ArrayList<String>();
        list.add("string");
        //To get the second element of the list;
        String listElement = list.get(1);
    }
}
