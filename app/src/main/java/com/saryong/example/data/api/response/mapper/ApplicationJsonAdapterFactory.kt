package com.saryong.example.data.api.response.mapper

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import se.ansman.kotshi.KotshiJsonAdapterFactory

@KotshiJsonAdapterFactory
abstract class ApplicationJsonAdapterFactory : JsonAdapter.Factory {
  companion object {
    val INSTANCE: ApplicationJsonAdapterFactory =
      KotshiApplicationJsonAdapterFactory()
  }
}

inline fun <reified T : Any> defaultKotshiListAdapter(): JsonAdapter<List<T>> {
  val moshi = Moshi.Builder().add(ApplicationJsonAdapterFactory.INSTANCE).build()
  val type = Types.newParameterizedType(List::class.java, T::class.java) as java.lang.reflect.Type
  return moshi.adapter(type)
}
