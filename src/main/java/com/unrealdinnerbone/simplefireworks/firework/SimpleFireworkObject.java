package com.unrealdinnerbone.simplefireworks.firework;

import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkObject;

import java.util.HashMap;

public class SimpleFireworkObject implements IFireworkObject {

    private String id;
    private HashMap<String, String> identifiers;
    private String[][] objects;


    public void setIdentifiers(HashMap<String, String> identifiers) {
        this.identifiers = identifiers;
    }

    public void setObjects(String[][] objects) {
        this.objects = objects;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public HashMap<String, String> getIdentifiers() {
        return identifiers;
    }

    @Override
    public String[][] getObject() {
        return objects;
    }
}
