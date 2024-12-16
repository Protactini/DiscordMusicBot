package org.example;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.example.functions.MusicBot;

public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.configure()
            .filename("config.env")
            .load();

        // Retrieve the bot token
        String token = dotenv.get("BOT_TOKEN");

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("BOT_TOKEN not found in config.env");
        }

        JDA api = JDABuilder.createDefault(token)
                .addEventListeners(new MusicBot())
                .build();
    }
}