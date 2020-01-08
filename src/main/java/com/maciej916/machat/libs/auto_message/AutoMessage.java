package com.maciej916.machat.libs.auto_message;

import com.maciej916.machat.config.ConfigValues;
import com.maciej916.machat.data.DataManager;
import com.maciej916.machat.libs.Log;
import net.minecraft.server.MinecraftServer;

import java.util.Timer;
import java.util.TimerTask;

public class AutoMessage {

    private Timer timer;
    private MinecraftServer server;

    public AutoMessage(MinecraftServer server) {
        this.server = server;
    }

    public void startTimer() {
        boolean dedicated = server.isDedicatedServer();
        if (dedicated || (!dedicated && ConfigValues.client_enable)) {
            timer = new Timer();
            TimerTask task = new AutoMessageTask(server);

            int frequency = DataManager.getMessages().getFrequency();
            if (frequency < 5000) {
                Log.debug("Auto Message frequency is too small. Changed to 5 seconds.");
                DataManager.getMessages().setFrequency(5000);
                frequency = 5000;
            }

            timer.schedule(task, frequency, frequency);
        }
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void restartTimer() {
        stopTimer();
        startTimer();
    }

}
