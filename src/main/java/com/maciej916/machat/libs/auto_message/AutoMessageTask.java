package com.maciej916.machat.libs.auto_message;

import com.maciej916.machat.data.DataManager;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.TimerTask;

import static com.maciej916.machat.libs.text.TextFormat.*;

public class AutoMessageTask extends TimerTask {

    private MinecraftServer server;
    private int i = 0;

    AutoMessageTask(MinecraftServer server) {
        this.server = server;
    }

    @Override
    public void run() {
        int cntMsg = DataManager.getMessages().messagesList().size();
        if (i >= cntMsg) i = 0;
        String message = DataManager.getMessages().messagesList().get(i);

        if (server.getPlayerList().getPlayers().size() == 0) return;
        for (ServerPlayerEntity player : server.getPlayerList().getPlayers()) {
            ArrayList<Object> var = variableFinder(message, true);
            var = replacePlayer(var, player);
            player.sendMessage(componentBuilder(var));
        }

        i++;
    }
}
