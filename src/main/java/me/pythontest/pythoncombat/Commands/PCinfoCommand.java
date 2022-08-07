package me.pythontest.pythoncombat.Commands;

import me.pythontest.pythoncombat.statics.ChatMessages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PCinfoCommand implements CommandExecutor  {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        sender.sendMessage(ChatMessages.PCinfo());
        return true;
    }
}
