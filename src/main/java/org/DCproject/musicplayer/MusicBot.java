package org.DCproject.musicplayer;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.DCproject.MainBotParameters;
import org.DCproject.logger.BotLogger;
import org.DCproject.logger.LogContext;
import org.DCproject.logger.LogLevel;

import java.util.concurrent.TimeUnit;

public class MusicBot extends ListenerAdapter {
    private final LogContext CONTEXT = LogContext.MusicBot;
    private final BotLogger LOGGER;

    public MusicBot(BotLogger LOGGER) {
        this.LOGGER = LOGGER;
    }


//    public void onMessageReceived(MessageReceivedEvent event) {
//        if (!event.isFromGuild()) return;
//        // This makes sure we only execute our code when someone sends a message with "!play"
//        if (!event.getMessage().getContentRaw().startsWith("!play")) return;
//        // Now we want to exclude messages from bots since we want to avoid command loops in chat!
//        // this will include own messages as well for bot accounts
//        // if this is not a bot make sure to check if this message is sent by yourself!
//        if (event.getAuthor().isBot()) return;
//
//        // Get the channel of the user sending the information
//        Channel channel  = event.getMember().getVoiceState().getChannel();
//        AudioManager audioManager = event.getGuild().getAudioManager();
//
//        //Get player
//        AudioPlayer player = getMusic();
//
//        //Setting the music bot here
//        audioManager.setSendingHandler(new AudioPlayerSendHandler(player));
//        // send the bot to the channel
//        audioManager.openAudioConnection((AudioChannel) channel);
//
//    }
//
//    private AudioPlayer getMusic(){
//        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
//        AudioSourceManagers.registerRemoteSources(playerManager);
//
//        AudioPlayer player = playerManager.createPlayer();
//
//        return player;
//    }
}
