package com.unrealdinnerbone.simplefireworks.firework;

import com.google.gson.Gson;
import com.unrealdinnerbone.simplefireworks.api.firework.FireworkObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonMaker
{
    public static FireworkObjectWrapper objectWrapper = new FireworkObjectWrapper();
    public static Gson gson = new Gson();


    public static void main(String[] args) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("W", "white");
        char letter = 'A';
        for(String[][] array: FireworkLetter2.ALL) {
            FireworkObject object = new FireworkObject();
            object.setIdentifiers(stringStringHashMap);
            object.setId(String.valueOf(letter++));
            object.setObjects(array);
            objectWrapper.fireworkObjects.add(object);
        }
        String json = gson.toJson(objectWrapper, FireworkObjectWrapper.class);
        System.out.println(json);
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
}
