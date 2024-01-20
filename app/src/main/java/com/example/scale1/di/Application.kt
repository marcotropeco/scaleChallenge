package com.example.scale1.di

import android.app.Application
import com.example.scale1.storage.PeopleEntity
import com.example.scale1.storage.WeekDataDao
import com.example.scale1.storage.WeekDataEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {

    private val weekDataDao: WeekDataDao by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(appModule)
        }

        val weekDataEntities = listOf(
            WeekDataEntity(
                monday = listOf(
                    PeopleEntity(name = "Ana", mode = "presencial"),
                    PeopleEntity(name = "Maria", mode = "presencial"),
                    PeopleEntity(name = "Jose", mode = "home-office"),
                    PeopleEntity(name = "Pedro", mode = "home-office")
                ),
                tuesday = listOf(
                    PeopleEntity(name = "Ana", mode = "presencial"),
                    PeopleEntity(name = "Maria", mode = "presencial"),
                    PeopleEntity(name = "Jose", mode = "home-office"),
                    PeopleEntity(name = "Pedro", mode = "home-office")
                ),
                wednesday = listOf(
                    PeopleEntity(name = "Ana", mode = "presencial"),
                    PeopleEntity(name = "Maria", mode = "presencial"),
                    PeopleEntity(name = "Jose", mode = "presencial"),
                    PeopleEntity(name = "Pedro", mode = "presencial")
                ),
                thursday = listOf(
                    PeopleEntity(name = "Ana", mode = "home-office"),
                    PeopleEntity(name = "Maria", mode = "home-office"),
                    PeopleEntity(name = "Jose", mode = "presencial"),
                    PeopleEntity(name = "Pedro", mode = "presencial")
                ),
                friday = listOf(
                    PeopleEntity(name = "Ana", mode = "home-office"),
                    PeopleEntity(name = "Maria", mode = "home-office"),
                    PeopleEntity(name = "Jose", mode = "presencial"),
                    PeopleEntity(name = "Pedro", mode = "presencial")
                )
            ),
            WeekDataEntity(
                monday = listOf(
                    PeopleEntity(name = "Ana", mode = "home-office"),
                    PeopleEntity(name = "Maria", mode = "home-office"),
                    PeopleEntity(name = "Jose", mode = "presencial"),
                    PeopleEntity(name = "Pedro", mode = "presencial")
                ),
                tuesday = listOf(
                    PeopleEntity(name = "Ana", mode = "home-office"),
                    PeopleEntity(name = "Maria", mode = "home-office"),
                    PeopleEntity(name = "Jose", mode = "presencial"),
                    PeopleEntity(name = "Pedro", mode = "presencial")
                ),
                wednesday = listOf(
                    PeopleEntity(name = "Ana", mode = "presencial"),
                    PeopleEntity(name = "Maria", mode = "presencial"),
                    PeopleEntity(name = "Jose", mode = "presencial"),
                    PeopleEntity(name = "Pedro", mode = "presencial")
                ),
                thursday = listOf(
                    PeopleEntity(name = "Ana", mode = "presencial"),
                    PeopleEntity(name = "Maria", mode = "presencial"),
                    PeopleEntity(name = "Jose", mode = "home-office"),
                    PeopleEntity(name = "Pedro", mode = "home-office")
                ),
                friday = listOf(
                    PeopleEntity(name = "Ana", mode = "presencial"),
                    PeopleEntity(name = "Maria", mode = "presencial"),
                    PeopleEntity(name = "Jose", mode = "home-office"),
                    PeopleEntity(name = "Pedro", mode = "home-office")
                )
            )
        )

        CoroutineScope(Dispatchers.Default).launch {
            weekDataDao.deleteAllWeekData()
            weekDataDao.insertWeekData(weekDataEntities[0])
            weekDataDao.insertWeekData(weekDataEntities[1])
        }
    }
}