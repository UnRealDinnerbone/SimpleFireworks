package com.unrealdinnerbone.simplefireworks.api.firework;

import java.util.HashMap;

public interface IFireworkObject
{
    String getID();

    HashMap<String, String> getIdentifiers();

    String[][] getObject();
}
