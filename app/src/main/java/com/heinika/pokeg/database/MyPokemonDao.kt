package com.heinika.pokeg.database

import androidx.room.*
import com.heinika.pokeg.model.MyPokemon
import kotlinx.coroutines.flow.Flow


@Dao
interface MyPokemonDao {

  @Query("SELECT * from MyPokemon")
  suspend fun fetchMyPokemonList():List<MyPokemon>

  @Query("SELECT * from MyPokemon")
  fun allMyPokemonListFlow(): Flow<List<MyPokemon>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertMyPokemon(myPokemon: MyPokemon)

  @Update
  suspend fun updateMyPokemon(myPokemon: MyPokemon)
}