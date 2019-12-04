package com.maciej916.machat.commands;

import com.maciej916.machat.data.DataLoader;
import com.maciej916.machat.libs.Log;
import com.maciej916.machat.libs.Methods;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;

public class CommandMacReload {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("macreload").requires(source -> source.hasPermissionLevel(2));
        builder.executes(context -> reload(context));
        dispatcher.register(builder);
    }

    private static int reload(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        Log.log("Call DataLoader");
        DataLoader.load();
        player.sendMessage(Methods.formatText("maereload.machat.done"));

        return Command.SINGLE_SUCCESS;
    }
}
