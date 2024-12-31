package org.DCproject.settings;

import com.jagrosh.jdautilities.command.GuildSettingsManager;
import net.dv8tion.jda.api.entities.Guild;
import org.DCproject.logger.LogContext;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


public class SettingManager implements GuildSettingsManager<Settings> {
    private final static Logger LOGGER = LoggerFactory.getLogger(LogContext.Setting.toString());
    private final static String SETTINGS_FILE = "resources/serversettings.json";
    private final HashMap<Long,Settings> settingMap = new HashMap<>();

    /**
     * Constructor of the Setting Management
     * User don't need to write serverseting.jason file this file is used to store previous setting for each Guild when the
     * bot restarted or shut down
     * This function will first read the settings in the JASON file and load the guildId and setting pair data into the settingMap
     */
    public SettingManager() {
        this.settings = new HashMap<>();


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
