package ru.derendyaev.mospolytech.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.derendyaev.mospolytech.gigaChat.models.message.GigaMessageResponse;
import ru.derendyaev.mospolytech.gigaChat.role.roleImpl.SystemRolePrompt;
import ru.derendyaev.mospolytech.gigaChat.role.roleImpl.UserRolePrompt;
import ru.derendyaev.mospolytech.restUtils.Client;

import java.util.HashMap;
import java.util.Map;

import static ru.derendyaev.mospolytech.bot.BotConstant.*;

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
    public String getBotUsername() {
        return "@horoscopeDaily_V2Bot";
    }

    private Map<Long, UserState> userStates = new HashMap<>();

    @Autowired
    private Client client;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            UserState userState = userStates.getOrDefault(chatId, new UserState());

            if (userMessage.equalsIgnoreCase("/start")) {
                sendMessage(chatId, INTRO_MESSAGE);
                userState.setStage(DialogStage.ASK_COMPETENCIES);
                sendMessage(chatId, "Введите через пробел набор компетенций, которыми вы обладаете. Пример: UI UX Верстка HTML CSS");
                userStates.put(chatId, userState);
            } else {
                switch (userState.getStage()) {
                    case ASK_COMPETENCIES -> {
                        userState.setCompetencies(userMessage);
                        userState.setStage(DialogStage.ASK_AREA_OF_STUDY);
                        sendMessage(chatId, ASK_COMPETENCIES_MESSAGE);
                    }
                    case ASK_AREA_OF_STUDY -> {
                        userState.setAreaOfStudy(userMessage);
                        userState.setStage(DialogStage.FINISH);
                        sendMessage(chatId, "Спасибо! Обрабатываем данные...");
                        generateDiplomaTopics(chatId, userState);
                    }
                }
                userStates.put(chatId, userState);
            }
        }
    }

    public void generateDiplomaTopics(Long chatId, UserState userState) {
        GigaMessageResponse gigaMessageResponse = client.gigaMessageGenerate(
                new SystemRolePrompt().getRolePrompt(),
                new UserRolePrompt(userState.getCompetencies(), userState.getAreaOfStudy()).getRolePrompt(), null);

        sendMessage(chatId, "Рекомендованные темы для дипломной работы:\n" + gigaMessageResponse.toString());
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
}
