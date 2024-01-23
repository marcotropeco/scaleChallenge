package com.example.scale1.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromString(value: String?): List<PeopleEntity>{
        val listType = object : TypeToken<List<PeopleEntity>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<PeopleEntity>): String? {
        return Gson().toJson(list)
    }
}
