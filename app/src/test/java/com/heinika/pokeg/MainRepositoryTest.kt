@file:Suppress("SpellCheckingInspection")

package com.heinika.pokeg

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.heinika.pokeg.model.PokemonResponse
import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.network.PokeGService
import com.heinika.pokeg.persistence.PokemonDao
import com.heinika.pokeg.repository.MainRepository
import com.heinika.pokeg.utils.MockUtil.mockPokemonList
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class MainRepositoryTest {

  private lateinit var repository: MainRepository
  private lateinit var client: PokeGClient
  private val service: PokeGService = mock()
  private val pokemonDao: PokemonDao = mock()

  @ExperimentalCoroutinesApi
  @get:Rule
  var coroutinesRule = MainCoroutinesRule()

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @ExperimentalCoroutinesApi
  @Before
  fun setup() {
    client = PokeGClient(service)
    repository = MainRepository(client, pokemonDao)
  }

  @ExperimentalTime
  @Test
  fun fetchPokemonListFromNetworkTest() = runBlocking {
    val mockData = PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
    whenever(pokemonDao.getPokemonList(page_ = 0)).thenReturn(emptyList())
    whenever(service.fetchPokemonList()).thenReturn(ApiResponse.of { Response.success(mockData) })

    repository.fetchPokemonList(
      page = 0,
      onStart = {},
      onSuccess = {},
      onError = {}
    ).test {
      assertEquals(expectItem()[0].page, 0)
      assertEquals(expectItem()[0].name, "bulbasaur")
      assertEquals(expectItem(), mockPokemonList())
      expectComplete()
    }

    verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
    verify(service, atLeastOnce()).fetchPokemonList()
    verify(client, atLeastOnce()).fetchPokemonList(page = 0)
    verify(pokemonDao, atLeastOnce()).insertPokemonList(mockData.results)
  }

  @ExperimentalTime
  @Test
  fun fetchPokemonListFromDatabaseTest() = runBlocking {
    val mockData = PokemonResponse(count = 984, next = null, previous = null, results = mockPokemonList())
    whenever(pokemonDao.getPokemonList(page_ = 0)).thenReturn(mockData.results)
    whenever(service.fetchPokemonList()).thenReturn(ApiResponse.of { Response.success(mockData) })

    repository.fetchPokemonList(
      page = 0,
      onStart = {},
      onSuccess = {},
      onError = {}
    ).test {
      assertEquals(expectItem()[0].page, 0)
      assertEquals(expectItem()[0].name, "bulbasaur")
      assertEquals(expectItem(), mockPokemonList())
      expectComplete()
    }

    verify(pokemonDao, atLeastOnce()).getPokemonList(page_ = 0)
    verifyNoMoreInteractions(service)
  }
}
