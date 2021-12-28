package com.heinika.pokeg.ui.nature

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.Nature
import com.heinika.pokeg.repository.NatureRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NatureViewModel @Inject constructor(natureRepository: NatureRepository) : ViewModel() {
  private val _natureList = mutableStateOf<List<Nature>>(emptyList())
  val natureList: State<List<Nature>> = _natureList

  init {
    viewModelScope.launch {
      natureRepository.natureListFlow().collect {
        _natureList.value = it
      }
    }
  }
}