package com.hiddentech.discordpapi;

import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class DiscordListeners extends ListenerAdapter {
    private final DiscordPAPI plugin;

    public DiscordListeners(DiscordPAPI plugin){
        this.plugin = plugin;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        plugin.messages.add(0,event.getMessage().getContentRaw());
        plugin.users.add(0,event.getAuthor().getName());
        plugin.channels.add(0,event.getChannel().getName());
        if(plugin.messages.size()>100) plugin.messages.pop();
        if(plugin.users.size()>100) plugin.messages.pop();
        if(plugin.channels.size()>100) plugin.messages.pop();
    }
}
