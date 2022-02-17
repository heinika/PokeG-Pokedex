package com.heinika.pokeg.module.team

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.info.Move
import com.heinika.pokeg.model.MyPokemonInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(teamRepository: TeamRepository) : ViewModel() {
  private val _teamNumberMap = mutableStateOf<Map<String, List<MyPokemonInfo>>>(emptyMap())
  val teamNumberMap: State<Map<String, List<MyPokemonInfo>>> = _teamNumberMap


  init {
    viewModelScope.launch {
      teamRepository.teamListMap().collect {
        _teamNumberMap.value = it
      }
    }
  }
}