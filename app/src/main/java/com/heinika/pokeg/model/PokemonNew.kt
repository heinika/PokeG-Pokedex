package com.heinika.pokeg.model


import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonNew(
    @Json(name = "base_experience")
    val baseExperience: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "identifier")
    val identifier: String,
    @Json(name = "is_default")
    val isDefault: Int,
    @Json(name = "order")
    val order: Int,
    @Json(name = "species_id")
    val speciesId: Int,
    @Json(name = "weight")
    val weight: Int
){
    fun getFormatId(): String = String.format("#%03d", id)

    fun getFormatWeight(): String = String.format("%.1f KG", weight.toFloat() / 10)

    fun getFormatHeight(): String = String.format("%.1f M", height.toFloat() / 10)

    fun toPokemon(pokemonRes: PokemonRes): Pokemon {
        var totalBaseStat = 0
        var hp = 0
        var atk = 0
        var def = 0
        var spAtk = 0
        var spDef = 0
        var speed = 0
        var generationId: Int
        var isBaby: Boolean
        var isLegendary: Boolean
        var isMythical: Boolean
        pokemonRes.fetchPokemonBaseStat().filter { it.pokemonId == id }.apply {
            hp = first { it.statId.isHPStat }.baseStat
            atk = first { it.statId.isAttackStat }.baseStat
            def = first { it.statId.isDefenseStat }.baseStat
            spAtk = first { it.statId.isSAttackStat }.baseStat
            spDef = first { it.statId.isSDefenseStat }.baseStat
            speed = first { it.statId.isSPeedStat }.baseStat
        }.forEach {
            totalBaseStat += it.baseStat
        }

        pokemonRes.fetchPokemonSpecies().first { it.id == speciesId }.let {
            generationId = it.generationId
            isBaby = it.isBaby.toBoolean
            isLegendary = it.isLegendary.toBoolean
            isMythical = it.isMythical.toBoolean
        }
        return Pokemon(
            id = id,
            speciesId = speciesId,
            name = identifier,
            types = pokemonRes.fetchPokemonType().filter { it.pokemonId == id },
            totalBaseStat = totalBaseStat,
            hp, atk, def, spAtk, spDef, speed,
            generationId = generationId,
            weight = weight,
            height = height,
            isBaby = isBaby,
            isLegendary = isLegendary,
            isMythical = isMythical
        )
    }
}