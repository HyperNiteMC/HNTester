package com.ericlam.mc.hntester.command;

import com.hypernite.mc.hnmc.core.main.HyperNiteMC;
import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public final class HeadCommand extends CommandNode {

    public HeadCommand(CommandNode parent) {
        super(parent, "head", null, "skin head command", null);
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        Block b = player.getTargetBlock(30);
        if (b == null) {
            player.sendMessage("block is null");
            return true;
        }
        HyperNiteMC.getAPI().getPlayerSkinManager().setHeadBlock(player.getUniqueId(), b, false, player.getFacing().getOppositeFace());
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
