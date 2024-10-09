package ru.derendyaev.mospolytech.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class EvokerBotService extends TelegramLongPollingBot {

    @Value("${app.values.bot.token}")
    private String botToken;

    public EvokerBotService() {
        super();
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            // Вопрос к пользователю
            String response = "Что вы хотите изучать?";

            if (userMessage.equalsIgnoreCase("/start")) {
                sendMessage(chatId, response);
            } else {
                // Отправляем сообщение с ответом
                sendMessage(chatId, "Ваш ответ: " + userMessage);
            }
        }
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "@horoscopeDaily_V2Bot";
    }
}
