package com.task;

import logic.GenderAnswer;

public class Presentation {

    public static String generateMessage(GenderAnswer genderAnswer) {
        return switch (genderAnswer) {
            case FEMALE -> "FEMALE";
            case MALE -> "MALE";
            case INCONCLUSIVE -> "INCONCLUSIVE";
        };
    }
}
