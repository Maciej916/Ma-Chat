package com.maciej916.machat.commands;

import com.maciej916.machat.classes.RulesData;
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

import static com.maciej916.machat.libs.text.TextFormat.*;

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

    public static ArrayList<Object> replacePage(ArrayList<Object> var, int page, int maxPages) {
        ArrayList<String> replace = new ArrayList<>();
        ArrayList<Object> replaceWith = new ArrayList<>();

        replace.add("page");
        replaceWith.add(new StringTextComponent(String.valueOf(page)));

        replace.add("max_pages");
        replaceWith.add(new StringTextComponent(String.valueOf(maxPages)));

        return variableReplacer(var, replace, replaceWith);
    }

    public static ArrayList<Object> replaceRule(ArrayList<Object> var, int id, String rule) {
        ArrayList<String> replace = new ArrayList<>();
        ArrayList<Object> replaceWith = new ArrayList<>();

        replace.add("id");
        replaceWith.add(new StringTextComponent(String.valueOf(id + 1)));

        replace.add("rule");
        replaceWith.add(new StringTextComponent(replaceColors(rule)));

        return variableReplacer(var, replace, replaceWith);
    }

    private static void showRules(ServerPlayerEntity player, int page) {
        RulesData rules = DataManager.getRules();

        int rulesCount = rules.rulesList().size();
        int rulesPerPage = ConfigValues.rules_per_page;
        int minPage = (page * rulesPerPage) - rulesPerPage;
        int maxPage = Math.min(page * rulesPerPage, rulesCount);
        int maxPages = (int) Math.ceil(((double)rulesCount) / rulesPerPage);

        if (page > maxPages) {
            player.sendMessage(Methods.formatText("rules.machat.not_exist"));
            return;
        }

        ArrayList<Object> var = variableFinder(rules.format_top, true);
        var = replacePage(var, page, maxPages);
        player.sendMessage(componentBuilder(var));

        for (int i = minPage; i < maxPage; i++) {
            var = variableFinder(rules.format_rule, true);
            var = replaceRule(var, i, rules.rulesList().get(i));
            player.sendMessage(componentBuilder(var));
        }

        var = variableFinder(rules.format_bottom, true);
        var = replacePage(var, page, maxPages);
        player.sendMessage(componentBuilder(var));
    }

}