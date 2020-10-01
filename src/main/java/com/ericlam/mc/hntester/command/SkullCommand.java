package com.ericlam.mc.hntester.command;

import com.hypernite.mc.hnmc.core.builders.ItemStackBuilder;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class SkullCommand extends CommandNode {

    public SkullCommand(CommandNode parent) {
        super(parent, "skull", null, "skin skull command", null);
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        new ItemStackBuilder(Material.PLAYER_HEAD).displayName(player.getName() + "'s head").head(player.getUniqueId()).buildWithSkin(item -> {
            player.getInventory().addItem(item);
        });
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
