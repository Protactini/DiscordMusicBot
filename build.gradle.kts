plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven (url = "https://jitpack.io" ) // Add JitPack repository
    maven(url = "https://maven.lavalink.dev/releases")
    maven("https://m2.duncte123.dev/releases")
    maven {
        name = "arbjergDevReleases" // Custom name for the Lavalink releases repository
        url = uri("https://maven.lavalink.dev/releases")
    }
}

dependencies {
    // https://mvnrepository.com/artifact/io.github.cdimascio/java-dotenv
    implementation ("io.github.cdimascio:java-dotenv:5.2.2")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("net.dv8tion:JDA:5.2.1")
    // https://mvnrepository.com/artifact/com.jagrosh/jda-utilities
    implementation ("com.github.Nagami-Yukki:JDA-Utilities:1.0")


    implementation ("dev.arbjerg:lavaplayer:2.2.2")
    implementation("dev.lavalink.youtube:common:1.11.2")
    // https://mvnrepository.com/artifact/com.jagrosh/JLyrics
//    implementation("com.jagrosh:JLyrics:0.4")
//    implementation("com.dunctebot:sourcemanagers:1.5.2")

}

tasks.test {
    useJUnitPlatform()
}