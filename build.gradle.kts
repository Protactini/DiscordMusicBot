plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("net.dv8tion:JDA:5.2.1")
    // https://mvnrepository.com/artifact/io.github.cdimascio/java-dotenv
    implementation ("io.github.cdimascio:java-dotenv:5.2.2")
    implementation ("dev.arbjerg:lavaplayer:2.2.2")

}

tasks.test {
    useJUnitPlatform()
}