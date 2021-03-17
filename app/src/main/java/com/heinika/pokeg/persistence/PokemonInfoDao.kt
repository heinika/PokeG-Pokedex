package com.heinika.pokeg.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heinika.pokeg.model.PokemonInfo

@Dao
interface PokemonInfoDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertPokemonInfo(pokemonInfo: PokemonInfo)

  @Query("SELECT * FROM PokemonInfo WHERE name = :name_")
  suspend fun getPokemonInfo(name_: String): PokemonInfo?
}
