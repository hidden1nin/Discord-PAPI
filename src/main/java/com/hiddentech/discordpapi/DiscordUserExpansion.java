package com.hiddentech.discordpapi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.hiddentech.discordpapi.DiscordPAPI.isInteger;

public class DiscordUserExpansion extends PlaceholderExpansion {
    private final List<String> list = new ArrayList<String>(){{add("1-99");}};
    private final DiscordPAPI plugin;

    public DiscordUserExpansion(DiscordPAPI plugin){
        this.plugin = plugin;
    }
    @Override
    public @NotNull String getIdentifier() {
        return "DUser";
    }

    @Override
    public @Nullable String getRequiredPlugin() {
        return null;
    }

    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }
    @Override
    public boolean persist(){
        return true;
    }
    @Override
    public boolean canRegister(){
        return true;
    }
    @Override
    public @NotNull List<String> getPlaceholders() {
        return list;
    }
    @Override
    public String onRequest(OfflinePlayer player, String identifier){
        if(player == null){
            return "";
        }
        if(!isInteger(identifier))return null;
        int msg = Integer.parseInt(identifier);
        if(plugin.users.size()>msg) return plugin.users.get(msg);
        return null;
    }


}
