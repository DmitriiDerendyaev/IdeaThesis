package ru.derendyaev.mospolytech.gigaChat.models.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @JsonProperty("role")
    private String role; // роль отправителя (system, user, assistant)

    @JsonProperty("content")
    private String content; // содержание сообщения
}