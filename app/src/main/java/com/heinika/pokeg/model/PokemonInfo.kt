/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.heinika.pokeg.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class PokemonInfo(
    @field:Json(name = "id") @PrimaryKey val id: Int,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "height") val height: Int,
    @field:Json(name = "weight") val weight: Int,
    @field:Json(name = "base_experience") val experience: Int,
    @field:Json(name = "types") val types: List<TypeResponse>,
    @field:Json(name = "stats") val statResponses: List<StatResponse>,
    val hp: Int = statResponses[0].baseStat,
    val attack: Int = statResponses[1].baseStat,
    val defense: Int = statResponses[2].baseStat,
    val specialAttack: Int = statResponses[3].baseStat,
    val specialDefense: Int = statResponses[4].baseStat,
    val speed: Int = statResponses[5].baseStat
) {
    fun getIdString(): String = String.format("#%03d", id)
    fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
    fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
    fun getHpString(): String = "$hp/$maxHp"
    fun getAttackString(): String = "$attack/$maxAttack"
    fun getDefenseString(): String = "$defense/$maxDefense"
    fun getSpeedString(): String = "$speed/$maxSpeed"

    @JsonClass(generateAdapter = true)
    data class TypeResponse(
        @field:Json(name = "slot") val slot: Int,
        @field:Json(name = "type") val type: Type
    )

    @JsonClass(generateAdapter = true)
    data class StatResponse(
        @field:Json(name = "base_stat") val baseStat: Int
    )

    @JsonClass(generateAdapter = true)
    data class Type(
        @field:Json(name = "name") val name: String
    )

    companion object {
        const val maxHp = 200
        const val maxAttack = 200
        const val maxDefense = 200
        const val maxSpecialAttack = 200
        const val maxSpecialDefense = 200
        const val maxSpeed = 200
    }
}
