package com.tecknix.clickdelay.listener;

import com.tecknix.clickdelay.TCClickDelayAPI;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRegisterChannelEvent;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PlayerListener implements Listener {

    private final TCClickDelayAPI plugin;

    private final List<Player> sendList = new ArrayList<>();

    @EventHandler(priority = EventPriority.LOWEST)
    public void onRegister(PlayerRegisterChannelEvent event) {
        if (event.getChannel().equals("Tecknix-Client"))  {
            //Add the player to the sendlist.
            this.sendList.add(event.getPlayer());
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(this.plugin.getPlugin(), () -> {

            final Player player = event.getPlayer();

            if (this.sendList.contains(player)) {
                //If the sendlist contains the player.
                this.plugin.sendCooldownMessage(player);
                //Send the message and remove them from the list.
                this.sendList.remove(player);
            }
        }, 2 * 20L);
    }
}
