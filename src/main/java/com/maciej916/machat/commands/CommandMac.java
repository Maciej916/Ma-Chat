package com.maciej916.machat.commands;

import com.maciej916.machat.MaChat;
import com.maciej916.machat.data.DataLoader;
import com.maciej916.machat.data.DataManager;
import com.maciej916.machat.libs.Log;
import com.maciej916.machat.libs.Methods;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.MessageArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;

public class CommandMac {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("mac").requires((source) -> source.hasPermissionLevel(4))
                .then(Commands.literal("reload").executes((context) -> reload(context.getSource())))
                .then(Commands.literal("rules")
                        .then(Commands.literal("add").then(Commands.argument("rule", MessageArgument.message()).executes((context) -> rulesAdd(context.getSource(), MessageArgument.getMessage(context, "rule")))))
                        .then(Commands.literal("remove").then(Commands.argument("id", IntegerArgumentType.integer()).executes((context) -> rulesRemove(context.getSource(), IntegerArgumentType.getInteger(context, "id")))))
                )
                .then(Commands.literal("automessages")
                        .then(Commands.literal("add").then(Commands.argument("message", MessageArgument.message()).executes((context) -> autoMessagesAdd(context.getSource(), MessageArgument.getMessage(context, "message")))))
                        .then(Commands.literal("remove").then(Commands.argument("id", IntegerArgumentType.integer()).executes((context) -> autoMessagesRemove(context.getSource(), IntegerArgumentType.getInteger(context, "id")))))
                        .then(Commands.literal("frequency").then(Commands.argument("seconds", IntegerArgumentType.integer()).executes((context) -> autoMessagesFrequency(context.getSource(), IntegerArgumentType.getInteger(context, "seconds")))))
                )
        );
    }

    private static int reload(CommandSource source) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        Log.log("Call DataLoader");
        DataLoader.load();
        player.sendMessage(Methods.formatText("maereload.machat.done"));
        return Command.SINGLE_SUCCESS;
    }

    private static int rulesAdd(CommandSource source, ITextComponent rule) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        DataManager.getRules().addRule(rule.getString());
        player.sendMessage(Methods.formatText("rules.machat.added"));
        return Command.SINGLE_SUCCESS;
    }

    private static int rulesRemove(CommandSource source, int id) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        DataManager.getRules().removeRule(id-1);
        player.sendMessage(Methods.formatText("rules.machat.removed"));
        return Command.SINGLE_SUCCESS;
    }

    private static int autoMessagesAdd(CommandSource source, ITextComponent rule) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        DataManager.getMessages().addMessage(rule.getString());
        player.sendMessage(Methods.formatText("automessage.machat.added"));
        return Command.SINGLE_SUCCESS;
    }

    private static int autoMessagesRemove(CommandSource source, int id) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        DataManager.getMessages().removeMessage(id-1);
        player.sendMessage(Methods.formatText("automessage.machat.removed"));
        return Command.SINGLE_SUCCESS;
    }

    private static int autoMessagesFrequency(CommandSource source, int seconds) throws CommandSyntaxException {
        ServerPlayerEntity player = source.asPlayer();
        if (seconds >= 5) {
            DataManager.getMessages().setFrequency(seconds * 1000);
            MaChat.autoMessage.restartTimer();
            player.sendMessage(Methods.formatText("automessage.machat.frequency.set", seconds));
        } else {
            player.sendMessage(Methods.formatText("automessage.machat.frequency.min"));
        }
        return Command.SINGLE_SUCCESS;
    }

}
