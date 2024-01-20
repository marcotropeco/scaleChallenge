package com.example.scale1.di

import com.example.scale1.service.WeekRepository
import com.example.scale1.service.WeekRepositoryImpl
import com.example.scale1.service.WeekUseCase
import com.example.scale1.ui.scale.WeekViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<WeekRepository> { WeekRepositoryImpl() }
    factory { WeekUseCase(get()) }
    viewModel { WeekViewModel(get()) }
}