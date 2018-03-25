package com.unrealdinnerbone.simplefireworks.firework;

import com.google.gson.Gson;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumExplodeEffect;
import com.unrealdinnerbone.simplefireworks.api.firework.EnumFireworkEffect;
import com.unrealdinnerbone.simplefireworks.api.firework.FireworkBase;
import com.unrealdinnerbone.simplefireworks.api.firework.FireworkObject;
import com.unrealdinnerbone.simplefireworks.lib.Reference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonMaker
{
    public static Gson gson = new Gson();




    public static void makeBaseJsonForObjects(File file)  {
        FireworkObjectWrapper objectWrapper = new FireworkObjectWrapper();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("W", "white");
        char letter = 'A';
        for (String[][] array : FireworkLetter2.ALL) {
            FireworkObject object = new FireworkObject();
            object.setIdentifiers(stringStringHashMap);
            object.setId(String.valueOf(letter++));
            object.setObjects(array);
            objectWrapper.fireworkObjects.add(object);
        }
        File letters = new File(file, "letters.json");
        Writer writer = null;
        try {
            writer = new FileWriter(letters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson.toJson(objectWrapper, FireworkObjectWrapper.class, writer);
    }

    public static void makeBaseFireworkFile(File file) {
        FireworkWrapper fireworkWrapper = new FireworkWrapper();
        FireworkBase base = new FireworkBase();
        List<Integer> whiteList = new ArrayList<>();
        whiteList.add(16777215);
        base.setFadeColors(whiteList);
        base.setColors(whiteList);
        base.setExplodeEffect(EnumExplodeEffect.SMALL_BALL);
        File letters = new File(file, "white.json");
        if(!letters.exists()) {
            try {
                letters.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fireworkWrapper.fireworks.add(base);
        Writer writer = null;
        try {
            writer = new FileWriter(letters);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson.toJson(fireworkWrapper, FireworkWrapper.class, writer);
    }


    public static class FireworkObjectWrapper {

        List<FireworkObject> fireworkObjects;

        public FireworkObjectWrapper() {
            fireworkObjects= new ArrayList<>();
        }

        public List<FireworkObject> getFireworkObjects() {
            return fireworkObjects;
        }

    }

    public static class FireworkWrapper {

        List<FireworkBase> fireworks;

        public FireworkWrapper() {
            fireworks = new ArrayList<>();
        }

        public List<FireworkBase> getFireworks() {
            return fireworks;
        }

    }
}
