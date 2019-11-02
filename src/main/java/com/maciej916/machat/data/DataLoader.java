package com.maciej916.machat.data;

import com.maciej916.machat.MaChat;
import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DataLoader {

    public static void setup() {
        Log.log("Setup main catalog");
        ConfigValues.mainCatalog = System.getProperty("user.dir") + "/" + MaChat.MODID + "/";
        Log.debug("Main catalog is: " + ConfigValues.mainCatalog);
        try {
            Log.log("Creating main catalogs and files");
            new File(ConfigValues.mainCatalog).mkdirs();
            File targetFile = new File(ConfigValues.mainCatalog + "motd.txt");
            if (!targetFile.exists()) {
                Log.log("Creating motd.txt in main catalog");
                InputStream initialStream = MaChat.class.getResourceAsStream("/motd.txt");
                byte[] buffer = new byte[initialStream.available()];
                initialStream.read(buffer);
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);
            }
        } catch (Exception e) {
            Log.log("Error in setup");
            System.out.println(e);
        }
    }

    public static void load() {
        Log.debug("Letup");
    }

}
