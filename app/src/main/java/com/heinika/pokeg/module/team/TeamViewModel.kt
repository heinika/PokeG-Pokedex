package com.heinika.pokeg.module.team

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.MyPokemon
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val teamRepository: TeamRepository) : ViewModel() {
  fun updateMyPokemon(myPokemon: MyPokemon) {
    viewModelScope.launch {
      withContext(Dispatchers.IO){
        teamRepository.updateMyPokemon(myPokemon)
      }
    }
  }

  private val _teamNumberMap = mutableStateOf<Map<String, List<MyPokemon>>>(emptyMap())
  val teamNumberMap: State<Map<String, List<MyPokemon>>> = _teamNumberMap

  private val _allMyPokemonList = mutableStateOf<List<MyPokemon>>(emptyList())
  val allMyPokemonList: State<List<MyPokemon>> = _allMyPokemonList


  init {
    viewModelScope.launch {

      teamRepository.teamListMap().collect {
        Timber.i("teamListMap")
        _teamNumberMap.value = it
        _allMyPokemonList.value = teamRepository.allMyPokemonList
      }

    }
  }
}