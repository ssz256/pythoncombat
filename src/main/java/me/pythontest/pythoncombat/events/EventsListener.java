package me.pythontest.pythoncombat.events;

import me.pythontest.pythoncombat.managers.PvpManager;
import me.pythontest.pythoncombat.objects.PCPlayer;
import me.pythontest.pythoncombat.pythoncombat.Pythoncombat;
import me.pythontest.pythoncombat.statics.ChatMessages;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class EventsListener implements Listener {
    private Pythoncombat plugin;
    private PvpManager pvpManager = new PvpManager();
    private double KillDamage = 4000*4000;
    public EventsListener(Pythoncombat plugin){
        this.plugin=plugin;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){
        plugin.getServer().getConsoleSender().sendMessage("triggered");
        if(PvpManager.isDamagingEntity(e.getDamager())){
            Player damager = PvpManager.GetDamager(e.getDamager());

            plugin.getServer().getConsoleSender().sendMessage("Player Instance");

            Player damaged = (Player) e.getEntity();
            PCPlayer Pcdamager = plugin.getStorageManager().getPlayer(damager.getUniqueId().toString());
            PCPlayer Pcdamaged = plugin.getStorageManager().getPlayer(damaged.getUniqueId().toString());
            if(PvpManager.canDamage(Pcdamager,Pcdamaged)){
                e.setCancelled(false);
                Pcdamaged.registerHit(damager.getName(), Pcdamager.getId(),damaged);
                Pcdamager.registerHit(damaged.getName(), Pcdamaged.getId(),damager);
            }
            else{
                e.setCancelled(true);
                damager.sendMessage(ChatMessages.CantFight(damaged));

            }
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        PCPlayer pcPlayer = plugin.getStorageManager().getPlayer(player.getUniqueId().toString());
        if(!PvpManager.canDisconnect(pcPlayer)){
            String idOfDamager = pcPlayer.getlastHit().getValue();
            player.damage(this.KillDamage,plugin.getServer().getPlayer(UUID.fromString(idOfDamager)));
        }
        plugin.getStorageManager().removePlayer(player.getUniqueId().toString());
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        plugin.getStorageManager().addPlayer(player.getUniqueId().toString());
    }
}
