package com.halty.tiktokgame.utils;

import com.halty.tiktokgame.listeners.TiktokListener;
import io.github.jwdeveloper.tiktok.TikTokLive;
import io.github.jwdeveloper.tiktok.live.LiveClient;
import org.bukkit.entity.Player;

public class TiktokConnections {
    private static LiveClient client;

    public static void setupNewLiveClient(String hostName, Player player) {
        client = TikTokLive.newClient(hostName).addListener(new TiktokListener(player)).build();
    }

    public static void connect()
    {
        if (client != null)
        {
            client.connect();
        }
    }

    public static void disconnect()
    {
        if (client != null)
        {
            client.disconnect();
        }
    }
}
