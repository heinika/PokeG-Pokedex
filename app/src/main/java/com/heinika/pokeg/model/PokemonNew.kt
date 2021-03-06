package com.heinika.pokeg.model


import com.heinika.pokeg.ConfigMMKV
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
}