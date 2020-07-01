///////////////////////////
// covid-web build file. //
///////////////////////////

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm")

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
}

dependencies {
  val retrofitVersion = "2.9.0"
  val okHttpVersion = "4.7.2"
  
  implementation(project(":covid-core"))

  implementation("com.squareup.okhttp3:okhttp:$okHttpVersion")
  implementation("com.squareup.okhttp3:logging-interceptor:$okHttpVersion")
  
  implementation("com.squareup.retrofit2:converter-jackson:$retrofitVersion")
  implementation("com.squareup.retrofit2:converter-scalars:$retrofitVersion")
  implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
  implementation("com.squareup.retrofit2:adapter-rxjava3:$retrofitVersion")
}
