package com.ericlam.mc.hntester;

import com.ericlam.mc.hntester.command.*;
import com.hypernite.mc.hnmc.core.misc.commands.DefaultCommand;

public final class TestCommand extends DefaultCommand {

    public TestCommand() {
        super(null, "test", "test.use", "test command");
        this.addSub(new SkullCommand(this));
        this.addSub(new HeadCommand(this));
        this.addSub(new TintCommand(this));
        this.addSub(new PulseCommand(this));
        this.addSub(new StackCommand(this));
        this.addSub(new ScoreboardCommand(this));
    }
}
