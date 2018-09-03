package com.saryong.example.data.api.response.mapper

import com.saryong.example.util.extention.atCurrentZone
import com.saryong.example.util.extention.atUTC
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class InstantAdapter : JsonAdapter<Instant>() {
  override fun toJson(writer: JsonWriter, value: Instant?) {
    if (value == null) {
      writer.nullValue()
    } else {
      writer.value(value.atCurrentZone().format(FORMATTER))
    }
  }
  
  override fun fromJson(reader: JsonReader): Instant? = when (reader.peek()) {
    JsonReader.Token.NULL -> reader.nextNull()
    else -> {
      val dateString = reader.nextString()
      parseDateString(dateString)
    }
  }
  
  companion object {
    private val FORMATTER: DateTimeFormatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'+'0000")
  
    fun parseDateString(dateString: String): Instant =
      LocalDateTime.parse(dateString, FORMATTER).atUTC().toInstant()
  }
}
