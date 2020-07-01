////////////////////////////////////////
// covid-api project root build file. //
////////////////////////////////////////

plugins {
  java
  id("org.jetbrains.kotlin.jvm") version "1.3.72" apply false
  id("com.github.ben-manes.versions") version "0.28.0"
}

allprojects {
  version = "0.0.1-SNAPSHOT"
}

subprojects {
  apply<JavaPlugin>()
  apply(plugin = "org.jetbrains.kotlin.jvm")

  repositories {
    jcenter()
    mavenCentral()
  }

  dependencies {
    val koin_version = "2.1.6"
    
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")

    implementation("org.koin:koin-core:$koin_version")

    // this is better suited in the data module, where ever that ends up.
    implementation("io.github.soc:directories:12")

    implementation("io.github.microutils:kotlin-logging:1.8.0.1")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.1")
    
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
  }
}
