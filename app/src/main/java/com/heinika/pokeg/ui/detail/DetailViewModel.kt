package com.heinika.pokeg.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heinika.pokeg.base.LiveCoroutinesViewModel
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
  private val detailRepository: DetailRepository,
) : LiveCoroutinesViewModel() {

  private val _toastMessage = MutableLiveData<String?>()
  val toastMessage: LiveData<String?> = _toastMessage

  private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
  val isLoading: LiveData<Boolean> = _isLoading

  fun getPokemonInfoLiveData(pokemon: Pokemon): LiveData<PokemonInfo> {
    _isLoading.postValue(true)
    return detailRepository.fetchPokemonInfo(
      pokemon,
      onSuccess = {
        _isLoading.postValue(false)
      },
      onError = {
        _isLoading.postValue(false)
        _toastMessage.postValue(it)
      }
    ).asLiveDataOnViewModelScope()
  }
}