package com.ericlam.mc.hntester.command;

import com.ericlam.mc.minigamemiscs.MinigameMiscs;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class PulseAddCommand extends CommandNode {

    public PulseAddCommand(CommandNode parent) {
        super(parent, "add", null, "pulse add command", "[time]");
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        int time = -1;
        if (list.size() > 0){
            try{
                time = Integer.parseInt(list.get(0));
            }catch (NumberFormatException e){
                player.sendMessage(e.getMessage());
                return true;
            }
        }
        MinigameMiscs.getApi().getPulseManager().startPulse(player, time);
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
