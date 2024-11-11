plugins {
    kotlin("jvm") version "2.0.21"

    // Provides the shadowJar task in Gradle
    // https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow
    id("com.github.johnrengelman.shadow") version "8.1.1"

}

group = "com.nickcoblentz.montoya.demos"
version = "0.1.0"

repositories {
    //add maven local in case you want to build some reusable libraries and host them within your home directory
    mavenLocal()

    mavenCentral()

    //Add two specific GitHub repositories in which maven packages can be found through jitpack.io
    maven(url="https://jitpack.io") {
        content {
            includeGroup("com.github.milchreis")
            includeGroup("com.github.ncoblentz")
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))

    // Include the Montoya API from Maven Central:
    // https://central.sonatype.com/artifact/net.portswigger.burp.extensions/montoya-api
    // Check for latest version: https://central.sonatype.com/artifact/net.portswigger.burp.extensions/montoya-api/versions
    implementation("net.portswigger.burp.extensions:montoya-api:2024.7")

    // Added the org.json library to be able to parse JSON
    // https://central.sonatype.com/artifact/org.json/json
    implementation("org.json:json:20240303")

    // Include https://github.com/ncoblentz/BurpMontoyaLibrary
    implementation("com.github.ncoblentz:BurpMontoyaLibrary:0.1.27")
    implementation("com.github.milchreis:uibooster:1.21.1")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}