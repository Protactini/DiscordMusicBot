package org.DCproject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import com.typesafe.config.*;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Activity;
import org.DCproject.logger.BotLogger;
import org.DCproject.logger.LogContext;
import org.DCproject.logger.LogLevel;

public class MainBotParameters {
    //Permission for this Bot
    public static final Permission[] RECOMMENDED_PERMS = new Permission[]{Permission.VIEW_CHANNEL, Permission.MESSAGE_SEND, Permission.MESSAGE_HISTORY, Permission.MESSAGE_ADD_REACTION,
        Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_MANAGE, Permission.MESSAGE_EXT_EMOJI,
        Permission.VOICE_CONNECT, Permission.VOICE_SPEAK, Permission.NICKNAME_CHANGE};
    // Parameter used for reading config of the main Bot (Conbination of different function bots)
    private final Config config;
    private final static LogContext CONTEXT = LogContext.Config;

    //logger for the bot
    private final BotLogger botLogger;

    private Path path = null;
    private String token, prefix, altprefix, helpWord, playlistsFolder, logLevel,
        successEmoji, warningEmoji, errorEmoji, loadingEmoji, searchingEmoji,
        evalEngine;
    private boolean stayInChannel, songInGame, npImages, updatealerts, useEval, dbots;
    private long owner, maxSeconds, aloneTimeUntilStop;
    private int maxYTPlaylistPages;
    private double skipratio;
    private OnlineStatus status;
    private Activity game;
    private Config aliases, transforms;

    private boolean valid = false;

    public MainBotParameters(Config config, BotLogger botLogger)
    {
        this.config = config;
        this.botLogger = botLogger;
    }

    public void load()
    {
        valid = false;

        // set values
        token = config.getString("token");
        prefix = config.getString("prefix");
        altprefix = config.getString("altprefix");
        helpWord = config.getString("help");

        successEmoji = config.getString("success");
        warningEmoji = config.getString("warning");
        errorEmoji = config.getString("error");
        loadingEmoji = config.getString("loading");
        searchingEmoji = config.getString("searching");
//        game = OtherUtil.parseGame(config.getString("game"));
//        status = OtherUtil.parseStatus(config.getString("status"));
        stayInChannel = config.getBoolean("stayinchannel");
        songInGame = config.getBoolean("songinstatus");
        npImages = config.getBoolean("npimages");
        updatealerts = config.getBoolean("updatealerts");
        logLevel = config.getString("loglevel");
        useEval = config.getBoolean("eval");
        evalEngine = config.getString("evalengine");
        maxSeconds = config.getLong("maxtime");
        maxYTPlaylistPages = config.getInt("maxytplaylistpages");
        aloneTimeUntilStop = config.getLong("alonetimeuntilstop");
        playlistsFolder = config.getString("playlistsfolder");
        aliases = config.getConfig("aliases");
        transforms = config.getConfig("transforms");
        skipratio = config.getDouble("skipratio");
        dbots = owner == 113156185389092864L;

        // Checking if the token and owner is correct
        try{
            // Retrieve the bot token
            if (token == null || token.isEmpty()) {
                throw new IllegalArgumentException("BOT_TOKEN not found in config.env");
            }
            if (owner <= 0) {
                throw new IllegalArgumentException("Invalid User ID! Exiting not found in config.env");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        valid = true;
        botLogger.alert(LogLevel.INFO, CONTEXT, "Bot parameters loaded successfully");
    }

    // Getter from here
    public boolean isValid()
    {
        return valid;
    }

    public String getConfigLocation()
    {
        return path.toFile().getAbsolutePath();
    }

    public String getPrefix()
    {
        return prefix;
    }

    public String getAltPrefix()
    {
        return "NONE".equalsIgnoreCase(altprefix) ? null : altprefix;
    }

    public String getToken()
    {
        return token;
    }

    public double getSkipRatio()
    {
        return skipratio;
    }

    public long getOwnerId()
    {
        return owner;
    }

    public String getSuccess()
    {
        return successEmoji;
    }

    public String getWarning()
    {
        return warningEmoji;
    }

    public String getError()
    {
        return errorEmoji;
    }

    public String getLoading()
    {
        return loadingEmoji;
    }

    public String getSearching()
    {
        return searchingEmoji;
    }

    public Activity getGame()
    {
        return game;
    }

    public boolean isGameNone()
    {
        return game != null && game.getName().equalsIgnoreCase("none");
    }

    public OnlineStatus getStatus()
    {
        return status;
    }

    public String getHelp()
    {
        return helpWord;
    }

    public boolean getStay()
    {
        return stayInChannel;
    }

    public boolean getSongInStatus()
    {
        return songInGame;
    }

    public String getPlaylistsFolder()
    {
        return playlistsFolder;
    }

    public boolean getDBots()
    {
        return dbots;
    }

    public boolean useUpdateAlerts()
    {
        return updatealerts;
    }

    public String getLogLevel()
    {
        return logLevel;
    }

    public boolean useEval()
    {
        return useEval;
    }

    public String getEvalEngine()
    {
        return evalEngine;
    }

    public boolean useNPImages()
    {
        return npImages;
    }

    public long getMaxSeconds()
    {
        return maxSeconds;
    }

    public int getMaxYTPlaylistPages()
    {
        return maxYTPlaylistPages;
    }

//    public String getMaxTime()
//    {
//        return TimeUtil.formatTime(maxSeconds * 1000);
//    }

    public long getAloneTimeUntilStop()
    {
        return aloneTimeUntilStop;
    }

//    public boolean isTooLong(AudioTrack track)
//    {
//        if(maxSeconds<=0)
//            return false;
//        return Math.round(track.getDuration()/1000.0) > maxSeconds;
//    }

    public String[] getAliases(String command)
    {
        try
        {
            return aliases.getStringList(command).toArray(new String[0]);
        }
        catch(NullPointerException | ConfigException.Missing e)
        {
            return new String[0];
        }
    }

    public Config getTransforms()
    {
        return transforms;
    }
}
