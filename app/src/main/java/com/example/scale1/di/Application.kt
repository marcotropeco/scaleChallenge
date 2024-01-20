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
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),
                ),
                tuesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),
                ),
                wednesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "presencial"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),

                ),
                thursday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),

                ),
                friday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),
                )
            ),
            WeekDataEntity(
                monday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),
                ),
                tuesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),
                ),
                wednesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "presencial"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),

                    ),
                thursday = listOf(
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),

                    ),
                friday = listOf(
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),
                )
            ),
            WeekDataEntity(
                monday = listOf(
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),
                ),
                tuesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),
                ),
                wednesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "presencial"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),

                    ),
                thursday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),

                    ),
                friday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),
                )
            ),
            WeekDataEntity(
                monday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),
                ),
                tuesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "home-office"),
                    PeopleEntity(name = "Isadora", mode = "home-office"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "home-office"),
                    PeopleEntity(name = "Bruno", mode = "home-office"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),
                ),
                wednesday = listOf(
                    PeopleEntity(name = "Henrique", mode = "presencial"),
                    PeopleEntity(name = "Rodrigo", mode = "presencial"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "presencial"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "presencial"),

                    ),
                thursday = listOf(
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),

                    ),
                friday = listOf(
                    PeopleEntity(name = "Henrique", mode = "home-office"),
                    PeopleEntity(name = "Rodrigo", mode = "home-office"),
                    PeopleEntity(name = "Marco", mode = "presencial"),
                    PeopleEntity(name = "Isadora", mode = "presencial"),
                    PeopleEntity(name = "Ingrid", mode = "home-office"),
                    PeopleEntity(name = "Rafael", mode = "presencial"),
                    PeopleEntity(name = "Bruno", mode = "presencial"),
                    PeopleEntity(name = "Raphael", mode = "home-office"),
                )
            )
        )

        CoroutineScope(Dispatchers.Default).launch {
            weekDataDao.deleteAllWeekData()
            weekDataDao.insertWeekData(weekDataEntities[0])
            weekDataDao.insertWeekData(weekDataEntities[1])
            weekDataDao.insertWeekData(weekDataEntities[2])
            weekDataDao.insertWeekData(weekDataEntities[3])
        }
    }
}