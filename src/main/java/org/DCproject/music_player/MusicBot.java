package org.DCproject.music_player;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.DCproject.music_player.audio_functions.AudioPlayerSendHandler;

public class MusicBot extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (!event.isFromGuild()) return;
        // This makes sure we only execute our code when someone sends a message with "!play"
        if (!event.getMessage().getContentRaw().startsWith("!play")) return;
        // Now we want to exclude messages from bots since we want to avoid command loops in chat!
        // this will include own messages as well for bot accounts
        // if this is not a bot make sure to check if this message is sent by yourself!
        if (event.getAuthor().isBot()) return;

        // Get the channel of the user sending the information
        Channel channel  = event.getMember().getVoiceState().getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        //Get player
        AudioPlayer player = getMusic();

        //Setting the music bot here
        audioManager.setSendingHandler(new AudioPlayerSendHandler(player));
        // send the bot to the channel
        audioManager.openAudioConnection((AudioChannel) channel);

    }

    private AudioPlayer getMusic(){
        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        AudioPlayer player = playerManager.createPlayer();

        return player;
    }


}
