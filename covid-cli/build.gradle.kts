plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")

    // Apply the application plugin to add support for building a CLI application.
    application
    
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

dependencies {
  implementation(project(":covid-core"))
}

application {
    // Define the main class for the application.
    mainClassName = "covid.cli.AppKt"
}
