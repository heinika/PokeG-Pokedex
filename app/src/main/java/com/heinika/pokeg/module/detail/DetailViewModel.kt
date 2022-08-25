package com.heinika.pokeg.module.detail

import androidx.lifecycle.LiveData
import com.heinika.pokeg.base.LiveCoroutinesViewModel
import com.heinika.pokeg.module.detail.itemdelegate.model.MoveItem
import com.heinika.pokeg.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val detailRepository: DetailRepository
) : LiveCoroutinesViewModel() {

  fun getPokemonMoveVersionLiveData(id: Int, speciesId: Int): LiveData<List<Int>> =
    detailRepository.pokemonMoveVersionsFlow(id, speciesId).asLiveDataOnViewModelScope()

  fun getPokemonMoveLiveData(id: Int, speciesId: Int,version: Int): LiveData<Map<Int, List<MoveItem>>> =
    detailRepository.pokemonMovesFlow(id, speciesId,version).asLiveDataOnViewModelScope()

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