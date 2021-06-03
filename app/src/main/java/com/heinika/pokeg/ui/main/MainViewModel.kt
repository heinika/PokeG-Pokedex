package com.heinika.pokeg.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.heinika.pokeg.base.LiveCoroutinesViewModel
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val mainRepository: MainRepository,
  private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

  val pokemonListLiveData: LiveData<List<Pokemon>>

  private val _toastMessage = MutableLiveData<String?>()
  val toastMessage: LiveData<String?> = _toastMessage

  private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
  val isLoading: LiveData<Boolean> = _isLoading

  init {
    Timber.d("init MainViewModel")

    pokemonListLiveData =
      mainRepository.fetchPokemonList(
        onStart = { _isLoading.postValue(true) },
        onSuccess = { _isLoading.postValue(false) },
        onError = { _toastMessage.postValue(it) }
      ).asLiveDataOnViewModelScope()
  }
}
