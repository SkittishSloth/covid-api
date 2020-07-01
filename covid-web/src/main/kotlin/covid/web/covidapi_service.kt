package covid.web

import java.util.*

import okhttp3.*
import retrofit2.*
import retrofit2.http.*

import io.reactivex.rxjava3.core.*

import mu.*

import covid.core.models.Route
import org.koin.dsl.*
import retrofit2.adapter.rxjava3.*
import retrofit2.converter.jackson.*

interface CovidApi {
  @GET("/")
  fun base(): Single<Map<String, Route>>
}

interface CovidApiService {
  fun base(): Single<Map<String, Route>>
}

internal class CovidApiServiceImpl(
  private val http: Http
): CovidApiService {
  private val log = KotlinLogging.logger {}

  private val client: OkHttpClient by lazy {
    http.client
  }

  private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
      .baseUrl(url)
      .addConverterFactory(JacksonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .client(client)
      .build()
  }
  
  private val api: CovidApi by lazy {
    retrofit.create(CovidApi::class.java)
  }
  
  override fun base(): Single<Map<String, Route>> {
    return api.base()
  }
  
  companion object {
    const val url: String = "https://api.covid19api.com/"
  }
}

val covidApi = module {
  single<CovidApiService> { CovidApiServiceImpl(get()) }
}
