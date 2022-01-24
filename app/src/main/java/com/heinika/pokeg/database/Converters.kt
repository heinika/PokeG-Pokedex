package com.heinika.pokeg.database

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun listToJson(value: List<Int>) = value.joinToString(",")

    @TypeConverter
    fun jsonToList(value: String) = value.split(",").map { it.toInt() }
}