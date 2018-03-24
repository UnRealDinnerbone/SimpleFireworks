package com.unrealdinnerbone.simplefireworks.parsar;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.unrealdinnerbone.simplefireworks.SimpleFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.IFirework;
import com.unrealdinnerbone.simplefireworks.api.firework.IFireworkObject;
import com.unrealdinnerbone.simplefireworks.firework.SimpleFireworkBase;
import com.unrealdinnerbone.simplefireworks.firework.SimpleFireworkObject;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectParser extends SimpleParser {

    private List<IFireworkObject> fireworkObjects;
    private Gson gson;

    public ObjectParser(File file) {
        super("FireworkObjectParser", file);
        this.fireworkObjects = new ArrayList<>();
        this.gson = new Gson();
    }

    @Override
    public void scan() {
        scanFolder(folder);
    }

    private void scanFolder(File folder) {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                scanFolder(file);
            } else {
                if (file.getName().endsWith(".json")) {
                    try {
                        JsonReader jsonReader = new JsonReader(new FileReader(file));
                        FireworkObjectWrapper objectWrapper = gson.fromJson(jsonReader, FireworkObjectWrapper.class);
                        fireworkObjects.addAll(objectWrapper.getFireworkObjects());
                    } catch (FileNotFoundException e) {
                        log(Level.ERROR, e);
                    }
                }
            }
        }
    }


    public List<IFireworkObject> getFireworkObjects() {
        return fireworkObjects;
    }

    public IFireworkObject getFormName(String name) {
        for(IFireworkObject object1: fireworkObjects) {
            if(object1.getID().equalsIgnoreCase(name)) {
                return object1;
            }
        }
        return null;
    }

    public boolean contains(String name) {
        return getFormName(name) != null;
    }

    public static class FireworkObjectWrapper {

        List<SimpleFireworkObject> fireworkObjects;

        public List<SimpleFireworkObject> getFireworkObjects() {
            return fireworkObjects;
        }
    }
}
