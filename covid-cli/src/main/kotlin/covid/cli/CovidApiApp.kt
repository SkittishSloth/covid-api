package covid.cli

import covid.web.*

class App {

  
}

fun main() {
  val api = CovidApiServiceImpl(HttpImpl)

  val response = api.base().blockingGet()
  println("Response:")
  println(response)
}
