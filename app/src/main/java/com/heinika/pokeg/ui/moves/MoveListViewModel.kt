package com.heinika.pokeg.ui.moves

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.repository.MoveListRepository
import com.heinika.pokeg.ui.moves.compose.SortChipStatus
import com.heinika.pokeg.utils.MoveProp
import com.heinika.pokeg.utils.PokemonProp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MoveListViewModel @Inject constructor(moveListRepository: MoveListRepository) : ViewModel() {
  private val _allMovesState = mutableStateOf<List<Move>>(emptyList())
  private var baseMoves = emptyList<Move>()
  private var sortOrderTypes = MoveProp.SortType.values().toList()
  private var powerState = SortChipStatus.Disable
  private var accuracyState = SortChipStatus.Disable
  private var ppState = SortChipStatus.Disable
  private var selectedDamageClass: MoveProp.DamageClass? = null
  val allMovesState: State<List<Move>> = _allMovesState

  private var typeFilterList: List<PokemonProp.Type> = emptyList()
  private var generationFilterList: List<PokemonProp.Generation> = emptyList()

  init {
    viewModelScope.launch {
      moveListRepository.allMovesFlow().collect {
        baseMoves = it
        _allMovesState.value = it
      }
    }
  }

  fun filterTypes(selectedTypes: List<PokemonProp.Type>) {
    typeFilterList = selectedTypes
    startFitter()
  }

  fun filterGenerations(selectedGenerations: List<PokemonProp.Generation>) {
    generationFilterList = selectedGenerations
    startFitter()
  }

  fun filterDamageClass(damageClass: MoveProp.DamageClass?) {
    selectedDamageClass = damageClass
    startFitter()
  }

  fun changeSortOrder(list: List<MoveProp.SortType>) {
    Timber.i("changeSortOrder ${list.joinToString()}")
    sortOrderTypes = list
    startFitter()
  }

  fun changSortState(type: MoveProp.SortType, state: SortChipStatus) {
    when (type) {
      MoveProp.SortType.Power -> powerState = state
      MoveProp.SortType.Accuracy -> accuracyState = state
      MoveProp.SortType.PP -> ppState = state
    }
    startFitter()
  }

  private fun getSortTypesState(type: MoveProp.SortType): SortChipStatus {
    return when (type) {
      MoveProp.SortType.Power -> powerState
      MoveProp.SortType.Accuracy -> accuracyState
      MoveProp.SortType.PP -> ppState
    }
  }

  private fun startFitter() {
    viewModelScope.launch {
      _allMovesState.value = withContext(Dispatchers.IO) {
        baseMoves.filter { move ->
          if (typeFilterList.isEmpty()) {
            true
          } else {
            typeFilterList.map { it.typeId }.contains(move.typeId)
          }
        }.filter { move ->
          if (generationFilterList.isEmpty()) {
            true
          } else {
            generationFilterList.map { it.id }.contains(move.generationId)
          }
        }.filter { move ->
          if (selectedDamageClass != null) {
            move.damageClassId == selectedDamageClass!!.ordinal + 1
          } else {
            true
          }
        }.sortedBy {
          when {
            sortOrderTypes.all { getSortTypesState(it) == SortChipStatus.Disable } -> 0
            else -> {
              var priority = 0
              sortOrderTypes.reversed().forEach { type ->
                val typeState = getSortTypesState(type)
                if (typeState != SortChipStatus.Disable) {
                  val f = if (typeState == SortChipStatus.Ascending) 1 else -1
                  priority += when (type) {
                    MoveProp.SortType.Power -> it.power.toPriorityInt * 1000 * f
                    MoveProp.SortType.Accuracy -> it.accuracy.toPriorityInt * 1000 * f
                    MoveProp.SortType.PP -> it.pp.toPriorityInt * 1000 * f
                  }
                }
              }
              Timber.i("priority: $priority,${it.eName},${it.id}")
              priority
            }
          }
        }
      }
    }
  }

  private val String.toPriorityInt: Int
    get() {
      return try {
        toInt()
      } catch (e: Exception) {
        0
      }
    }

}