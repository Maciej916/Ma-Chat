package com.maciej916.machat.libs.text;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

import static com.maciej916.machat.libs.Text.rankColor;

public class TextFormat {

    public static ArrayList<Object> variableFinder(String text, boolean colors) {
        if (colors) {
            text = replaceColors(text);
        }
        ArrayList<Object> var = new ArrayList<>();
        String[] found = StringUtils.substringsBetween(text, "%", "%");
        if (found != null) {
            int i = 0;
            for (String variable : found) {
                String varText = "%" + variable + "%";
                int textBefore = text.indexOf(varText);
                int textAfter = text.indexOf(varText) + varText.length();
                var.add(new StringText(text.substring(0 , textBefore)));
                var.add(new VariableText(variable));
                text = text.substring(textAfter);
                if (++i == found.length) var.add(new StringText(text));
            }
        } else {
            var.add(new StringText(text));
        }
        return var;
    }

    public static StringTextComponent componentBuilder(ArrayList<Object> var) {
        StringTextComponent text = new StringTextComponent("");
        for (Object item : var) {
            if (item instanceof IText) {
                text.appendSibling(((IText) item).getTextComponent());
            }
            if (item instanceof ITextComponent) {
                text.appendSibling((ITextComponent) item);
            }
        }
        return text;
    }

    public static ArrayList<Object> variableReplacer(ArrayList<Object> var, ArrayList<String> replace, ArrayList<Object> replaceWith) {
        for (int i = 0; i < var.size(); i++){
            if (var.get(i) instanceof VariableText) {
                VariableText varText = (VariableText) var.get(i);
                int replaceIndex = replace.indexOf(varText.getText());
                if (replaceIndex != -1) {
                    var.set(i, replaceWith.get(replaceIndex));
                }
            }
        }
        return var;
    }

    public static ArrayList<Object> replacePlayer(ArrayList<Object> var, ServerPlayerEntity player) {
        ArrayList<String> replace = new ArrayList<>();
        ArrayList<Object> replaceWith = new ArrayList<>();

        ITextComponent raw_username = player.getDisplayName();
        ITextComponent username = rankColor(player);
        String dimension = Objects.requireNonNull(DimensionType.getKey(player.dimension)).toString();
        String players = String.valueOf(player.server.getPlayerList().getOnlinePlayerNames().length);
        String max_players = String.valueOf(player.server.getPlayerList().getMaxPlayers());

        replace.add("raw_username");
        replaceWith.add(raw_username);

        replace.add("username");
        replaceWith.add(username);

        replace.add("dimension");
        replaceWith.add(new StringTextComponent(dimension));

        replace.add("players");
        replaceWith.add(new StringTextComponent(players));

        replace.add("max_players");
        replaceWith.add(new StringTextComponent(max_players));

        return variableReplacer(var, replace, replaceWith);
    }

    public static String replaceColors(String text) {
        return text.replaceAll("&", Character.toString ((char) 167));
    }

}
