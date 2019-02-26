package org.robatipoor.vajehyabbot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ScheduledTasks
 */

@Component
public class ScheduledTasks {

    private static final Logger log = LogManager.getLogger(ScheduledTasks.class);

    @Scheduled(fixedRate = 60000 * 10)
    public void sendRequest() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().header("User-Agent", "Mozilla/5.0 (X11; Linux i686; rv:64.0) Gecko/20100101 Firefox/64.0").url("https://yajehyabbot.herokuapp.com/").build();
        try (Response response = client.newCall(request).execute()) {
            log.info(response.body().string());
        }
    }
}
