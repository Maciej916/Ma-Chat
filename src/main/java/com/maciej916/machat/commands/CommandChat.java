package com.maciej916.machat.commands;

import com.maciej916.machat.MaChat;
import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.libs.Methods;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import static com.maciej916.machat.libs.text.TextFormat.replaceColors;

public class CommandChat {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("chat").requires((source) -> source.hasPermissionLevel(1))
                .then(Commands.literal("toggle").executes((context) -> toggle(context.getSource())))
        );
    }

    private static int clear(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();

        for (int i = 0; i < 40; i++) {
            player.server.getPlayerList().sendMessage(new StringTextComponent(""));
        }

        ITextComponent chatCleared = new StringTextComponent(replaceColors(ConfigValues.chatClearTemplate));
        player.server.getPlayerList().sendMessage(chatCleared);

        return Command.SINGLE_SUCCESS;
    }

    private static int toggle(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();

        if (MaChat.chatEnabled) {
            MaChat.chatEnabled = false;
            player.sendMessage(Methods.formatText("chat.machat.off"));
        } else {
            MaChat.chatEnabled = true;
            player.sendMessage(Methods.formatText("chat.machat.on"));
        }

        return Command.SINGLE_SUCCESS;
    }


}