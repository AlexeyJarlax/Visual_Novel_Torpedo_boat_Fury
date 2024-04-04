package com.pavlovalexey.torpedo.model

data class Resource(
    val rubles: Int,
    val fame: Int,
    val teamLoyalty: Int,
    val vodka: Int,
    val maxim: Int,
    val capital: Int, // книги по сценарию Революция
    val necronomicon: Int, // книги по сценарию Некрономикон
    val neisvestno: Int, // ресурс на будущее
    val relationship: Int // отношение в диалоге
)


