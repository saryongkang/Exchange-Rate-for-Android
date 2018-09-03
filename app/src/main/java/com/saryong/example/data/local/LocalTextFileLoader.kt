package com.saryong.example.data.local

import android.content.Context
import com.saryong.example.data.api.response.mapper.defaultKotshiListAdapter
import timber.log.Timber
import java.io.IOException

object LocalTextFileLoader {
  const val defaultJsonFolder = "json/"
  
  @Throws(IOException::class)
  fun load(context: Context, filename: String): String {
    val file = context.assets.open(filename)
    val buffer = ByteArray(file.available())
    file.read(buffer)
    file.close()
    return String(buffer)
  }

  inline fun <reified E : Any> loadJsonList(context: Context, filename: String): List<E> {
    val jsonAdapter = defaultKotshiListAdapter<E>()
    return try {
      val jsonText = LocalTextFileLoader.load(context, defaultJsonFolder + filename)
      jsonAdapter.fromJson(jsonText) ?: throw IOException("Failed to load json file: [$filename]")
    } catch (e: IOException) {
      Timber.e(e.localizedMessage)
      return emptyList()
    }
  }
}