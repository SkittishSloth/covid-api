plugins {
  java
  id("org.jetbrains.kotlin.jvm") version "1.3.72" apply false
  id("com.github.ben-manes.versions") version "0.28.0"
}

subprojects {
  apply<JavaPlugin>()
  apply(plugin = "org.jetbrains.kotlin.jvm")

  repositories {
    jcenter()
    mavenCentral()
  }

  dependencies {
    val koin_version = "2.1.5"
    
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    
    implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")
    implementation("org.koin:koin-core:$koin_version")
    
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.1")
    
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
  }
}