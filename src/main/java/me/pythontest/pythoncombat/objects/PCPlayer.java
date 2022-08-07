package me.pythontest.pythoncombat.objects;

import javafx.util.Pair;
import me.pythontest.pythoncombat.pythoncombat.Pythoncombat;
import me.pythontest.pythoncombat.statics.ActionBarMessages;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import java.util.*;
public class PCPlayer {
    private List<String> duels = new ArrayList<String>();
    private List<String> duelsInvites = new ArrayList<String>();
    private List<Pair<Long,String>> timeouts = new ArrayList<Pair<Long,String>>();
    private boolean pvpEnabled = false;
    private String id;
    private Pythoncombat plugin;
    private Timer timeoutTimer = null;
    private Player player;
    //aka combatlog time
    static public int TimeoutTime = 30;
    private Pair<Long,String> Lasthit=null;
    public PCPlayer(String id,Pythoncombat plugin){
        this.id = id;
        this.plugin = plugin;
        this.player = plugin.getServer().getPlayer(this.id);
    }
    public String getId(){
        return this.id;
    }
    public boolean isInDuel(String id){
        for (String duel : duels) {
            if(duel.equals("id"))
                return true;
        }
        return false;
    }
    public void addDuel(String id){
        duels.add(id);
    }
    public void removeDuel(String id){
        for (int i = 0; i < duels.size(); i++) {
            if(duels.get(i).equals(id))
                duels.remove(i);
        }
    }
    public boolean getPvpStatus(){
        return this.pvpEnabled;
    }
    public void setPvpStatus(boolean status){
        this.pvpEnabled = status;
    }
    public void addTimeout(String id){
        Integer internalId = getIdOfTimeout(id);
        Date now = new Date();
        Long timeoutTime = now.getTime();
        timeoutTime+=this.TimeoutTime*1000;
        if(internalId instanceof Integer){
            timeouts.set(internalId,new Pair<>(timeoutTime,id));
        }
        else
            timeouts.add(new Pair<>(timeoutTime,id));

    }
    public Long getTimeout(String id){
        Integer internalId = getIdOfTimeout(id);
        if(internalId instanceof Integer)
            return timeouts.get(internalId).getKey();
        return null;

    }
    private Integer getIdOfTimeout(String id){
        for (int i = 0; i < timeouts.size(); i++) {
            if(timeouts.get(i).getValue().equals(id))
                return i;
        }
        return null;
    }
    public void registerHit(String name,String id, Player thisplayer){
        Date now = new Date();
        Lasthit = new Pair<>(now.getTime(),id);
        this.addTimeout(id);
        if(this.timeoutTimer!=null){
            try{
                timeoutTimer.cancel();
            }
            catch(IllegalStateException e){

            }
        }
        timeoutTimer=new Timer();
        player = thisplayer;
        player.sendActionBar(ActionBarMessages.Timeoutbar(TimeoutTime,TimeoutTime));
        timeoutTimer.scheduleAtFixedRate(new TimerTask() {
            private double time = TimeoutTime;
            @Override
            public void run() {
                if(time<=0)
                    try{
                        player.sendActionBar(Component.empty());
                    this.cancel();
            }
            catch(IllegalStateException e){

            }
                else{
                    player.sendActionBar(ActionBarMessages.Timeoutbar(time,TimeoutTime));
                }
                time-=0.1;

            }
        },100,100);
    }
    public Pair<Long,String> getlastHit(){
        return this.Lasthit;
    }
    public boolean createInvite(String id){
        boolean exist = existInvite(id);
        if(!existInvite(id))
            duelsInvites.add(id);
        return !exist;
    }
    public boolean existInvite(String id){
        for (String duelsInvite : duelsInvites) {
            if(duelsInvite.equals(id))
                return true;
        }
        return false;
    }
    public void removeInvite(String id){
        for (int i = 0; i < duelsInvites.size(); i++) {
            if(duelsInvites.get(i).equals(id)){
                duelsInvites.remove(i);
                return;
            }

        }
    }
    public void removeAllDuels(){
        duels = new ArrayList<String>();
    }
    public List<String> getDuels(){
        return duels;
    }
    public List<String> getDuelsInvites(){
        return  duelsInvites;
    }
}
