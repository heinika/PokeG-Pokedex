package com.heinika.pokeg.ui.main

import androidx.lifecycle.*
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.MainRepository
import com.heinika.pokeg.utils.PokemonProp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository,
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val _toastMessage = MutableLiveData<String?>()
  val toastMessage: LiveData<String?> = _toastMessage

  private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
  val isLoading: LiveData<Boolean> = _isLoading

  private val _pokemonSortListStateFlow = MutableStateFlow<List<Pokemon>>(listOf())
  val pokemonSortListStateFlow = _pokemonSortListStateFlow

  private var basePokemonList: List<Pokemon>? = null

  var filterTypeList : List<PokemonProp.Type> = emptyList()


  init {
    Timber.d("init MainViewModel")

    viewModelScope.launch {
      mainRepository.fetchPokemonList(
        onStart = { _isLoading.postValue(true) },
        onSuccess = { _isLoading.postValue(false) },
        onError = { _toastMessage.postValue(it) }
      ).collect {
        basePokemonList = it
        _pokemonSortListStateFlow.value = it
      }
    }
  }

  fun startSortAndFilter() {
    basePokemonList?.let { baseList ->
      _pokemonSortListStateFlow.value = baseList.filterType()
    }
  }

  private fun List<Pokemon>.filterType(): List<Pokemon> {
    return filter { pokemon ->
      if (filterTypeList.isEmpty()){
        true
      } else {
        var result = true
        filterTypeList.forEach { type ->
          if (!pokemon.types.map { it.typeId }.contains(type.typeId)){
            result = false
          }
        }
        result
      }
    }
  }

}
