package com.example.scale1.service

import com.example.scale1.extensions.toListWeekData
import com.example.scale1.model.WeekData
import com.example.scale1.storage.WeekDataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface WeekRepository {
    suspend fun getWeekDataStorage(week: Int): List<WeekData>
}

class WeekRepositoryImpl(private val weekDataDao: WeekDataDao) : WeekRepository {
    override suspend fun getWeekDataStorage(weekNumberYear: Int): List<WeekData> {
        return withContext(Dispatchers.IO) {
            val listWeeks = weekDataDao.getAllWeekData().toListWeekData()

            val calcModWeek = weekNumberYear % listWeeks.size

            if (calcModWeek == 0) {
                listOf(listWeeks[listWeeks.size - 1])
            }else {
                listOf(listWeeks[calcModWeek - 1])
            }
        }
    }
}