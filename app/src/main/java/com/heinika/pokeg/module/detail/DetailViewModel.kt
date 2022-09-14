package com.heinika.pokeg.module.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.model.*
import com.heinika.pokeg.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val detailRepository: DetailRepository
) : ViewModel() {
  val versionIdList = mutableStateListOf<Int>()

  private val _pokemonMoveMap = mutableStateOf(emptyMap<Int, List<MoveItem>>())
  val pokemonMoveMap: State<Map<Int, List<MoveItem>>> = _pokemonMoveMap

  private val _versionId = mutableStateOf<Int?>(null)
  val versionId: State<Int?> = _versionId

  private val _moveMethodId = mutableStateOf(1)
  val moveMethodId: State<Int> = _moveMethodId

  val pokemonNameList = mutableStateListOf<PokemonName>()
  val abilityList = mutableStateListOf<Ability>()
  val allOtherFormList = mutableStateListOf<Pokemon>()
  val speciesEggGroupList = mutableStateListOf<SpeciesEggGroup>()
  val speciesEvolutionChainList = mutableStateListOf<SpeciesEvolutionChain>()

  private val _pokemonSpecie = mutableStateOf<PokemonSpecie?>(null)
  val pokemonSpecie: State<PokemonSpecie?> = _pokemonSpecie

  private val _specieFlavorText = mutableStateOf("")
  val specieFlavorText: State<String> = _specieFlavorText

  fun refreshPokemonMoveVersion(id: Int, speciesId: Int) {
    viewModelScope.launch {
      detailRepository.pokemonMoveVersionsFlow(id, speciesId).collect { versionIds ->
        versionIdList.clear()
        versionIdList.addAll(versionIds)

        _versionId.value = when {
          versionIdList.contains(ConfigMMKV.defaultVersion) -> ConfigMMKV.defaultVersion
          else -> versionIds.last()
        }

        _versionId.value?.let { version ->
          val pokemon = PokemonDataCache.pokemonList.first { it.globalId == id }
          val requestId = if(pokemon.form == 3) pokemon.speciesId else pokemon.globalId
          detailRepository.pokemonMovesFlow(requestId, speciesId, version).collect {
            _moveMethodId.value = 1
            _pokemonMoveMap.value = it
          }
        }
      }
    }
  }


  fun refreshPokemonMoveMap(id: Int, speciesId: Int, version: Int) {
    viewModelScope.launch {
      detailRepository.pokemonMovesFlow(id, speciesId, version).collect {
        _moveMethodId.value = 1
        _versionId.value = version
        _pokemonMoveMap.value = it
      }
    }
  }

  fun changeMethodId(methodId: Int) {
    _moveMethodId.value = methodId
  }


  fun refreshPokemonNameList(id: Int) {
    viewModelScope.launch {
      detailRepository.pokemonNameFlow(id).collect{
        pokemonNameList.clear()
        pokemonNameList.addAll(it)
      }
    }
  }


  fun refreshPokemonSpecie(id: Int) {
    viewModelScope.launch {
      detailRepository.pokemonSpecieFlow(id).collect{
        _pokemonSpecie.value = it
      }
    }
  }


  fun refreshSpeciesAllOtherForms(specieId: Int, globalId: Int) {
    viewModelScope.launch {
      detailRepository.speciesAllOtherFormsFlow(specieId, globalId).collect{
        allOtherFormList.clear()
        allOtherFormList.addAll(it)
      }
    }
  }


  fun refreshPokemonAbilityList(id: Int) {
    viewModelScope.launch {
      detailRepository.pokemonAbilitiesFlow(id).collect{
        abilityList.clear()
        abilityList.addAll(it)
      }
    }
  }


  fun refreshSpecieEggGroupList(id: Int) {
    viewModelScope.launch {
      detailRepository.specieEggGroupFlow(id).collect{
        speciesEggGroupList.clear()
        speciesEggGroupList.addAll(it)
      }
    }
  }


  fun refreshSpecieEvolutionChainList(id: Int) {
    viewModelScope.launch {
      detailRepository.specieEvolutionChainFlow(id).collect{
        speciesEvolutionChainList.clear()
        speciesEvolutionChainList.addAll(it)
      }
    }
  }


  fun refreshSpecieFlavorText(id: Int) {
    viewModelScope.launch {
      detailRepository.specieFlavorTextsFlow(id).collect{
        _specieFlavorText.value = it
      }
    }
  }

}



