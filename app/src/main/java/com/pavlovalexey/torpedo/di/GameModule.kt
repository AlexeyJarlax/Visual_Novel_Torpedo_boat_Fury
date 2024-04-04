package com.pavlovalexey.torpedo.di

import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.repository.GameRepository
import com.pavlovalexey.torpedo.repository.GameRepositoryImpl
import com.pavlovalexey.torpedo.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {
    // Определяем Resource как синглтон
    single<Resource> { Resource(0, 0, 0, 0, 0, 0, 0, 0, 0) }

    // Передаем ресурс из модуля зависимостей в конструктор GameRepositoryImpl
    single<GameRepository> { GameRepositoryImpl(get(), get()) }

    // Передаем ресурс из модуля зависимостей в конструктор MainViewModel
    viewModel { MainViewModel(get(), get()) }
}