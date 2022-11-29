package com.heinika.pokeg.module.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.ConfigMMKV.favoritePokemons
import com.heinika.pokeg.info.*
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.MainRepository
import com.heinika.pokeg.repository.res.PokemonRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@Suppress("unused")
@HiltViewModel
class HomeViewModel @Inject constructor(
  private val mainRepository: MainRepository,
  private val pokemonRes: PokemonRes,
  private val savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val _toastMessage = mutableStateOf("")
  val toastMessage: State<String> = _toastMessage

  private val _searchText = mutableStateOf("")
  val searchText: State<String> = _searchText

  private val _isLoading = mutableStateOf(true)
  val isLoading: State<Boolean> = _isLoading

  var _isSortDesc = mutableStateOf(true)
  val isSortDesc: State<Boolean> = _isSortDesc

  val pokemonSortStateList = mutableStateListOf<Pokemon>()

  private lateinit var allPokemonList: List<Pokemon>
  private lateinit var zhuziPokemonList: List<Pokemon>
  private lateinit var hiSuiPokemonList: List<Pokemon>
  private lateinit var basePokemonList: List<Pokemon>

  var filterTypeList: List<Int> = emptyList()
    set(value) {
      field = value
      startSortAndFilter()
    }

  private val _filterGenerations: MutableState<List<Generation>> = mutableStateOf(emptyList())
  val filterGenerations: State<List<Generation>> = _filterGenerations

  val filterTags = mutableStateListOf<Tag>()

  val sortBaseStatusList = mutableStateListOf<BaseStatus>()

  private val _selectedBodyStatus: MutableState<BodyStatus?> = mutableStateOf(null)
  val selectedBodyStatus: State<BodyStatus?> = _selectedBodyStatus

  init {
    Timber.d("init MainViewModel")

    viewModelScope.launch {
      mainRepository.fetchPokemonList(
        onStart = { },
        onSuccess = { _isLoading.value = false },
        onError = { _toastMessage.value = it }
      ).collect { pokemonList ->
        allPokemonList = pokemonList
        zhuziPokemonList = RegionNumber.ZhuZiMap.keys.map { zhuziNumber ->
          Log.i("TAG", ": zhuziPokemonList $zhuziNumber")
          pokemonList.first { it.globalId == zhuziNumber }
         }
        hiSuiPokemonList = RegionNumber.HiSuiMap.keys.map { hisuiNumber -> pokemonList.first { it.globalId == hisuiNumber } }
        basePokemonList = pokemonList
        clearAndAddAll(basePokemonList)
      }
    }
  }

  fun changDexType(dexType: DexType) {
    basePokemonList = when (dexType) {
      DexType.Global -> allPokemonList
      DexType.ZhuZi -> zhuziPokemonList
      DexType.HiSui -> hiSuiPokemonList
    }
    clearAndAddAll(basePokemonList)
  }

  fun changeBodyStatus(bodyStatus: BodyStatus?) {
    _selectedBodyStatus.value = bodyStatus
  }

  fun changeGenerations(list: List<Generation>) {
    _filterGenerations.value = list
    startSortAndFilter()
  }

  fun startSortAndFilter() {
    clearAndAddAll(basePokemonList.filterType().filterGenerations().filterTags().sortBaseStatus().sortBodyStatus())
  }

  private fun clearAndAddAll(pokemonList: List<Pokemon>) {
    pokemonSortStateList.clear()
    pokemonSortStateList.addAll(pokemonList)
  }

  fun setSearchText(searchText: String) {
    _searchText.value = searchText
    if (searchText.isEmpty()) {
      clearAndAddAll(basePokemonList)
    } else {
      this.basePokemonList.filter {
        it.getCName(pokemonRes).contains(searchText, true) || it.id.toString()
          .contains(searchText, true)
      }.let {
        clearAndAddAll(it)
      }
    }
  }

  private fun List<Pokemon>.filterType(): List<Pokemon> {
    return filter { pokemon ->
      if (filterTypeList.isEmpty()) {
        true
      } else {
        var result = true
        filterTypeList.forEach { typeId ->
          if (!pokemon.types.contains(typeId)) {
            result = false
          }
        }
        result
      }
    }
  }

  private fun List<Pokemon>.filterGenerations(): List<Pokemon> {
    return filter { pokemon ->
      if (filterGenerations.value.isEmpty()) {
        true
      } else {
        var result = false
        filterGenerations.value.forEach { generation ->
          if (pokemon.generationId == generation.id) {
            result = true
          }
        }
        result
      }
    }
  }

  private fun List<Pokemon>.filterTags(): List<Pokemon> {
    return filter { pokemon ->
      if (filterTags.isEmpty()) {
        true
      } else {
        filterTags.forEach { tag ->
          when (tag) {
            Tag.Favorite -> {
              if (favoritePokemons.contains(pokemon.globalId.toString())) {
                return@filter true
              }
            }
            Tag.Legendary -> {
              if (legendaryIdList.contains(pokemon.globalId)) {
                return@filter true
              }
              legendaryIdList.contains(pokemon.globalId)
            }
            Tag.Mythical -> {
              if (mythicalIdList.contains(pokemon.globalId)) {
                return@filter true
              }
            }
            Tag.BABY -> {
              if (babyIdList.contains(pokemon.globalId)) {
                return@filter true
              }
            }
            Tag.GMAX -> if (gmaxIdRange.contains(pokemon.globalId)) {
              return@filter true
            }
            Tag.ALOLA -> if (alolaIdList.contains(pokemon.globalId)) {
              return@filter true
            }
            Tag.GALAR -> if (galarIdList.contains(pokemon.globalId)) {
              return@filter true
            }
            Tag.HISUI -> if (hisuiIdList.contains(pokemon.globalId)) {
              return@filter true
            }
            Tag.MEGA -> if (megaIdList.contains(pokemon.globalId)) {
              return@filter true
            }
          }
        }
        false
      }
    }
  }

  private fun List<Pokemon>.sortBaseStatus(): List<Pokemon> {
    return sortedBy { pokemon ->
      if (sortBaseStatusList.isEmpty()) {
        0
      } else {
        var sortPriority = 0
        sortBaseStatusList.forEach {
          sortPriority += when (it) {
            BaseStatus.HP -> pokemon.hp
            BaseStatus.ATK -> pokemon.atk
            BaseStatus.DEF -> pokemon.def
            BaseStatus.SP_ATK -> pokemon.spAtk
            BaseStatus.SP_DEF -> pokemon.spDef
            BaseStatus.SPEED -> pokemon.speed
          }
        }
        if (_isSortDesc.value) {
          -sortPriority
        } else {
          sortPriority
        }
      }
    }
  }

  private fun List<Pokemon>.sortBodyStatus(): List<Pokemon> {
    return if (selectedBodyStatus.value != null) {
      sortedBy { pokemon ->
        var sortPriority = 0
        when (selectedBodyStatus.value) {
          BodyStatus.WEIGHT -> {
            sortPriority = pokemon.weight
          }
          BodyStatus.HEIGHT -> {
            sortPriority = pokemon.height
          }
          else -> {}
        }
        if (_isSortDesc.value) {
          -sortPriority
        } else {
          sortPriority
        }
      }
    } else {
      this
    }
  }

  fun changeSortOrder() {
    _isSortDesc.value = !_isSortDesc.value
    startSortAndFilter()
  }

}
