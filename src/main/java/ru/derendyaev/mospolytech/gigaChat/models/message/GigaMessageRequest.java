package ru.derendyaev.mospolytech.gigaChat.models.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class GigaMessageRequest {

    @JsonProperty("model")
    private String model; // идентификатор модели

    @JsonProperty("stream")
    private boolean stream; // режим стриминга

    @JsonProperty("update_interval")
    private int updateInterval; // интервал обновления токенов

    @JsonProperty("messages")
    private List<Message> messages; // список сообщений
}
