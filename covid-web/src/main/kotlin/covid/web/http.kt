package covid.web

import okhttp3.OkHttpClient
import okhttp3.logging.*

import mu.*
import org.koin.dsl.*

interface Http: AutoCloseable {
  val client: OkHttpClient
  
  fun cleanup()
  
  override fun close() = cleanup()
}
  
internal object HttpImpl: Http {
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
      .build()
  }

  override val client: OkHttpClient by lazyClient

  override fun cleanup() {
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

val http = module {
  single<Http> { HttpImpl }
}
