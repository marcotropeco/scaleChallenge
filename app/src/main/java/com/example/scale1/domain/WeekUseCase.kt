package com.example.scale1.domain

import com.example.scale1.domain.models.ResultUseCase
import com.example.scale1.domain.models.WeekData
import com.example.scale1.domain.repository.WeekRepository

class WeekUseCase(private val repository: WeekRepository) {
    suspend operator fun invoke(week: Int): ResultUseCase<List<WeekData>> {
        return try {
            val data = repository.getWeekDataStorage(week)
            ResultUseCase.Success(data)
        } catch (e: Exception) {
            ResultUseCase.Failure(e)
        }
    }

}