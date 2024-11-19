package ru.derendyaev.mospolytech;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.derendyaev.mospolytech.gigaChat.models.auth.GigaToken;
import ru.derendyaev.mospolytech.gigaChat.models.message.GigaMessageResponse;
import ru.derendyaev.mospolytech.gigaChat.role.roleImpl.SystemRolePrompt;
import ru.derendyaev.mospolytech.gigaChat.role.roleImpl.UserRolePrompt;
import ru.derendyaev.mospolytech.restUtils.Client;

@SpringBootApplication
public class IdeaThesisApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(IdeaThesisApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("Приложение запущено");
	}
}