package org.robatipoor.vajehyabbot;

import java.util.Objects;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VajehYabBotApplication implements CommandLineRunner {

	private static final Logger log = LogManager.getLogger(VajehYabBotApplication.class);
	public final static int PORT;

	static {
		if (Objects.equals(System.getenv("PORT"), null)) {
			log.fatal("PORT number not set ");
		}
		PORT = Integer.parseInt(System.getenv("PORT"));
	}

	public static void main(String[] args) {
		SpringApplication.run(VajehYabBotApplication.class, args);
	}

	@Override
	public void run(String... args) {

		ApiContextInitializer.init();
		TelegramBotsApi botsApi = new TelegramBotsApi();
		try {
			botsApi.registerBot(new VajehYabBot());
			log.info("registerBot VajehYabBot");
		} catch (TelegramApiException e) {
			log.fatal("Register bot Failed maybe connection problem {}", e.toString());
		}
	}
}
