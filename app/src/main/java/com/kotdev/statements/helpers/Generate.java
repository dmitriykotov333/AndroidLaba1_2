package com.kotdev.statements.helpers;

import java.util.Random;

public class Generate {
    private static final Random RN = new Random();

    public static String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }
}
