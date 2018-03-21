package com.unrealdinnerbone.simplefireworks.lib.firework;

import scala.actors.threadpool.Arrays;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static final String[][] TEST = {
            {"TEST"}
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
            case "TEST":
                return TEST;
        }
        return null;
    }

    public static final List<String> ALL = new ArrayList<>();

    static {
        ALL.add("F");
        ALL.add("T");
        ALL.add("B");
        ALL.add("TEST");
    }
}
