package com.unrealdinnerbone.simplefireworks.lib.firework;

import javax.annotation.Nullable;

public class FireworkLetter {


    private static final String[][] B = {
            {"B", "B", " "},
            {"B", " ", "B"},
            {"B", "B", "B"},
            {"B", " ", "B"},
            {"B", "B", " "}
    };


    private static final String[][] F = {
            {"F", "F", "F"},
            {"F", " ", " "},
            {"F", "F", "F"},
            {"F", " ", " "},
            {"F", " ", " "}
    };


    private static final String[][] T = {
            {"T", "T", "T"},
            {" ", "T", " "},
            {" ", "T", " "},
            {" ", "T", " "},
            {" ", "T", " "},
    };

    @Nullable
    public static String[][] formLetter(String letter) {
        switch (letter)
        {
            case "B":
                return B;
            case "F":
                return F;
            case "T":
                return T;
        }
        return null;
    }
}
