package com.heinika.pokeg.ui.moves

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.repository.MoveListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveListViewModel @Inject constructor(moveListRepository: MoveListRepository) : ViewModel() {
    private val _allMovesState = mutableStateOf<List<Move>>(emptyList())
    val allMovesState : State<List<Move>> = _allMovesState

    init {
      viewModelScope.launch {
          moveListRepository.allMovesFlow().collect {
              _allMovesState.value = it
          }
      }
    }
}