package com.maciej916.machat.commands;

import com.maciej916.machat.classes.rules.RulesData;
import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.data.DataManager;
import com.maciej916.machat.libs.Methods;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;

import static com.maciej916.machat.libs.Text.replaceVariables;

public class CommandRules {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> builder = Commands.literal("rules").requires(source -> source.hasPermissionLevel(0));
        builder
                .executes(context -> rules(context))
                        .then(Commands.argument("page", IntegerArgumentType.integer())
                        .executes(context -> rulesPage(context)));

        dispatcher.register(builder);
    }

    private static int rules(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        showRules(player, 1);
        return Command.SINGLE_SUCCESS;
    }

    private static int rulesPage(CommandContext<CommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().asPlayer();
        int page = IntegerArgumentType.getInteger(context, "page");
        showRules(player, page);
        return Command.SINGLE_SUCCESS;
    }

    private static String formatPage(ServerPlayerEntity player, String text, int page, int maxPages) {
        text = text.replaceAll("%page%", String.valueOf(page));
        text = text.replaceAll("%max_pages%", String.valueOf(maxPages));
        return replaceVariables(player, text);
    }

    private static String formatRule(ServerPlayerEntity player, String text, int id, String rule) {
        text = text.replaceAll("%id%", String.valueOf(id));
        text = text.replaceAll("%rule%", rule);
        return replaceVariables(player, text);
    }

    private static void showRules(ServerPlayerEntity player, int page) {
        RulesData rules = DataManager.getRules();

        int rulesCount = rules.rulesList().size();
        int rulesPerPage = ConfigValues.rulesPerPage;
        int minPage = (page * rulesPerPage) - rulesPerPage;
        int maxPage = Math.min(page * rulesPerPage, rulesCount);
        int maxPages = (int) Math.ceil( ((double)rulesCount) / rulesPerPage);

        if (page > maxPages) {
            player.sendMessage(Methods.formatText("rules.machat.not_exist"));
            return;
        }

        player.sendMessage(new StringTextComponent(formatPage(player, rules.format_top, page, maxPages)));
        for (int i = minPage; i < maxPage; i++) {
            player.sendMessage(new StringTextComponent(formatRule(player, rules.format_rule, i + 1, rules.rulesList().get(i))));
        }
        player.sendMessage(new StringTextComponent(formatPage(player, rules.format_bottom, page, maxPages)));
    }
}