package com.example.scale1.service

import com.example.scale1.model.ResultUseCase
import com.example.scale1.model.WeekData

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