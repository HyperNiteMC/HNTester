package com.ericlam.mc.hntester.command;

import com.ericlam.mc.minigamemiscs.MinigameMiscs;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class TintAddCommand extends CommandNode {
    public TintAddCommand(CommandNode parent) {
        super(parent, "add", null, "tint add command", "<percentage> [time]");
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        if (!(commandSender instanceof Player)) return true;
        var player = (Player) commandSender;
        int percentage;
        try{
            percentage = Integer.parseInt(list.get(0));
        }catch (NumberFormatException e){
            player.sendMessage(e.getMessage());
            return true;
        }
        int time = -1;
        if (list.size() > 1){
            try{
                time = Integer.parseInt(list.get(1));
            }catch (NumberFormatException e){
                player.sendMessage(e.getMessage());
                return true;
            }
        }
        if (time != -1){
            MinigameMiscs.getApi().getTintManager().fadeTint(player, percentage, time);
        }else{
            MinigameMiscs.getApi().getTintManager().sendTint(player, percentage);
        }

        return true;
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
