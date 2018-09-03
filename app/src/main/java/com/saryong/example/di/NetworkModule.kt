package com.saryong.example.di

import com.saryong.example.data.api.AlphaVantageApi
import com.saryong.example.data.api.TransferWiseApi
import com.saryong.example.data.api.response.mapper.ApplicationJsonAdapterFactory
import com.saryong.example.data.api.response.mapper.InstantAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threeten.bp.Instant
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(
  includes = [BuildTypeBasedNetworkModule::class]
)
open class NetworkModule {
  companion object {
    val instance = NetworkModule()
  }
  
  @RetrofitAlphaVantage @Singleton @Provides
  fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
      .addNetworkInterceptor(loggingInterceptor)
      .build()
  
  @RetrofitAlphaVantage @Singleton @Provides
  fun provideRetrofit(@RetrofitAlphaVantage okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://www.alphavantage.co")
      .addConverterFactory(
        MoshiConverterFactory.create(Moshi.Builder()
          .add(ApplicationJsonAdapterFactory.INSTANCE)
          .add(Instant::class.java, InstantAdapter())
          .build()))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
      .build()
  
//  @Singleton @Provides
//  open fun provideTransferWiseApi(retrofit: Retrofit): TransferWiseApi =
//    retrofit.create(TransferWiseApi::class.java)
  
  @Singleton @Provides
  open fun provideAlphaVantageApi(@RetrofitAlphaVantage retrofit: Retrofit): AlphaVantageApi =
    retrofit.create(AlphaVantageApi::class.java)
}