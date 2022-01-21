package com.heinika.pokeg.module.mypokemon

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.model.MyPokemonInfo
import com.heinika.pokeg.module.gameprops.props.carryIIIPropsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyPokemonViewModel @Inject constructor(private val myPokemonRepository: MyPokemonRepository) :
  ViewModel() {
  private val _myDetailPokemonInfo: MutableState<MyPokemonInfo?> = mutableStateOf(null)
  val myDetailPokemon: State<MyPokemonInfo?> = _myDetailPokemonInfo

  fun requestInitDetailPokemon(id: Int, name: String) {
    viewModelScope.launch {
      withContext(Dispatchers.IO) {
        val pokemon = PokemonDataCache.pokemonList.first { it.id == id }
        val abilities = myPokemonRepository.fetchPokemonAbilities(id)
        val versions = myPokemonRepository.fetchPokemonMoveVersionList(id, pokemon.speciesId)
        val allMoves = myPokemonRepository.fetchAllMoveList()
        val moves = myPokemonRepository.fetchMoveList(id, pokemon.speciesId, versions.last())
        val myPokemonInfo = MyPokemonInfo(
          name,
          id,
          Generation.values()[pokemon.generationId - 1],
          typeIdList = pokemon.types.map { Type.values()[it.typeId - 1] },
          carry = carryIIIPropsList.first(),
          nature = Nature.Brave,
          ability = abilities.first(),
          moveList = moves.filter { it.methodId == 4 }.takeLast(4).map { it.moveId }
            .map { moveId ->
              allMoves.first { it.id == moveId }
            }
        )
        _myDetailPokemonInfo.value = myPokemonInfo
      }
    }
  }

  fun saveMyPokemonToDataBase(){
    myDetailPokemon.value?.let {

    }
  }
}