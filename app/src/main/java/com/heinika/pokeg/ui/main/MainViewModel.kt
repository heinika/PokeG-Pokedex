package com.heinika.pokeg.ui.main

import androidx.lifecycle.*
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.MainRepository
import com.heinika.pokeg.repository.res.PokemonRes
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
  private val pokemonRes: PokemonRes,
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val _toastMessage = MutableLiveData<String?>()
  val toastMessage: LiveData<String?> = _toastMessage

  private val _searchText = MutableLiveData<CharSequence?>()
  val searchText: LiveData<CharSequence?> = _searchText

  private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
  val isLoading: LiveData<Boolean> = _isLoading

  private val _isSortDesc = MutableLiveData<Boolean>().apply { value = true }
  val isSortDesc: LiveData<Boolean> = _isSortDesc

  private val _pokemonSortListStateFlow = MutableStateFlow<List<Pokemon>>(listOf())
  val pokemonSortListStateFlow = _pokemonSortListStateFlow

  private var basePokemonList: List<Pokemon>? = null

  var filterTypeList: List<PokemonProp.Type> = emptyList()
    set(value) {
      field = value
      startSortAndFilter()
    }

  private val _filterGenerations: MutableLiveData<List<PokemonProp.Generation>> = MutableLiveData(emptyList())
  val filterGenerations: LiveData<List<PokemonProp.Generation>> = _filterGenerations

  private val _sortBaseStatusList: MutableLiveData<List<PokemonProp.BaseStatus>> =
    MutableLiveData(emptyList())

  val sortBaseStatusList: LiveData<List<PokemonProp.BaseStatus>> = _sortBaseStatusList

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

  fun changeSortBaseStatusList(list: List<PokemonProp.BaseStatus>) {
    _sortBaseStatusList.value = list
    startSortAndFilter()
  }

  fun changeGenerations(list: List<PokemonProp.Generation>) {
    _filterGenerations.value = list
    startSortAndFilter()
  }

  private fun startSortAndFilter() {
    basePokemonList?.let { baseList ->
      _pokemonSortListStateFlow.value = baseList.filterType().filterGenerations().sortBaseStatus()
    }
  }

  fun setSearchText(searchText: CharSequence?) {
    _searchText.value = searchText
    if (searchText.isNullOrEmpty()) {
      basePokemonList?.let { _pokemonSortListStateFlow.value = it }
    } else {
      basePokemonList?.filter {
        it.getCName(pokemonRes).contains(searchText, true) || it.id.toString()
          .contains(searchText, true)
      }?.let {
        _pokemonSortListStateFlow.value = it
      }
    }
  }

  private fun List<Pokemon>.filterType(): List<Pokemon> {
    return filter { pokemon ->
      if (filterTypeList.isEmpty()) {
        true
      } else {
        var result = true
        filterTypeList.forEach { type ->
          if (!pokemon.types.map { it.typeId }.contains(type.typeId)) {
            result = false
          }
        }
        result
      }
    }
  }

  private fun List<Pokemon>.filterGenerations(): List<Pokemon> {
    return filter { pokemon ->
      if (filterGenerations.value!!.isEmpty()){
        true
      } else {
        var result = false
        filterGenerations.value!!.forEach { generation ->
          if (pokemon.generationId == generation.id) {
            result = true
          }
        }
        result
      }
    }
  }

  private fun List<Pokemon>.sortBaseStatus(): List<Pokemon> {
    return sortedBy { pokemon ->
      if (sortBaseStatusList.value.isNullOrEmpty()) {
        0
      } else {
        var sortPriority = 0
        sortBaseStatusList.value?.forEach {
          sortPriority += when (it) {
            PokemonProp.BaseStatus.HP -> pokemon.hp
            PokemonProp.BaseStatus.ATK -> pokemon.atk
            PokemonProp.BaseStatus.DEF -> pokemon.def
            PokemonProp.BaseStatus.SP_ATK -> pokemon.spAtk
            PokemonProp.BaseStatus.SP_DEF -> pokemon.spDef
            PokemonProp.BaseStatus.SPEED -> pokemon.speed
          }
        }
        if (isSortDesc.value!!) {
          -sortPriority
        } else {
          sortPriority
        }
      }
    }
  }

  fun changeSortOrder() {
    _isSortDesc.value = !_isSortDesc.value!!
    startSortAndFilter()
  }

}
