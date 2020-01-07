package com.maciej916.machat.libs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

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
