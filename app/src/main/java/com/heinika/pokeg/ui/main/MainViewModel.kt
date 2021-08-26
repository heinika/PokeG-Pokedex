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

  var filterType1 = PokemonProp.Type.UNKNOWN
  var filterType2 = PokemonProp.Type.UNKNOWN


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
      _pokemonSortListStateFlow.value = baseList.sortedBy { -it.totalBaseStat }.filterType()
    }
  }

  private fun List<Pokemon>.filterType(): List<Pokemon> {
    if (filterType1 == PokemonProp.Type.UNKNOWN && filterType2 == PokemonProp.Type.UNKNOWN) return this
    if (filterType1 != PokemonProp.Type.UNKNOWN && filterType2 != PokemonProp.Type.UNKNOWN) return this.filter { pokemon ->
      pokemon.types.any { it.typeId == filterType1.typeId } && pokemon.types.any { it.typeId == filterType2.typeId }
    }
    if (filterType1 != PokemonProp.Type.UNKNOWN) return this.filter { pokemon ->
      pokemon.types.any { it.typeId == filterType1.typeId }
    }
    if (filterType2 != PokemonProp.Type.UNKNOWN) return this.filter { pokemon ->
      pokemon.types.any { it.typeId == filterType2.typeId }
    }
    return this
  }

}
