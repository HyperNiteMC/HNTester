package com.ericlam.mc.hntester.command;

import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class StackCommand extends CommandNode {

    public StackCommand(CommandNode parent) {
        super(parent, "stack", null, "stack  test", null);
    }

    @Override
    public boolean executeCommand(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;
        Map<ItemStack, Integer> count = new LinkedHashMap<>();
        var items = player.getInventory().getStorageContents();
        if (items.length == 0) {
            commandSender.sendMessage("empty");
            return true;
        }
        for (ItemStack itemStack : items) {
            if (itemStack == null || itemStack.getType() == Material.AIR) continue;
            count.computeIfPresent(itemStack, (item, c) -> c + item.getAmount());
            count.putIfAbsent(itemStack, itemStack.getAmount());
            player.getInventory().removeItem(itemStack);
        }
        count.forEach((i, c) -> {
            player.sendMessage("item: " + i.getType() + " amount: " + c);
            var item = i;
            while (c > 64) {
                item.setAmount(64);
                player.getInventory().addItem(item);
                item = i.clone();
                c -= 64;
            }
            if (c > 0) {
                item.setAmount(c);
                player.getInventory().addItem(item);
            }
        });
        player.sendMessage("done");
        return true;
    }

    @Override
    public List<String> executeTabCompletion(@NotNull CommandSender commandSender, @NotNull List<String> list) {
        return null;
    }
}
