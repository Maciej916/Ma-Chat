package com.maciej916.machat.libs;

import com.google.gson.*;

import java.io.*;

public class Json {

    public static void save(String catalog, String fileName, Object saveClass) {
        try (Writer writer = new FileWriter(catalog + fileName + ".json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
            gson.toJson(saveClass, writer);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
