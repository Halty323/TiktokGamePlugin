package com.halty.tiktokgame.commands;

import com.halty.tiktokgame.utils.GameUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        GameUtil.stopGame();
        return true;
    }
}
