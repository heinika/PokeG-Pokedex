package com.heinika.pokeg.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.heinika.pokeg.model.MyPokemon


@Dao
interface MyPokemonDao {

  @Query("SELECT * from MyPokemon")
  fun getAlMyPokemon():List<MyPokemon>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertMyPokemon(myPokemon: MyPokemon)
}