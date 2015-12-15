package bzh.telecom.tp.storm.scheduling;

import bzh.telecom.tp.storm.service.RedisService;
import com.google.common.collect.Lists;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Clement on 28/11/15.
 */
@Service
public class ImportData {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportData.class);
    private List<Integer> feedIds = Lists.newArrayList(7, 11, 36, 37, 57);
    @Autowired
    private RedisService redisService;
    @Value("${feed.url}")
    private String feedUrl;

    @Scheduled(initialDelay = 0, fixedRate = 1000)
    public void getData() {
        DateTime now = DateTime.now(DateTimeZone.forID("Europe/Paris")).minusMonths(1);
        for (int feed : feedIds) {
            try {
                HttpResponse<String> response = Unirest.get(feedUrl).routeParam("id", Integer.toString(feed)).routeParam("start", Long.toString(now
                        .minusSeconds(1).getMillis())).routeParam("end", Long.toString(now.getMillis())).asString();
                String responseBody = response.getBody();
                if (StringUtils.isNotEmpty(responseBody) && responseBody.matches("\\[\\[[0-9]+\\,\"[0-9]+\"\\]\\]")){
                    String bodyWithoutHook = responseBody.substring(2, responseBody.length()-2);
                    String[] values = bodyWithoutHook.split("\\,");
                    long ts = Long.parseLong(values[0]);
                    Integer value = Integer.parseInt(values[1].substring(1, values[1].length() - 1));
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", feed);
                    jsonObject.put("ts", new DateTime(ts, DateTimeZone.UTC).plusMonths(1).toString());
                    jsonObject.put("value", value);
                    redisService.publishEvent("feed", jsonObject.toString());
                }
            } catch (UnirestException e) {
                LOGGER.error("Unable to query the feed", feedUrl);
            }
        }
    }
}
