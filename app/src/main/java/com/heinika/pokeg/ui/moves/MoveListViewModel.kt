package com.heinika.pokeg.ui.moves

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.repository.MoveListRepository
import com.heinika.pokeg.utils.PokemonProp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveListViewModel @Inject constructor(moveListRepository: MoveListRepository) : ViewModel() {
  private val _allMovesState = mutableStateOf<List<Move>>(emptyList())
  private var baseMoves = emptyList<Move>()
  val allMovesState: State<List<Move>> = _allMovesState

  private var typeFilterList: List<PokemonProp.Type> = emptyList()
  private var generationFilterList: List<PokemonProp.Generation> = emptyList()

  init {
    viewModelScope.launch {
      moveListRepository.allMovesFlow().collect {
        baseMoves = it
        _allMovesState.value = it
      }
    }
  }

  fun filterTypes(selectedTypes: List<PokemonProp.Type>) {
    typeFilterList = selectedTypes
    startFitter()
  }

  fun filterGenerations(selectedGenerations: List<PokemonProp.Generation>) {
    generationFilterList = selectedGenerations
    startFitter()
  }

  private fun startFitter() {
    _allMovesState.value = baseMoves.filter { move ->
      if (typeFilterList.isEmpty()) {
        true
      } else {
        typeFilterList.map { it.typeId }.contains(move.typeId)
      }
    }.filter { move ->
      if (generationFilterList.isEmpty()) {
        true
      } else {
        generationFilterList.map { it.id }.contains(move.generationId)
      }
    }
  }

}