package ru.derendyaev.mospolytech.gigaChatTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;
import ru.derendyaev.mospolytech.restUtils.Client;

@SpringBootTest
public class GigaChatServiceIntegrationTest {

    @Autowired
    private Client gigaChatService;

    @Test
    public void testGetToken() {
    }
}
