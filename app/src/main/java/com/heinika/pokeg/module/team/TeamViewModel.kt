package com.heinika.pokeg.module.team

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.model.MyPokemonInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(teamRepository: TeamRepository) : ViewModel() {
  private val _teamNumberMap = mutableStateOf<Map<String,List<MyPokemonInfo>>>(emptyMap())
  val teamNumberMap: State<Map<String,List<MyPokemonInfo>>> = _teamNumberMap
  private var allMoveList : List<Move> = emptyList()


  init {
    viewModelScope.launch {
      teamRepository.allMovesFlow().collect {moveList ->
        allMoveList = moveList
        teamRepository.teamListMap(moveList).collect {
          _teamNumberMap.value = it
        }
      }
    }
  }
}