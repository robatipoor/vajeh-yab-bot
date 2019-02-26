package org.robatipoor.vajehyabbot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@SpringBootApplication
public class VajehYabBotApplication implements CommandLineRunner {

	private static Logger log = LoggerFactory.getLogger(VajehYabBot.class);
	public final static int PORT;

	static {
		if (Objects.equals(System.getenv("PORT"), null)) {
			log.error("PORT number not set ");
			throw new EnvironmentVariableException("PORT number not set ");
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
			log.error("Register bot Failed maybe connection problem {}", e.toString());
			throw new RuntimeException(e.toString());
		}
	}
}
