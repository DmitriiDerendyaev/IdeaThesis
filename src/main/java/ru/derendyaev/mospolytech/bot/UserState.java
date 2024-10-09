package ru.derendyaev.mospolytech.bot;

import lombok.Data;

@Data
public class UserState {
    private String competencies;
    private String areaOfStudy;
    private DialogStage stage;

}
