package com.heinika.pokeg.module.ability

import androidx.lifecycle.viewModelScope
import com.heinika.pokeg.base.LiveCoroutinesViewModel
import com.heinika.pokeg.model.PokemonAbility
import com.heinika.pokeg.repository.AbilityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AbilityViewModel @Inject constructor(private val abilityRepository: AbilityRepository) : LiveCoroutinesViewModel() {
  private var allPokemonAbilities: List<PokemonAbility>? = null

  init {
    viewModelScope.launch {
      abilityRepository.allPokemonAbilitiesFlow().collect { pokemonAbilities ->
        allPokemonAbilities = pokemonAbilities
      }
    }
  }

  fun findPokemonListByAbilityId(abilityId: Int): List<Int> {
    return mutableListOf<Int>().apply {
      allPokemonAbilities?.let { pokemonAbilities ->
        addAll(pokemonAbilities.filter { it.abilityId == abilityId }.map { it.pokemonId })
      }
    }
  }
}