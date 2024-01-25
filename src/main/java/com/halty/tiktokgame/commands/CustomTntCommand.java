package com.halty.tiktokgame.commands;

import com.halty.tiktokgame.utils.GameUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomTntCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        GameUtil.tntCustom(Integer.parseInt(strings[0]), (Player) commandSender, Integer.parseInt(strings[1]));
        return true;
    }


}
