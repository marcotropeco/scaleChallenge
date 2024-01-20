package com.example.scale1.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface PeopleDao {
    @Insert
    suspend fun insertPeople(people: PeopleEntity)

    @Query("SELECT * FROM people_table")
    suspend fun getAllPeople(): List<PeopleEntity>

    @Query("Delete from people_table")
    suspend fun deleteAllPeople()
}
