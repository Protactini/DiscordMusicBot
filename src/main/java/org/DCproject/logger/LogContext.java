package org.DCproject.logger;

public enum LogContext {
    Config ("config"),

    MusicBot("musicbot"),
    MusicBotClient("musicbotclient"),
    MusicBotFunctions("musicbotfunctions"),

    MainBot("mainbot"),
    MainBotParameter("mainbotparameter"),
    MainBotStarter("mainbotstarter"),

    Setting("setting"),
    SettingManager("settingmanager"),;

    private String description;

    LogContext(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
