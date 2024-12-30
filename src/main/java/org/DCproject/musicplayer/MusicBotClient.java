package org.DCproject.musicplayer;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.examples.command.AboutCommand;
import org.DCproject.*;

import java.awt.*;

/*
 * This class is used for building all CommandClient for this bot
 * Each big scoop should have its own CommandClient
 */
public class MusicBotClient {

    /*
     * This is the CommandClient for music functions
     */
    public CommandClient createMusicCommandClient(MainBotParameters mainBotParameters, String version) {
        // About Command
        AboutCommand aboutCommand = new AboutCommand(Color.BLUE.brighter(),
            "a music bot that is [easy to host yourself!](https://github.com/jagrosh/MusicBot) (v" +  version + ")",
            new String[]{"High-quality music playback", "FairQueue™ Technology"},
            MainBotParameters.RECOMMENDED_PERMS);
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
