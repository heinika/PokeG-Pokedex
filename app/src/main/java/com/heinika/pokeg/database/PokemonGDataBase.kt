package com.heinika.pokeg.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.heinika.pokeg.model.MyPokemon

@Database(entities = [MyPokemon::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
public abstract class PokemonGDataBase : RoomDatabase() {
  abstract fun myPokemonDao(): MyPokemonDao
}