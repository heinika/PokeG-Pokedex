package com.heinika.pokeg.module.team

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.model.TeamNumberInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(teamRepository: TeamRepository) : ViewModel() {
  private val _teamNumberList = mutableStateOf<List<TeamNumberInfo>>(emptyList())
  val teamNumberList: State<List<TeamNumberInfo>> = _teamNumberList
  private var allMoveList : List<Move> = emptyList()


  init {
    viewModelScope.launch {
      teamRepository.allMovesFlow().collect {moveList ->
        allMoveList = moveList
        teamRepository.teamNumberInfoListFlow(moveList).collect {
          _teamNumberList.value = it
        }
      }
    }
  }
}