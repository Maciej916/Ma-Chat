package com.maciej916.machat.libs;

import com.maciej916.machat.MaChat;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;

import java.io.*;
import java.util.Optional;

import static com.maciej916.machat.MaChat.MODID;

public class Methods {

    private static String getVersion() {
        Optional<? extends ModContainer> o = ModList.get().getModContainerById(MODID);
        if (o.isPresent()) {
            return o.get().getModInfo().getVersion().toString();
        }
        return "NONE";
    }

    public static boolean isDev() {
        String version = getVersion();
        return version.equals("NONE");
    }

    public static FileReader loadFile(String catalog, String fileName) throws Exception {
        return new FileReader(catalog + fileName + ".json");
    }

    public static void writeModFile(String resource, String destination) throws Exception {
        File targetFile = new File(destination);
        if (!targetFile.exists()) {
            Log.log("Creating " + resource);
            InputStream initialStream = MaChat.class.getResourceAsStream("/" + resource);
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
        }
    }

    public static TextComponent formatText(String translationKey, Object... args) {
        return new TranslationTextComponent(translationKey, args);
    }

}

