package com.ericlam.mc.hntester.command;

import com.ericlam.mc.minigamemiscs.MinigameMiscs;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class PulseRemoveCommand extends CommandNode {
    public PulseRemoveCommand(CommandNode parent) {
        super(parent, "remove", null, "pulse rmeove", null);
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        MinigameMiscs.getApi().getPulseManager().stopPulse(player);
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
