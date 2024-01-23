package com.example.scale1.domain.repository

import com.example.scale1.domain.models.WeekData

interface WeekRepository {
    suspend fun getWeekDataStorage(week: Int): List<WeekData>
}