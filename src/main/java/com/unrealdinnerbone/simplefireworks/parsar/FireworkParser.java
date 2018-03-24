package com.unrealdinnerbone.simplefireworks.parsar;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.unrealdinnerbone.simplefireworks.api.firework.IFirework;
import com.unrealdinnerbone.simplefireworks.firework.SimpleFireworkBase;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;

public class FireworkParser extends SimpleParser
{

    private HashMap<String, FireworkWrapper> fireworkObjects;
    private Gson gson;

    public FireworkParser(File file) {
        super("FireworkParser", file);
        this.fireworkObjects = new HashMap<>();
        this.gson = new Gson();
    }

    @Override
    public void scan() {
        scanFolder(folder);
    }

    private void scanFolder(File folder) {
        for(File file: folder.listFiles()) {
            if (file.isDirectory()) {
                scanFolder(file);
            }else
            if(file.getName().endsWith(".json")) {
                try {
                    String name = file.getName().replace(".json", "");
                    JsonReader jsonReader = new JsonReader(new FileReader(file));
                    FireworkWrapper fireworkWrapper = gson.fromJson(jsonReader, FireworkWrapper.class);
                    fireworkObjects.put(name, fireworkWrapper);
                } catch (FileNotFoundException e) {
                    log(Level.ERROR, e);
                }
            }
        }
    }

    public HashMap<String, FireworkWrapper> getFireworkObjects() {
        return fireworkObjects;
    }

    public static class FireworkWrapper {

        List<SimpleFireworkBase> fireworks;

        public List<SimpleFireworkBase> getFireworks() {
            return fireworks;
        }
    }
}
