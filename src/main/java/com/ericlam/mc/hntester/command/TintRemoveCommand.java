package com.ericlam.mc.hntester.command;

import com.ericlam.mc.minigamemiscs.MinigameMiscs;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class TintRemoveCommand extends CommandNode {

    public TintRemoveCommand(CommandNode parent) {
        super(parent, "remove", null, "tint remove command", null);
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        MinigameMiscs.getApi().getTintManager().removeTint(player);
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
