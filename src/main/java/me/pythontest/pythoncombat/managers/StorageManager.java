package me.pythontest.pythoncombat.managers;

import me.pythontest.pythoncombat.objects.PCPlayer;
import me.pythontest.pythoncombat.pythoncombat.Pythoncombat;

import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    private Pythoncombat plugin;
    public StorageManager(Pythoncombat plugin){
        this.plugin = plugin;

    }
    private List<PCPlayer> PlayerList = new ArrayList<PCPlayer>();
    public void addPlayer(String id){
        PlayerList.add(new PCPlayer(id,plugin));
    }
    public void removePlayer(String id){
        for (int i = 0; i < PlayerList.size(); i++) {
            if(PlayerList.get(i).getId().equals(id))
                PlayerList.remove(i);
        }
    }
    public PCPlayer getPlayer(String id){
        for (PCPlayer pcPlayer : PlayerList) {
            if(pcPlayer.getId().equals(id))
                return pcPlayer;
        }
        return null;
    }
}
