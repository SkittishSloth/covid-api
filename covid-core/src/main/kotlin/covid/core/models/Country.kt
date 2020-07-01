package covid.core.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIgnore

data class Country @JsonCreator constructor(
  @get:JsonGetter("name")
  @param:JsonProperty(value = "Name", required = true)
  val name: String,
  
  @get:JsonGetter("slug")
  @param:JsonProperty(value = "Slug", required = true)
  val slug: String,
  
  @get:JsonGetter("iso2")
  @param:JsonProperty(value = "ISO2", required = true)
  val iso2: String
)