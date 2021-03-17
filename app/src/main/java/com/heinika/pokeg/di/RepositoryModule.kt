package com.heinika.pokeg.di

import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.persistence.PokemonDao
import com.heinika.pokeg.repository.MainRepository
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
        pokemonDao: PokemonDao
    ): MainRepository {
        return MainRepository(pokeGClient, pokemonDao)
    }
}