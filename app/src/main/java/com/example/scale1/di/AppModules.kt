package com.example.scale1.di

import com.example.scale1.services.WeekRepositoryImpl
import com.example.scale1.domain.WeekUseCase
import com.example.scale1.domain.repository.WeekRepository
import com.example.scale1.storage.AppDatabase
import com.example.scale1.ui.scale.WeekViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { AppDatabase.getDatabase(get()) }
    single { get<AppDatabase>().peopleDao() }
    single { get<AppDatabase>().weekDataDao() }

    single<WeekRepository> { WeekRepositoryImpl(get()) }
    factory { WeekUseCase(get()) }
    viewModel { WeekViewModel(get()) }
}