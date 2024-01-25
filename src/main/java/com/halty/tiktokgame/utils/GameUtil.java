package com.halty.tiktokgame.utils;

import com.halty.tiktokgame.TiktokGamePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

public class GameUtil {
    public static void startGame(Player player, String hostName) {
        TiktokGamePlugin.setGameStarted(true);

        player.sendMessage("Connecting to " + hostName + "...");
        TiktokConnections.setupNewLiveClient(hostName, player);
        TiktokConnections.connect();

    }

    public static void tntCustom(int n, Player player, int count) {
        for (int i = 0; i < count; i++) {
            Bukkit.getScheduler().runTaskLater(TiktokGamePlugin.getInstance(), () -> {
                Location playerLocation = player.getLocation();
                TNTPrimed tnt = (TNTPrimed) playerLocation.getWorld().spawnEntity(playerLocation, EntityType.PRIMED_TNT);
                tnt.setYield(n);
                tnt.setFuseTicks(60);
            }, 0);
        }
    }

    public static void stopGame() {
        TiktokGamePlugin.setGameStarted(false);
        TiktokConnections.disconnect();
    }
}
