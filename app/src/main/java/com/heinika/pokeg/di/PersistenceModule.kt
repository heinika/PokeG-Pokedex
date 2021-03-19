/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.heinika.pokeg.di

import android.app.Application
import androidx.room.Room
import com.heinika.pokeg.persistence.*
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

  @Provides
  @Singleton
  fun provideMoshi(): Moshi {
    return Moshi.Builder().build()
  }

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
    typeResponseConverter: TypeResponseConverter,
    statResponseConverter: StatResponseConverter
  ): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "PokeG.db")
      .fallbackToDestructiveMigration()
      .addTypeConverter(typeResponseConverter)
      .addTypeConverter(statResponseConverter)
      .build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
    return appDatabase.pokemonDao()
  }

  @Provides
  @Singleton
  fun providePokemonInfoDao(appDatabase: AppDatabase):PokemonInfoDao{
    return appDatabase.pokemonInfoDao()
  }

  @Provides
  @Singleton
  fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
    return TypeResponseConverter(moshi)
  }

  @Provides
  @Singleton
  fun provideStatResponseConverter(moshi: Moshi): StatResponseConverter {
    return StatResponseConverter(moshi)
  }
}
