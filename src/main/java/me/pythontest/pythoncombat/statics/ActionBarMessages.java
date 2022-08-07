package me.pythontest.pythoncombat.statics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;

import java.awt.*;
import java.text.DecimalFormat;

public class ActionBarMessages {
    public static Component Timeoutbar(double now,double max){
        Component message = Component.empty();
        Component nowText  = Component.text(new DecimalFormat("##.#").format(now));
        Component maxText = Component.text(" / "+max);
        nowText = nowText.color(TextColor.fromHexString("#ff0000"));
        message = message.append(nowText);
        message = message.append(maxText);
        return  message;
    }
}
