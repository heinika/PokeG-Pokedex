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

package com.heinika.pokeg.persistence

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.heinika.pokeg.model.PokemonInfo
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class StatResponseConverter constructor(
  private val moshi: Moshi
) {

  @TypeConverter
  fun fromString(value: String): List<PokemonInfo.StatResponse>? {
    val listType =
      Types.newParameterizedType(List::class.java, PokemonInfo.StatResponse::class.java)
    val adapter: JsonAdapter<List<PokemonInfo.StatResponse>> = moshi.adapter(listType)
    return adapter.fromJson(value)
  }


  @TypeConverter
  fun fromInfoStat(type: List<PokemonInfo.StatResponse>?): String {
    val listType =
      Types.newParameterizedType(List::class.java, PokemonInfo.StatResponse::class.java)
    val adapter: JsonAdapter<List<PokemonInfo.StatResponse>> = moshi.adapter(listType)
    return adapter.toJson(type)
  }
}
