package com.heinika.pokeg.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

abstract class LiveCoroutinesViewModel : ViewModel() {
  fun <T> Flow<T>.asLiveDataOnViewModelScope(): LiveData<T> {
    return asLiveData(viewModelScope.coroutineContext + Dispatchers.IO)
  }
}