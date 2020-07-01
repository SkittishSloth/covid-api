package covid.web

import retrofit2.*
import retrofit2.adapter.rxjava3.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

import okhttp3.OkHttpClient
import okhttp3.logging.*

import mu.*

interface Http: AutoClosable {
  val client: OkHttpClient
  
  fun cleanup()
  
  fun close() = cleanup()
}
  
internal object HttpImpl {
  private val log = KotlinLogging.logger {}
  private val httpLogger = object : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
      log.debug(message)
    }
  }

  private val lazyClient = lazy {
    val httpLog = HttpLoggingInterceptor(httpLogger)
    httpLog.setLevel(HttpLoggingInterceptor.Level.BASIC)
    
    OkHttpClient.Builder()
      .addInterceptor(httpLog)
      .addConverterFactory(JacksonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .build()
  }

  val client: OkHttpClient by lazyClient

  fun cleanup() {
    if (lazyClient.isInitialized()) {
      log.info("Cleaning up Http client.")
      with (lazyClient.value) {
        dispatcher.executorService.shutdown()
        connectionPool.evictAll()
        cache?.close()
      }
    }
  }
}