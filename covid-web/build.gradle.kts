plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

dependencies {
  val retrofitVersion = "2.9.0"

  implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
  implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")
}
