package com.heinika.pokeg.ui.team

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.Nature
import com.heinika.pokeg.model.TeamNumberInfo
import com.heinika.pokeg.repository.NatureRepository
import com.heinika.pokeg.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
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