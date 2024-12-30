package org.DCproject;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.DCproject.logger.BotLogger;
import org.DCproject.logger.LogContext;
import org.DCproject.logger.LogLevel;

import java.util.concurrent.TimeUnit;

public class MainBotStarter extends ListenerAdapter {
    private final LogContext CONTEXT = LogContext.MainBotStarter;
    private final BotLogger LOGGER;

    public MainBotStarter(BotLogger LOGGER) {
        this.LOGGER = LOGGER;
    }

    @Override
    public void onReady(ReadyEvent event) {
        if(event.getJDA().getGuildCache().isEmpty())
        {
            LOGGER.alert(LogLevel.WARNING, CONTEXT, "This bot is not on any guilds! Use the following link to add the bot to your guilds! \n"
                + event.getJDA().getInviteUrl(MainBotParameters.RECOMMENDED_PERMS));
        }

        event.getJDA().getGuilds().forEach((guild) ->
        {
            try
            {
                String defpl = bot.getSettingsManager().getSettings(guild).getDefaultPlaylist();
                VoiceChannel vc = bot.getSettingsManager().getSettings(guild).getVoiceChannel(guild);
                if(defpl!=null && vc!=null && bot.getPlayerManager().setUpHandler(guild).playFromDefault())
                {
                    guild.getAudioManager().openAudioConnection(vc);
                }
            }
            catch(Exception ignore) {}
        });
        if(bot.getConfig().useUpdateAlerts())
        {
            bot.getThreadpool().scheduleWithFixedDelay(() ->
            {
                try
                {
                    User owner = bot.getJDA().retrieveUserById(bot.getConfig().getOwnerId()).complete();
                    String currentVersion = OtherUtil.getCurrentVersion();
                    String latestVersion = OtherUtil.getLatestVersion();
                    if(latestVersion!=null && !currentVersion.equalsIgnoreCase(latestVersion))
                    {
                        String msg = String.format(OtherUtil.NEW_VERSION_AVAILABLE, currentVersion, latestVersion);
                        owner.openPrivateChannel().queue(pc -> pc.sendMessage(msg).queue());
                    }
                }
                catch(Exception ignored) {} // ignored
            }, 0, 24, TimeUnit.HOURS);
        }
    }
}
