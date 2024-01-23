package com.halty.tiktokgame;

import com.halty.tiktokgame.commands.StartCommand;
import com.halty.tiktokgame.commands.StopCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class TiktokGamePlugin extends JavaPlugin {
    private static volatile boolean gameStarted = false;
    private static TiktokGamePlugin instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        Objects.requireNonNull(getCommand("startgame".toLowerCase())).setExecutor(new StartCommand());
        Objects.requireNonNull(getCommand("stopgame".toLowerCase())).setExecutor(new StopCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public TiktokGamePlugin() {
        instance = this;
    }

    public static TiktokGamePlugin getInstance() {
        return instance;
    }

    public static void setGameStarted(boolean gameStarted) {
        TiktokGamePlugin.gameStarted = gameStarted;
    }

    public static boolean isGameStarted() {
        return gameStarted;
    }
}
