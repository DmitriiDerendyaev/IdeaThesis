package ru.derendyaev.mospolytech.gigaChat.role.roleImpl;

import ru.derendyaev.mospolytech.gigaChat.role.RolePrompt;

public class UserRolePrompt extends RolePrompt {

    private final String competencies;
    private final String areaOfStudy;

    public UserRolePrompt(String competencies, String areaOfStudy) {
        this.competencies = competencies;
        this.areaOfStudy = areaOfStudy;
    }

    @Override
    public String getRolePrompt() {
        return "Необходимо помочь студенту выбрать тему для дипломной работы на основе его компетенций и направления научной деятельности. " +
                "Учитывай предоставленные данные и помоги предложить три темы для диплома, каждая из которых должна быть с кратким описанием и рекомендацией, какие навыки и технологии пригодятся для реализации.\n\n" +
                "Данные о студенте:\n" +
                "Компетенции: " + this.competencies + "\n" +
                "Предметная область: " + this.areaOfStudy;
    }
}