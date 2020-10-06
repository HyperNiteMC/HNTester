package com.ericlam.mc.hntester;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.ScheduledPacket;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HNTester extends JavaPlugin {

    final Map<OfflinePlayer, Map<String, String>> currentMap = new ConcurrentHashMap<>();

    @Override
    public void onEnable() {
        HyperNiteMC.getAPI().getCommandRegister().registerCommand(this, new TestCommand());
        getLogger().warning("Server IP: " + getServer().getIp() + ":" + getServer().getPort());
        if (getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            getLogger().info("Placeholder hooked.");
            ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Server.SCOREBOARD_SCORE) {
                @Override
                public void onPacketSending(PacketEvent event) {
                    currentMap.putIfAbsent(event.getPlayer(), new ConcurrentHashMap<>());
                    getLogger().info("scoreboard packet send to: "+event.getPlayer().getName());
                    var wrapper = new WrapperPlayServerScoreboardScore(event.getPacket());
                    getLogger().info("action: " + wrapper.getAction().name());
                    if (wrapper.getAction() == EnumWrappers.ScoreboardAction.CHANGE) {
                        var before = wrapper.getScoreName();
                       getLogger().info("before: " + before);
                        var after = PlaceholderAPI.setPlaceholders(event.getPlayer(), before);
                       getLogger().info("after: " + after);
                        wrapper.setScoreName(after);
                        currentMap.get(event.getPlayer()).put(before, after);
                    } else if (wrapper.getAction() == EnumWrappers.ScoreboardAction.REMOVE) {
                        var before = wrapper.getScoreName();
                       getLogger().info("before: " + before);
                        var current = currentMap.get(event.getPlayer()).get(before);
                        var after = before == null ? PlaceholderAPI.setPlaceholders(event.getPlayer(), before) : current;
                        //var after = PlaceholderAPI.setPlaceholders(event.getPlayer(), before);
                       getLogger().info("after: " + after);
                        wrapper.setScoreName(after);
                    }
                    getLogger().info("mapper");
                    currentMap.forEach((p, map) -> getLogger().info(p.getName()+": "+map.toString()));
                }
            });
        }
    }


}
