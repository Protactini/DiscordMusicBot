package org.DCproject.logger;

public enum LogContext {
    Config ("config"),
    MusicBot("musicbot"),
    MainBot("mainbot"),
    MainBotParameter("mainbotparameter");

    private String description;

    LogContext(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
