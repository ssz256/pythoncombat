package me.pythontest.pythoncombat.managers;

import javafx.util.Pair;
import jdk.javadoc.internal.doclets.toolkit.util.Utils;
import me.pythontest.pythoncombat.objects.PCPlayer;
import org.bukkit.entity.*;

import java.util.Date;

public class PvpManager {
    Player lastCrystalDamager = null;
    public static boolean canDamage(PCPlayer damager, PCPlayer damaged){
        if(damaged.getId().equals(damager.getId()))
            return true;
        if(damager.isInDuel(damaged.getId()))
            return true;
        Long Timeout = damager.getTimeout(damaged.getId());
        Date now = new Date();
        if(damager.getTimeout(damaged.getId()) instanceof Long && Timeout>now.getTime())
            return true;
        if(damager.getPvpStatus()&&damaged.getPvpStatus())
            return true;
        return false;
    }
    public static boolean canDisconnect(PCPlayer player){
        Pair<Long,String> lasthit = player.getlastHit();
        if(lasthit!=null){
            Date now = new Date();
            if(lasthit.getKey()+PCPlayer.TimeoutTime*1000>now.getTime())
                return false;
        }
        return true;
    }
    public static boolean isDamagingEntity(Entity entity){
        if(entity instanceof Player)
            return true;
        if(entity instanceof TNTPrimed&&((TNTPrimed) entity).getSource()instanceof Player)
            return true;
        if(entity instanceof Arrow&&((Arrow) entity).getShooter() instanceof Player)
            return true;
        return false;
    }
    public static Player GetDamager(Entity entity){
        Player damager=null;
        if(entity instanceof Arrow&&((Arrow) entity).getShooter() instanceof Player){
            damager = (Player) ((Arrow) entity).getShooter();
            ((Arrow) entity).remove();
        } else if (entity instanceof TNTPrimed&&((TNTPrimed) entity).getSource()instanceof Player) {
            damager = (Player) ((TNTPrimed) entity).getSource();
        } else if (entity instanceof EnderCrystal) {
            
        } else
            damager = (Player) entity;
        return damager;
    }
}
