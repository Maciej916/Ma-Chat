package com.maciej916.machat.libs;

import com.maciej916.machat.config.ConfigValues;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import java.lang.reflect.Field;

import static com.maciej916.machat.libs.text.TextFormat.replaceColors;

public class Text {

    public static ITextComponent rankColor(ServerPlayerEntity player) {
        ITextComponent displayName = player.getDisplayName();
        if (ConfigValues.rank_colors) {
            for (int i = 4; i >= 0; i--) {
                if (player.hasPermissionLevel(i)) {
                    try {
                        Field colorField = ConfigValues.class.getField("rank_color_" + i);
                        String colorFieldVal = (String) colorField.get(ConfigValues.class);
                        ITextComponent newName = new StringTextComponent(replaceColors(colorFieldVal));
                        displayName = newName.appendSibling(displayName);
                        displayName = newName.appendSibling(new StringTextComponent(Character.toString ((char) 167) + "r"));
                    } catch (Exception ignored) { }
                    break;
                }
            }
        }
        return displayName;
    }

}