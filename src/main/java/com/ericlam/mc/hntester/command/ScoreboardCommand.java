package com.ericlam.mc.hntester.command;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.ericlam.mc.hntester.HNTester;
import com.ericlam.mc.hntester.WrapperPlayServerScoreboardScore;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ScoreboardCommand extends CommandNode {

    private final Scoreboard scoreboard;
    private final Objective obj;
    private final Scoreboard reset = Bukkit.getScoreboardManager().getNewScoreboard();
    private static final String PLAYER = "hello: %player_name%";
    private static final String TIME = "time: %localtime_time_HH:mm:ss%";
    private static final String GAME_MODE = "gamemode: %player_gamemode%";

    public ScoreboardCommand(CommandNode parent) {
        super(parent, "scoreboard", null, "scoreboard command", null);
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Optional.ofNullable(scoreboard.getObjective("Test")).ifPresent(Objective::unregister);
        obj = scoreboard.registerNewObjective("Test", "dummy", "Test Title");
        Optional.ofNullable(scoreboard.getTeam("test")).ifPresent(Team::unregister);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore(PLAYER).setScore(10);
        obj.getScore(TIME).setScore(9);
        obj.getScore(GAME_MODE).setScore(8);

        Bukkit.getScheduler().runTaskTimer(HNTester.getProvidingPlugin(HNTester.class), () -> {
            updatePlayerFake();
        }, 0L, 20L);
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        //if (!(commandSender instanceof Player)) return true;
        updatePlayerReal();
        return true;
    }

    private void updatePlayerReal(){
        obj.setDisplaySlot(null);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    private void updatePlayerFake(){
        Bukkit.getOnlinePlayers().forEach(p -> {
            Map.of(PLAYER, 10, TIME, 9, GAME_MODE, 8).forEach((entry, score)->{
                var wrapper = new WrapperPlayServerScoreboardScore(new PacketContainer(PacketType.Play.Server.SCOREBOARD_SCORE));
                wrapper.setScoreboardAction(EnumWrappers.ScoreboardAction.REMOVE);
                wrapper.setScoreName(entry);
                wrapper.setObjectiveName(obj.getName());
                wrapper.sendPacket(p);

                wrapper = new WrapperPlayServerScoreboardScore(new PacketContainer(PacketType.Play.Server.SCOREBOARD_SCORE));
                wrapper.setScoreboardAction(EnumWrappers.ScoreboardAction.CHANGE);
                wrapper.setScoreName(entry);
                wrapper.setObjectiveName(obj.getName());
                wrapper.setValue(score);
                wrapper.sendPacket(p);
            });
        });
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
