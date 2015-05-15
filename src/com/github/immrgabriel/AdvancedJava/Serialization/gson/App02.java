package com.github.immrgabriel.AdvancedJava.Serialization.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class App02 {
    public static void main(String[] args) {
        DataObject dataObject = new DataObject();
        dataObject.i++;
        dataObject.s+='!';
        dataObject.myData = "hello!";
        System.out.println(dataObject);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(dataObject);
        System.out.println(json);

        DataObject object = gson.fromJson(json, DataObject.class);
        System.out.println(object);

    }
}
