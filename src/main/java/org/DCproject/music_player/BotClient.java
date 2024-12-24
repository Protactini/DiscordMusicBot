package org.DCproject.music_player;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.examples.command.AboutCommand;
import net.dv8tion.jda.api.Permission;
import org.DCproject.MainBotParameters;

import java.awt.*;

/*
 * This class is used for building all CommandClient for this bot
 * Each big scoop should have its own CommandClient
 */
public class BotClient {

    private final Permission[] RECOMMENDED_PERMS = new Permission[]{Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND, Permission.MESSAGE_HISTORY, Permission.MESSAGE_ADD_REACTION,
        Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_MANAGE, Permission.MESSAGE_EXT_EMOJI,
        Permission.VOICE_CONNECT, Permission.VOICE_SPEAK, Permission.NICKNAME_CHANGE};
    /*
     * This is the CommandClient for music functions
     */
    public CommandClient createMusicCommandClient(MainBotParameters mainBotParameters, String version) {
        // About Command
        AboutCommand aboutCommand = new AboutCommand(Color.BLUE.brighter(),
            "a music bot that is [easy to host yourself!](https://github.com/jagrosh/MusicBot) (v" +  version + ")",
            new String[]{"High-quality music playback", "FairQueue™ Technology"},
            RECOMMENDED_PERMS);
        CommandClientBuilder commandClient = new CommandClientBuilder()
            .setPrefix(mainBotParameters.getPrefix())
            .setAlternativePrefix(mainBotParameters.getAltPrefix())
            .setOwnerId(Long.toString(mainBotParameters.getOwnerId()))
            .setEmojis(mainBotParameters.getSuccess(), mainBotParameters.getWarning(), mainBotParameters.getError())
            .setHelpWord(mainBotParameters.getHelp())
            .setLinkedCacheSize(200)
//            .setGuildSettingsManager(mainBotParameters)
            .addCommands(aboutCommand);


        return commandClient.build();
    }
}
