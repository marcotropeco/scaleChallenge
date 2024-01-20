package com.example.scale1.service

import com.example.scale1.model.People
import com.example.scale1.model.WeekData

interface WeekRepository {
    suspend fun getWeekData(week: Int): List<WeekData>
}

class WeekRepositoryImpl : WeekRepository {
    override suspend fun getWeekData(week: Int): List<WeekData> {
        val weeks = listOf(
            WeekData(
                monday = listOf(
                    People(name = "Ana", mode = "presencial"),
                    People(name = "Maria", mode = "presencial"),
                    People(name = "Jose", mode = "home-office"),
                    People(name = "Pedro", mode = "home-office")
                ),
                tuesday = listOf(
                    People(name = "Ana", mode = "presencial"),
                    People(name = "Maria", mode = "presencial"),
                    People(name = "Jose", mode = "home-office"),
                    People(name = "Pedro", mode = "home-office")
                ),
                wednesday = listOf(
                    People(name = "Ana", mode = "presencial"),
                    People(name = "Maria", mode = "presencial"),
                    People(name = "Jose", mode = "presencial"),
                    People(name = "Pedro", mode = "presencial")
                ),
                thursday = listOf(
                    People(name = "Ana", mode = "home-office"),
                    People(name = "Maria", mode = "home-office"),
                    People(name = "Jose", mode = "presencial"),
                    People(name = "Pedro", mode = "presencial")
                ),
                friday = listOf(
                    People(name = "Ana", mode = "home-office"),
                    People(name = "Maria", mode = "home-office"),
                    People(name = "Jose", mode = "presencial"),
                    People(name = "Pedro", mode = "presencial")
                )
            ),
            WeekData(
                monday = listOf(
                    People(name = "Ana", mode = "home-office"),
                    People(name = "Maria", mode = "home-office"),
                    People(name = "Jose", mode = "presencial"),
                    People(name = "Pedro", mode = "presencial")
                ),
                tuesday = listOf(
                    People(name = "Ana", mode = "home-office"),
                    People(name = "Maria", mode = "home-office"),
                    People(name = "Jose", mode = "presencial"),
                    People(name = "Pedro", mode = "presencial")
                ),
                wednesday = listOf(
                    People(name = "Ana", mode = "presencial"),
                    People(name = "Maria", mode = "presencial"),
                    People(name = "Jose", mode = "presencial"),
                    People(name = "Pedro", mode = "presencial")
                ),
                thursday = listOf(
                    People(name = "Ana", mode = "presencial"),
                    People(name = "Maria", mode = "presencial"),
                    People(name = "Jose", mode = "home-office"),
                    People(name = "Pedro", mode = "home-office")
                ),
                friday = listOf(
                    People(name = "Ana", mode = "presencial"),
                    People(name = "Maria", mode = "presencial"),
                    People(name = "Jose", mode = "home-office"),
                    People(name = "Pedro", mode = "home-office")
                )
            )
        )

        val mod = week % weeks.size
        if(mod == 0){
            return listOf(weeks[weeks.size-1])
        }
        return listOf(weeks[mod-1])
    }
}