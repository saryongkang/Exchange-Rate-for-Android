package com.saryong.example.data.local

import android.content.Context
import com.saryong.example.data.api.json.mapper.defaultKotshiListAdapter
import timber.log.Timber
import java.io.IOException

object LocalTextFileLoader {
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
      val jsonText = LocalTextFileLoader.load(context, filename)
      jsonAdapter.fromJson(jsonText) ?: throw IOException("Failed to load json file: [$filename]")
    } catch (e: IOException) {
      Timber.e(e.localizedMessage)
      return emptyList()
    }
  }
}