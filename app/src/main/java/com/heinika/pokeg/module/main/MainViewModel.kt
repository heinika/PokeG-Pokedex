package com.heinika.pokeg.module.main

import androidx.lifecycle.*
import com.heinika.pokeg.ConfigMMKV.favoritePokemons
import com.heinika.pokeg.info.*
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.MainRepository
import com.heinika.pokeg.repository.res.PokemonRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@Suppress("unused")
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

  private val _pokemonSortListLiveData = MutableLiveData<List<Pokemon>>(listOf())
  val pokemonSortListLiveData: LiveData<List<Pokemon>> = _pokemonSortListLiveData

  private lateinit var allPokemonList: List<Pokemon>
  private lateinit var hiSuiPokemonList: List<Pokemon>
  private lateinit var basePokemonList: List<Pokemon>

  var filterTypeList: List<Int> = emptyList()
    set(value) {
      field = value
      startSortAndFilter()
    }

  private val _filterGenerations: MutableLiveData<List<Generation>> =
    MutableLiveData(emptyList())
  val filterGenerations: LiveData<List<Generation>> = _filterGenerations

  private val _filterTags: MutableLiveData<List<Tag>> =
    MutableLiveData(emptyList())
  private val filterTags: LiveData<List<Tag>> = _filterTags

  private val _sortBaseStatusList: MutableLiveData<List<BaseStatus>> =
    MutableLiveData(emptyList())

  private val _selectedBodyStatus: MutableLiveData<BodyStatus?> =
    MutableLiveData(null)
  val selectedBodyStatus: LiveData<BodyStatus?> = _selectedBodyStatus

  val sortBaseStatusList: LiveData<List<BaseStatus>> = _sortBaseStatusList

  var onRefreshFavorite: ((Pokemon) -> Unit)? = null

  init {
    Timber.d("init MainViewModel")

    viewModelScope.launch {
      mainRepository.fetchPokemonList(
        onStart = { _isLoading.postValue(true) },
        onSuccess = { _isLoading.postValue(false) },
        onError = { _toastMessage.postValue(it) }
      ).collect { pokemonList ->
        allPokemonList = pokemonList
        hiSuiPokemonList =
          RegionNumber.HiSuiMap.keys.map { hisuiNumber -> pokemonList.first { it.globalId == hisuiNumber } }
        basePokemonList = pokemonList
        _pokemonSortListLiveData.value = pokemonList
      }
    }
  }

  fun changDexType(dexType: DexType) {
    basePokemonList = when (dexType) {
      DexType.Global -> allPokemonList
      DexType.HiSui -> hiSuiPokemonList
    }
    _pokemonSortListLiveData.value = basePokemonList
  }

  fun changeBasePokemonListFavorite(pokemon: Pokemon, isFavorite: Boolean) {
    pokemon.isFavorite = isFavorite
    onRefreshFavorite?.invoke(pokemon)
  }

  fun changeSortBaseStatusList(list: List<BaseStatus>) {
    _sortBaseStatusList.value = list
  }

  fun changeBodyStatus(bodyStatus: BodyStatus?) {
    _selectedBodyStatus.value = bodyStatus
  }

  fun changeGenerations(list: List<Generation>) {
    _filterGenerations.value = list
    startSortAndFilter()
  }

  fun changeTags(list: List<Tag>) {
    _filterTags.value = list
    startSortAndFilter()
  }

  fun startSortAndFilter() {
    _pokemonSortListLiveData.value =
      this.basePokemonList.filterType().filterGenerations().filterTags().sortBaseStatus()
        .sortBodyStatus()
  }

  fun setSearchText(searchText: CharSequence?) {
    _searchText.value = searchText
    if (searchText.isNullOrEmpty()) {
      _pokemonSortListLiveData.value = this.basePokemonList
    } else {
      this.basePokemonList.filter {
        it.getCName(pokemonRes).contains(searchText, true) || it.id.toString()
          .contains(searchText, true)
      }.let {
        _pokemonSortListLiveData.value = it
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
      if (filterGenerations.value!!.isEmpty()) {
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

  private fun List<Pokemon>.filterTags(): List<Pokemon> {
    return filter { pokemon ->
      if (filterTags.value!!.isEmpty()) {
        true
      } else {
        filterTags.value!!.forEach { tag ->
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
          }
        }
        false
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
            BaseStatus.HP -> pokemon.hp
            BaseStatus.ATK -> pokemon.atk
            BaseStatus.DEF -> pokemon.def
            BaseStatus.SP_ATK -> pokemon.spAtk
            BaseStatus.SP_DEF -> pokemon.spDef
            BaseStatus.SPEED -> pokemon.speed
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
        if (isSortDesc.value!!) {
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
    _isSortDesc.value = !_isSortDesc.value!!
    startSortAndFilter()
  }

}
