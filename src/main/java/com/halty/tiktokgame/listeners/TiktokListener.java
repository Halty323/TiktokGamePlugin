package com.halty.tiktokgame.listeners;

import com.halty.tiktokgame.TiktokGamePlugin;
import io.github.jwdeveloper.tiktok.annotations.TikTokEventHandler;
import io.github.jwdeveloper.tiktok.data.events.TikTokCommentEvent;
import io.github.jwdeveloper.tiktok.data.events.TikTokConnectedEvent;
import io.github.jwdeveloper.tiktok.data.events.TikTokDisconnectedEvent;
import io.github.jwdeveloper.tiktok.data.events.gift.TikTokGiftComboEvent;
import io.github.jwdeveloper.tiktok.data.events.gift.TikTokGiftEvent;
import io.github.jwdeveloper.tiktok.data.events.social.TikTokFollowEvent;
import io.github.jwdeveloper.tiktok.data.events.social.TikTokLikeEvent;
import io.github.jwdeveloper.tiktok.data.events.social.TikTokShareEvent;
import io.github.jwdeveloper.tiktok.listener.TikTokEventListener;
import io.github.jwdeveloper.tiktok.live.LiveClient;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.*;

import java.util.Random;

public class TiktokListener implements TikTokEventListener {
    private final Player player;

    public TiktokListener (Player player) {
        this.player = player;
    }
    @TikTokEventHandler
    public synchronized void onConnect(LiveClient liveClient, TikTokConnectedEvent event) {
        player.sendMessage("Connected to host");
    }

    @TikTokEventHandler
    public synchronized void onLike(LiveClient liveClient, TikTokLikeEvent event) {
        spawnMobTimes(event.getUser().getName(), event.getLikes()/15, EntityType.ZOMBIE);
    }

    @TikTokEventHandler
    public synchronized void onFollow(LiveClient liveClient, TikTokFollowEvent event) {
        spawnMobTimes(event.getUser().getName(), 3, EntityType.CREEPER);
    }

    @TikTokEventHandler
    public synchronized void onComment(LiveClient liveClient, TikTokCommentEvent event) {
        if (new Random().nextInt(100) <= 10) {
            spawnMobTimes(event.getUser().getName(), 2, EntityType.values()[new Random().nextInt(EntityType.values().length)]);
        }
    }

    @TikTokEventHandler
    public synchronized void onShare(LiveClient liveClient, TikTokShareEvent event) {
        spawnMobTimes(event.getUser().getName(), 3, EntityType.WITHER_SKELETON);
    }

    @TikTokEventHandler
    public synchronized void onGift(LiveClient liveClient, TikTokGiftEvent event) {
        tntRain(event.getGift().getDiamondCost());
        player.sendMessage(event.getUser().getName() + " sends " + event.getGift().getName() + " for " + event.getGift().getDiamondCost());
    }

    @TikTokEventHandler
    public synchronized void onGiftCombo(LiveClient liveClient, TikTokGiftComboEvent event) {
        tntRain(event.getGift().getDiamondCost());
        player.sendMessage(event.getUser().getName() + " sends " + event.getGift().getName() + " for " + event.getGift().getDiamondCost());
    }

    @TikTokEventHandler
    public synchronized void onDisconnect(LiveClient liveClient, TikTokDisconnectedEvent event) {
        player.sendMessage("Disconnected from host");
    }

    private void spawnMobTimes(String sender, int n, EntityType entityType) {
        for (int i = 0; i < n; i++) {
            Bukkit.getScheduler().runTaskLater(TiktokGamePlugin.getInstance(), () -> {
                Location playerLocation = player.getLocation();
                LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(playerLocation, entityType);
                entity.setCustomName(sender);
                entity.setCustomNameVisible(true);
                player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            }, 0);
        }
    }

    private void tntRain(int n) {
        for (int i = 0; i < 20*n; i++) {
            Bukkit.getScheduler().runTaskLater(TiktokGamePlugin.getInstance(), () -> {
                Location playerLocation = player.getLocation();
                TNTPrimed tnt = (TNTPrimed) playerLocation.getWorld().spawnEntity(playerLocation.add(new Random().nextInt(20+n) - (10+n), 20, new Random().nextInt(20+n) - (10+n)), EntityType.PRIMED_TNT);
                tnt.setYield(5);
                tnt.setFuseTicks(60);
            }, 0);
        }
    }

}
