package com.pavlovalexey.torpedo.di

import com.pavlovalexey.torpedo.repository.GameRepository
import com.pavlovalexey.torpedo.repository.GameRepositoryImpl
import com.pavlovalexey.torpedo.viewmodel.GameViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameModule = module {
    single<GameRepository> { GameRepositoryImpl(get()) }
    viewModel { GameViewModel(get()) }
}