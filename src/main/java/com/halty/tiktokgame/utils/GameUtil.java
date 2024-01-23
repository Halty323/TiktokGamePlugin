package com.halty.tiktokgame.utils;

import com.halty.tiktokgame.TiktokGamePlugin;
import org.bukkit.entity.Player;

public class GameUtil {
    public static void startGame(Player player) {
        String hostName = TiktokGamePlugin.getInstance().getConfig().getString("hostName");

        TiktokGamePlugin.setGameStarted(true);

        player.sendMessage("Connecting to " + hostName + "...");
        TiktokConnections.setupNewLiveClient(hostName, player);
        TiktokConnections.connect();

    }

    public static void stopGame() {
        TiktokGamePlugin.setGameStarted(false);
        TiktokConnections.disconnect();
    }
}
