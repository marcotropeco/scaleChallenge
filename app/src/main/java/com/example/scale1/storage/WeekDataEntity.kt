package com.example.scale1.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
@Entity(tableName = "week_data_table")
data class WeekDataEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "monday")
    @TypeConverters(Converters::class)
    val monday: List<PeopleEntity>,
    @ColumnInfo(name = "tuesday")
    @TypeConverters(Converters::class)
    val tuesday: List<PeopleEntity>,
    @ColumnInfo(name = "wednesday")
    @TypeConverters(Converters::class)
    val wednesday: List<PeopleEntity>,
    @ColumnInfo(name = "thursday")
    @TypeConverters(Converters::class)
    val thursday: List<PeopleEntity>,
    @ColumnInfo(name = "friday")
    @TypeConverters(Converters::class)
    val friday: List<PeopleEntity>
)
