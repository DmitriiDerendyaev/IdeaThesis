package ru.derendyaev.mospolytech.restUtils;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.derendyaev.mospolytech.exceptions.TokenException;
import ru.derendyaev.mospolytech.gigaChat.models.auth.GigaToken;
import ru.derendyaev.mospolytech.gigaChat.models.message.GigaMessageRequest;
import ru.derendyaev.mospolytech.gigaChat.models.message.GigaMessageResponse;

import java.util.Collections;
import java.util.UUID;

@Service
public class Client {

    private WebClient webClientToken;
    private WebClient webClientChat;

    @Value("${app.values.api.giga-chat.chat-settings.scope}")
    private String scope;

    @Value("${app.values.api.giga-chat.base-url.auth}")
    private String baseUrlAuth;

    @Value("${app.values.api.giga-chat.base-url.chat}")
    private String baseUrlGigaChat;

    @Value("${app.values.api.giga-chat.auth-key}")
    private String authKey;

    @PostConstruct
    public void init() {
        this.webClientChat = WebClientFactory.createWebClient(baseUrlGigaChat);
        this.webClientToken = WebClientFactory.createWebClient(baseUrlAuth);
    }

    public GigaToken getToken() {
        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.put("RqUID", Collections.singletonList(getUUID()));
        tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        tokenHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        tokenHeaders.set("Authorization", "Basic " + authKey);

        return this.webClientToken
                .post()
                .uri("/api/v2/oauth")
                .headers(httpHeaders -> httpHeaders.addAll(tokenHeaders))
                .body(BodyInserters.fromFormData("scope", scope))
                .retrieve()
                .bodyToMono(GigaToken.class)
                .block();
    }

    public GigaMessageResponse gigaMessageGenerate(String context, String userRequest, String token) {
        checkToken(token);
        HttpHeaders messageHeaders = new HttpHeaders();
        messageHeaders.setContentType(MediaType.APPLICATION_JSON);
        messageHeaders.put("X-Request-ID", Collections.singletonList(getUUID()));
        messageHeaders.put("X-Session-ID", Collections.singletonList(getUUID()));
        messageHeaders.put("X-Client-ID", Collections.singletonList(getUUID()));
        messageHeaders.setBearerAuth(getToken().getAccessToken());

        return this.webClientChat
                .post()
                .uri("/api/v1/chat/completions")
                .headers(httpHeaders -> httpHeaders.addAll(messageHeaders))
                .bodyValue(new GigaMessageRequest())
                .retrieve()
                .bodyToMono(GigaMessageResponse.class).block();
    }

    private String getUUID() {
        return UUID.randomUUID().toString();
    }

    private void checkToken(String token) {
        if (token == null) {
            throw new TokenException("Expired token!");
        }
    }


}
