package org.DCproject;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.DCproject.logger.BotLogger;
import org.DCproject.music_player.MusicBot;
import org.DCproject.music_player.MusicBotClient;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String token;
        File customConfigFile = new File("resources/config.env");
        Config config = ConfigFactory.parseFile(customConfigFile);
        BotLogger botLogger = new BotLogger();


        MainBotParameters mainBotParameters = new MainBotParameters(config, botLogger);
        mainBotParameters.load();

        JDA api = JDABuilder.createDefault(mainBotParameters.getToken())
                .addEventListeners(
                    new EventWaiter(),
                    new MusicBotClient().createMusicCommandClient(mainBotParameters, "1.0.0"),
                    new MusicBot())
                .build();
    }
}