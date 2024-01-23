package com.example.scale1.services

import com.example.scale1.extensions.toListWeekData
import com.example.scale1.domain.models.WeekData
import com.example.scale1.domain.repository.WeekRepository
import com.example.scale1.storage.WeekDataDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeekRepositoryImpl(private val weekDataDao: WeekDataDao) : WeekRepository {
    override suspend fun getWeekDataStorage(week: Int): List<WeekData> {
        return withContext(Dispatchers.IO) {
            val listWeeks = weekDataDao.getAllWeekData().toListWeekData()

            when (listWeeks.size) {
                0 -> listOf()
                else -> {
                    val calcModWeek = week % listWeeks.size
                    if (calcModWeek == 0) {
                        listOf(listWeeks[listWeeks.size - 1])
                    } else {
                        listOf(listWeeks[calcModWeek - 1])
                    }
                }
            }
        }
    }
}