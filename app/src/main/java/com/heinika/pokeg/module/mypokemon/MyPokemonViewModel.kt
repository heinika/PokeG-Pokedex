package com.heinika.pokeg.module.mypokemon

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.model.MyPokemon
import com.heinika.pokeg.module.gameprops.props.carryIIIPropsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(private val myPokemonRepository: MyPokemonRepository) :
  ViewModel() {
  private val _myDetailPokemonInfo: MutableState<MyPokemon?> = mutableStateOf(null)
  val myDetailPokemon: State<MyPokemon?> = _myDetailPokemonInfo

  private val _myPokemonList: MutableState<List<MyPokemon>> = mutableStateOf(emptyList())
  val myPokemonList: State<List<MyPokemon>> = _myPokemonList

  fun refreshAllPokemonList(onFinish: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        Timber.i(
          "viewModelScope ${
            myPokemonRepository.fetchAllMyPokemonList().joinToString { it.name }
          }"
        )
        _myPokemonList.value = myPokemonRepository.fetchAllMyPokemonList()
      }
      onFinish()
    }
  }

  fun requestInitDetailPokemon(globalId: Int, name: String) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        val pokemon = PokemonDataCache.pokemonList.first { it.globalId == globalId }
        val abilities = myPokemonRepository.fetchPokemonAbilities(globalId)
        val versions = myPokemonRepository.fetchPokemonMoveVersionList(globalId, pokemon.speciesId)
        val moves = myPokemonRepository.fetchMoveList(globalId, pokemon.speciesId, versions.last())
        val myPokemon = MyPokemon(
          name,
          globalId,
          Generation.values()[pokemon.generationId - 1].id,
          typeIdList = pokemon.types,
          carry = carryIIIPropsList.first().cname,
          natureId = Nature.Brave.ordinal,
          abilityId = abilities.first().id,
          moveIdList = moves.filter { it.methodId == 4 }.takeLast(4).map { it.moveId }
        )
        _myDetailPokemonInfo.value = myPokemon
      }
    }
  }

  fun requestExistMyPokemon(name: String) {
    val myPokemon = myPokemonList.value.first { it.name == name }
    _myDetailPokemonInfo.value = myPokemon
  }

  fun saveMyPokemonToDataBase(myPokemon: MyPokemon, onFinish: () -> Unit) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        if (myPokemonList.value.any { it.myPokemonId == myPokemon.myPokemonId }) {
          myPokemonRepository.updateMyPokemon(myPokemon)
        } else {
          myPokemonRepository.insertMyPokemon(myPokemon)
        }
      }
      onFinish()
    }
  }

}