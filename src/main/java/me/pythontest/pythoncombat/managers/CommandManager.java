package me.pythontest.pythoncombat.managers;

import me.pythontest.pythoncombat.Commands.DuelCommand;
import me.pythontest.pythoncombat.Commands.PCinfoCommand;
import me.pythontest.pythoncombat.Commands.PvpCommand;
import me.pythontest.pythoncombat.TabCompleters.DuelCompleter;
import me.pythontest.pythoncombat.TabCompleters.PvpCompleter;
import me.pythontest.pythoncombat.pythoncombat.Pythoncombat;

public class CommandManager {
    private static Pythoncombat plugin;
    public CommandManager(Pythoncombat plugin){
        this.plugin = plugin;
    }
    public static PvpCommand pvpCommand;
    public static PCinfoCommand pcinfoCommand;
    public static DuelCommand duelCommand;
    public static DuelCompleter duelCompleter;
    public void registercommands(){
         pcinfoCommand = new PCinfoCommand();
         pvpCommand = new PvpCommand(plugin);
         duelCommand = new DuelCommand(plugin);
         duelCompleter = new DuelCompleter(plugin);
        plugin.getCommand("pcinfo").setExecutor(pcinfoCommand);
        plugin.getCommand("pvp").setExecutor(pvpCommand);
        plugin.getCommand("pvp").setTabCompleter(new PvpCompleter());
        plugin.getCommand("duel").setExecutor(duelCommand);
        plugin.getCommand("duel").setTabCompleter(duelCompleter);
    }
}
