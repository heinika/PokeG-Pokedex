package com.heinika.pokeg.module.typedetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.heinika.pokeg.info.Type
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TypeDetailScreenViewModel @Inject constructor() : ViewModel() {
  var curTypes by mutableStateOf<List<Type>>(emptyList())
}