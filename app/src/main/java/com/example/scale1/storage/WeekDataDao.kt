package com.example.scale1.storage

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeekDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeekData(weekData: WeekDataEntity)

    @Query("SELECT id, monday, tuesday, wednesday, thursday, friday FROM week_data_table group by id")
    suspend fun getAllWeekData(): List<WeekDataEntity>

    @Query("DELETE FROM week_data_table")
    suspend fun deleteAllWeekData()
}
