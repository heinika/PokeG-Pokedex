package com.heinika.pokeg.module.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.base.LiveCoroutinesViewModel
import com.heinika.pokeg.module.detail.itemdelegate.model.MoveItem
import com.heinika.pokeg.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val detailRepository: DetailRepository
) : LiveCoroutinesViewModel() {
  val versionIdList = mutableStateListOf<Int>()

  private val _pokemonMoveMap = mutableStateOf(emptyMap<Int, List<MoveItem>>())
  val pokemonMoveMap: State<Map<Int, List<MoveItem>>> = _pokemonMoveMap

  private val _versionId = mutableStateOf<Int?>(null)
  val versionId: State<Int?> = _versionId

  private val _moveMethodId = mutableStateOf(1)
  val moveMethodId: State<Int> = _moveMethodId

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
          detailRepository.pokemonMovesFlow(id, speciesId, version).collect {
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


  fun getPokemonSpecieNameLiveData(id: Int) =
    detailRepository.pokemonNameFlow(id).asLiveDataOnViewModelScope()

  fun getPokemonSpecieLiveData(id: Int) =
    detailRepository.pokemonSpecieFlow(id).asLiveDataOnViewModelScope()

  fun speciesAllOtherFormsLiveData(specieId: Int, globalId: Int) =
    detailRepository.speciesAllOtherFormsFlow(specieId, globalId).asLiveDataOnViewModelScope()

  fun getPokemonAbilitiesLiveData(id: Int) =
    detailRepository.pokemonAbilitiesFlow(id).asLiveDataOnViewModelScope()

  fun getSpecieEggGroupLiveData(id: Int) =
    detailRepository.specieEggGroupFlow(id).asLiveDataOnViewModelScope()

  fun getSpecieEvolutionChainLiveData(id: Int) =
    detailRepository.specieEvolutionChainFlow(id).asLiveDataOnViewModelScope()

  fun getSpecieFlavorTextsLiveData(id: Int) =
    detailRepository.specieFlavorTextsFlow(id).asLiveDataOnViewModelScope()


}



