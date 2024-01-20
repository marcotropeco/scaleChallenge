package com.example.scale1.service

import com.example.scale1.model.WeekData

class WeekUseCase(private val repository: WeekRepository) {
    suspend fun getScaleWeek(week: Int): List<WeekData> {
        return repository.getWeekData(week)
    }
}