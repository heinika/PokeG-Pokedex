package com.heinika.pokeg.di

import com.heinika.pokeg.network.HttpRequestInterceptor
import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.network.PokeGService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(HttpRequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://pokeapi.co/api/v2/")
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun providePokeGService(retrofit: Retrofit): PokeGService {
    return retrofit.create(PokeGService::class.java)
  }

  @Provides
  @Singleton
  fun providePokeGClient(pokedexService: PokeGService): PokeGClient {
    return PokeGClient(pokedexService)
  }
}
