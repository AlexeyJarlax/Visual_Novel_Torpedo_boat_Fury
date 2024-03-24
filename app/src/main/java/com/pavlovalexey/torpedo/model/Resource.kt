package com.pavlovalexey.torpedo.model

data class Resource(
    val rubles: Int,
    val fame: Int,
    val teamLoyalty: Int,
    val vodka: Int,
    val maxim: Int,
    val capital: Boolean = false, // сценарий Карл Маркс
    val necronomicon: Boolean = false // сценарий Карл Маркс
)