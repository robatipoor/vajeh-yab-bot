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

    @Scheduled(fixedRate = 60000 * 30)
    public void sendRequest() throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://yajehyabbot.herokuapp.com/").build();
        try (Response response = client.newCall(request).execute()) {
            log.info(response.body().string());
        }
    }
}
