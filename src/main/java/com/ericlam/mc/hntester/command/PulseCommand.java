package com.ericlam.mc.hntester.command;

import com.hypernite.mc.hnmc.core.misc.commands.CommandNode;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public final class PulseCommand extends DefaultCommand {

    public PulseCommand(CommandNode parent) {
        super(parent, "pulse", null, "pulse command");
        this.addSub(new PulseAddCommand(this));
        this.addSub(new PulseRemoveCommand(this));
    }
}
