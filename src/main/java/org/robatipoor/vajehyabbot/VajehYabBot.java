package org.robatipoor.vajehyabbot;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * VajehYabBot
 */
public class VajehYabBot extends TelegramLongPollingBot {

    private static Logger log = LoggerFactory.getLogger(VajehYabBot.class);

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setChatId(update.getMessage().getChatId());
            String msg = update.getMessage().getText();
            if (Objects.equals(msg, "/start")) {
                log.info("start command");
                message.setText("Please Send Me Farsi Word !");
                executeMessage(message);
                return;
            }
            String result = null;
            try {
                log.info("search msg = " + msg);
                result = Dictionary.search(msg);
            } catch (IOException e) {
                log.warn(e.toString());
                message.setText("search failed !");
                executeMessage(message);
                return;
            }
            log.info("Result = " + result);
            message.setText(result);
            executeMessage(message);
        }
    }

    private void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.warn("send message failed " + e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        if (Objects.equals(System.getenv("USERNAME_BOT"), null)) {
            log.error("USERNAME_BOT not set !");
            throw new EnvironmentVariableException("USERNAME_BOT not set !");
        }
        return System.getenv("UERNAME_BOT");
    }

    @Override
    public String getBotToken() {
        if (Objects.equals(System.getenv("TELEGRAM_TOKEN"), null)) {
            log.error("TELEGRAM_TOKEN not set !");
            throw new EnvironmentVariableException("TELEGRAM_TOKEN not set !");
        }
        return System.getenv("TELEGRAM_TOKEN");
    }

}
