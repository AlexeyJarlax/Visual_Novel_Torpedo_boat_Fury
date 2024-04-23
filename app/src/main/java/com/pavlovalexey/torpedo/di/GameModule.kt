package com.pavlovalexey.torpedo.di

import com.pavlovalexey.torpedo.model.Resource
import com.pavlovalexey.torpedo.repository.GameRepository
import com.pavlovalexey.torpedo.repository.GameRepositoryImpl
import com.pavlovalexey.torpedo.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/** модуль зависимостей. Схема активити - сингл + фрагменты Основные рвсчеты в GameRepositoryImpl*/

val gameModule = module {

    single<Resource> { Resource(0, 0, 0, 0, 0, 0, 0, 0, 0) }

    single<GameRepository> { GameRepositoryImpl(get(), get()) }

    viewModel { MainViewModel(get(), get()) }
}