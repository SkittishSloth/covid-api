package covid.core.models

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonIgnore

/*
  "allRoute": {
    "Name": "Get All Data",
    "Description": "Returns all data in the system. Warning: this request returns 8MB+ and takes 5+ seconds",
    "Path": "/all"
  },
*/

data class Route @JsonCreator constructor(
  @get:JsonGetter("name")
  @param:JsonProperty(value = "Name", required = true)
  val name: String,
  
  @get:JsonGetter("description")
  @param:JsonProperty(value = "Description", required = true)
  val description: String,
  
  @get:JsonGetter("path")
  @param:JsonProperty(value = "Path", required = true)
  val path: String
)