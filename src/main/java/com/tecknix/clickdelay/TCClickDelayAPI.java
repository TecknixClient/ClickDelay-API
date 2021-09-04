package com.tecknix.clickdelay;

import com.tecknix.clickdelay.listener.PlayerListener;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TCClickDelayAPI {

    @Getter private final JavaPlugin plugin;

    @Setter private boolean disabled;

    /**
     * Registers the plugin. This allows us to implement this api into any plugin!
     *
     * @param plugin - An instance of a plugin.
     */
    public TCClickDelayAPI(JavaPlugin plugin) {
        this.plugin = plugin;

        //Register the outgoing "Tecknix-Client" Channel.
        this.plugin.getServer().getMessenger().registerOutgoingPluginChannel(this.plugin, "Tecknix-Client");

        //Register the Join Listener.
        this.plugin.getServer().getPluginManager().registerEvents(new PlayerListener(this), this.plugin);
    }

    /**
     * Sends the client a plugin message telling it to disable the click delay timer.
     *
     * @param player - A Bukkit player object.
     */
    public void sendCooldownMessage(Player player) {
        final ByteBuf buffer = Unpooled.buffer();

        //Writes the cooldown packets ID as a byte.
        //This is done because the client uses packets normally.
        //Here we are sending a single thing.
        //To see the packet system reference our bukkit api.
        buffer.writeByte(3);

        //Writes the state of the cooldown.
        buffer.writeBoolean(this.disabled);

        //Send the plugin message to the player on the client's channel.
        player.sendPluginMessage(this.plugin, "Tecknix-Client", buffer.array());
    }
}
