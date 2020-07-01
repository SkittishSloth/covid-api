package covid.cli

import covid.web.*
import org.koin.core.*
import org.koin.core.context.*

class CovidApiApp(
  private val args: Array<String>
): KoinComponent {
  private val api: CovidApiService by inject()

  fun run() {
    val response = api.base().blockingGet()
    println("Response:")
    println(response)
  }
}

fun KoinApplication.apiModules() = modules(
  http,
  covidApi
)

fun main(args: Array<String>) {
  startKoin {
    apiModules()
  }

  CovidApiApp(args).run()

  stopKoin()
}
