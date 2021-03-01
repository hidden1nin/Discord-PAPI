package com.hiddentech.discordpapi;

import me.clip.placeholderapi.PlaceholderAPI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.block.data.type.Gate;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Stack;
import java.util.logging.MemoryHandler;

public final class DiscordPAPI extends JavaPlugin {
    public JDA jda;
    public Stack<String> messages = new Stack<>();
    public Stack<String> users = new Stack<>();
    public Stack<String> channels = new Stack<>();
    private String token = "";
    @Override
    public void onEnable() {
        // Plugin startup logic
        try {
            startBot();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) {
            /*
             * We inform about the fact that PlaceholderAPI isn't installed and then
             * disable this plugin to prevent issues.
             */
            //no papi
            Bukkit.getLogger().warning("Could not find PlaceholderAPI! This plugin is required for Discord PAPI support! Shutting Down!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        new DiscordMessageExpansion(this).register();
        new DiscordUserExpansion(this).register();
        new DiscordChannelExpansion(this).register();
        jda.addEventListener(new DiscordListeners(this));
        jda.getuse
    }

    private void startBot() throws LoginException {
        if(getConfig().contains("token")){
            token = getConfig().getString("token");
        }else {
            getConfig().set("token","TOKEN-HERE");
            saveConfig();
        }
        jda = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES).setStatus(OnlineStatus.ONLINE).setActivity(Activity.listening("Discord PAPI stuff")).build();
    }

    @Override
    public void onDisable() {
        jda.shutdown();
        // Plugin shutdown logic
    }
    /*
https://stackoverflow.com/questions/5439529/determine-if-a-string-is-an-integer-in-java
 */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
