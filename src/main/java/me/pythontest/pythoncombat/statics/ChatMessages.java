package me.pythontest.pythoncombat.statics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class ChatMessages {
    public static Component CantFight(Player damaged){
        Component message = Component.empty();
        Component cantDamageText = Component.text("Nie możesz walczyć z "+damaged.getName()+". Jeżeli chcesz z nim walczyć ");
        cantDamageText = cantDamageText.color(TextColor.fromHexString("#AA0000"));
        Component duelInvite = Component.text("zaproś go do duel");
        duelInvite = duelInvite.style(Style.style(TextDecoration.UNDERLINED));
        duelInvite = duelInvite.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND,"/duel invite "+damaged.getName()));
        message = message.append(cantDamageText);
        message = message.append(duelInvite);
        return message;
    }
    public static Component PCinfo(){
        Component message = Component.text("Autorem pluginu pythoncombat jest pythontest");
        return message;
    }
    public static Component NotPlayer(){
        return Component.text("Aby użyć tej komendy musisz być graczem");
    }
    public static Component LowArgs(){
        return Component.text("Za mało argumentów");
    }
    public static Component PvpUpdate(String state){
        return Component.text("Pomyślnie zmieniono status pvp na: "+state).color(TextColor.fromHexString("#32CD32"));
    }
    public static Component UnknownUser(String username){
        return Component.text("Nieznana nazwa użytkownika: "+username).color(TextColor.fromHexString("#FF0000"));
    }
    public static Component DuelInviteCreated(String username){
        Component message = Component.empty();
        Component cantDamageText = Component.text("Użytkownik "+username+"zaprosił cię do duel. Jeżeli chcesz z nim walczyć ");
        cantDamageText = cantDamageText.color(TextColor.fromHexString("#32CD32"));
        Component duelInvite = Component.text("kliknij tutaj");
        duelInvite = duelInvite.style(Style.style(TextDecoration.UNDERLINED));
        duelInvite = duelInvite.clickEvent(ClickEvent.clickEvent(ClickEvent.Action.RUN_COMMAND,"/duel accept "+username));
        message = message.append(cantDamageText);
        message = message.append(duelInvite);
        return message;
    }
    public static Component DuelInviteSended(String username){
        return Component.text("Wysłano zaproszenie do walki do użytkownika "+username).color(TextColor.fromHexString("#32CD32"));
    }
    public static Component DuelInviteFailed(){
        return Component.text("Nie udało się wyslać zaproszenia").color(TextColor.fromHexString("#ff0000"));
    }
    public static Component DuelInviteNotExist(String username){
        return Component.text("Zaproszenie od użytkownika "+username+" nie istnieje").color(TextColor.fromHexString("#ff0000"));
    }
    public static Component DuelInviteAccepted(String username,Boolean to){
        return Component.text("Zaproszenie "+(to?"do":"od")+" użytkownika "+username+" zostało zaakceptowane. Możecie walczyć").color(TextColor.fromHexString("#32CD32"));
    }
    public static Component DuelInviteDeclined(String username){
        return Component.text("Pomyślnie odrzucono zaproszenie od "+username).color(TextColor.fromHexString("#32CD32"));
    }
    public static Component DuelNotExist(String username){
        return Component.text("Duel z użytkownikiem "+username+" nie istnieje").color(TextColor.fromHexString("#ff0000"));
    }
    public static Component DuelRemove(String username){
        return Component.text("Duel z użytkownikiem "+username+" został usunięty").color(TextColor.fromHexString("#ff0000"));
    }
}
