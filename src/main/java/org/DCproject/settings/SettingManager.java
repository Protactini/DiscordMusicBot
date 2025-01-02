package org.DCproject.settings;

import com.jagrosh.jdautilities.command.GuildSettingsManager;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import net.dv8tion.jda.api.entities.Guild;
import org.DCproject.Main;
import org.DCproject.logger.BotLogger;
import org.DCproject.logger.LogContext;
import org.DCproject.logger.LogLevel;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;


public class SettingManager implements GuildSettingsManager<Settings> {
    // Setting the log of this class
    private final static BotLogger botLogger = Main.botLogger;
    private final static LogContext CONTEXT = LogContext.SettingManager;

    // Stored file for Guild and setting pair
    private final static String SETTINGS_FILE = "resources/serverSettings.yaml";
    private final HashMap<Long,Settings> settingsMap;

    /**
     * Constructor of the Setting Management
     * User don't need to write serverseting.jason file this file is used to store previous setting for each Guild when the
     * bot restarted or shut down
     * This function will first read the settings in the JASON file and load the guildId and setting pair data into the settingMap
     */
    public SettingManager() {
        this.settingsMap = new HashMap<>();

        try {
            Config loadedSettings = ConfigFactory.parseFile(new File(SETTINGS_FILE));

            for (Map.Entry<String, ConfigValue> entry : loadedSettings.root().entrySet()) {
                String id = entry.getKey();
                Config config = loadedSettings.getConfig(id);

                // Legacy version support: Check for old repeat mode representation
                RepeatMode repeatMode = config.hasPath("repeat_mode")
                    ? RepeatMode.valueOf(config.getString("repeat_mode"))
                    : (config.hasPath("repeat") && config.getBoolean("repeat")) ? RepeatMode.ALL : RepeatMode.OFF;

                settingsMap.put(Long.parseLong(id), new Settings(this,
                    config.hasPath("text_channel_id") ? config.getString("text_channel_id") : null,
                    config.hasPath("voice_channel_id") ? config.getString("voice_channel_id") : null,
                    config.hasPath("dj_role_id") ? config.getString("dj_role_id") : null,
                    config.hasPath("volume") ? config.getInt("volume") : 100,
                    config.hasPath("default_playlist") ? config.getString("default_playlist") : null,
                    repeatMode,
                    config.hasPath("prefix") ? config.getString("prefix") : null,
                    config.hasPath("skip_ratio") ? config.getDouble("skip_ratio") : -1,
                    config.hasPath("queue_type") ? QueueType.valueOf(config.getString("queue_type")) : QueueType.FAIR
                ));

            }
        } catch (NoSuchFileException e) {
            // create an empty json file
            try {
                LOG.info("serversettings.json will be created in " + OtherUtil.getPath("serversettings.json").toAbsolutePath());
                Files.write(OtherUtil.getPath("serversettings.json"), new JSONObject().toString(4).getBytes());
            } catch(IOException ex) {
                LOG.warn("Failed to create new settings file: "+ex);
            }
            return;
        } catch(IOException | JSONException e) {
            LOG.warn("Failed to load server settings: "+e);
        }

        LOG.info("serversettings.json loaded from " + OtherUtil.getPath("serversettings.json").toAbsolutePath());

    }


    @Nullable
    @Override
    public Settings getSettings(Guild guild) {
        return null;
    }

    @Override
    public void init() {
        GuildSettingsManager.super.init();
    }

    @Override
    public void shutdown() {
        GuildSettingsManager.super.shutdown();
    }
}
