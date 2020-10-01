package com.ericlam.mc.hntester.command;

import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public final class TintCommand extends DefaultCommand {

    public TintCommand(CommandNode parent) {
        super(parent, "tint", null, "tint command");
        this.addSub(new TintAddCommand(this));
        this.addSub(new TintRemoveCommand(this));
    }
}
