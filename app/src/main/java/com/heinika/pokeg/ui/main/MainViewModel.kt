package com.heinika.pokeg.ui.main

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.heinika.pokeg.base.LiveCoroutinesViewModel
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository,
  private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

  private val pokemonFetchingIndex: MutableStateFlow<Int> = MutableStateFlow(0)
  val pokemonListLiveData: LiveData<List<Pokemon>>

  private val _toastMessage = MutableLiveData<String?>()
  val toastMessage: LiveData<String?> = _toastMessage

  private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
  val isLoading: LiveData<Boolean> = _isLoading

  init {
    Timber.d("init MainViewModel")

    pokemonListLiveData = pokemonFetchingIndex.asLiveData().switchMap { page ->
      mainRepository.fetchPokemonList(
        page = page,
        onStart = { _isLoading.postValue(true) },
        onSuccess = { _isLoading.postValue(false) },
        onError = { _toastMessage.postValue(it) }
      ).asLiveDataOnViewModelScope()
    }
  }

  @MainThread
  fun fetchNextPokemonList() {
    if (!_isLoading.value!!) {
      pokemonFetchingIndex.value++
    }
  }
}
