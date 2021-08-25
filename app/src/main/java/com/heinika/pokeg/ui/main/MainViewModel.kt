package com.heinika.pokeg.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.base.LiveCoroutinesViewModel
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository,
  private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

  val pokemonListState: StateFlow<List<Pokemon>>

  private val _toastMessage = MutableLiveData<String?>()
  val toastMessage: LiveData<String?> = _toastMessage

  private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
  val isLoading: LiveData<Boolean> = _isLoading

  init {
    Timber.d("init MainViewModel")

    pokemonListState =
      mainRepository.fetchPokemonList(
        onStart = { _isLoading.postValue(true) },
        onSuccess = { _isLoading.postValue(false) },
        onError = { _toastMessage.postValue(it) }
      ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000), // Or Lazily because it's a one-shot
        initialValue = emptyList()
      )
  }
}
