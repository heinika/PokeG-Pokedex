package com.heinika.pokeg.di

import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.repository.MainRepository
import com.heinika.pokeg.repository.res.PokemonRes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

  @Provides
  @ViewModelScoped
  fun provideMainRepository(
    pokeGClient: PokeGClient,
    pokemonRes: PokemonRes
  ): MainRepository {
    return MainRepository(pokemonRes)
  }
}