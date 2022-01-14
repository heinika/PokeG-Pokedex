package com.heinika.pokeg.module.team

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.heinika.pokeg.model.TeamNumberInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(teamRepository: TeamRepository) : ViewModel() {
  private val _teamNumberList = mutableStateOf<List<TeamNumberInfo>>(emptyList())


//  init {
//    viewModelScope.launch {
//      teamRepository.teamNumberListFlow().collect { list ->
//        _teamNumberList.value = list.map { it.toTeamNumberInfo() }
//      }
//    }
//  }
}