package com.saryong.example.di

import com.saryong.example.App
import com.saryong.example.di.activitymodule.MainActivityBuilder
import com.saryong.example.di.activitymodule.SplashActivityBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  AppModule::class,
  NetworkModule::class,
  ViewModelModule::class,

  SplashActivityBuilder::class,
  MainActivityBuilder::class
])

interface AppComponent : AndroidInjector<App> {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun application(application: App): Builder

    fun networkModule(networkModule: NetworkModule): Builder

    fun build(): AppComponent
  }

  override fun inject(app: App)

}