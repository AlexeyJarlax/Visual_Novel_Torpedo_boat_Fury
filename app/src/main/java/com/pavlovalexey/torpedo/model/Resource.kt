package com.pavlovalexey.torpedo.model

data class Resource(
    var rubles: Int,
    var fame: Int,
    var teamLoyalty: Int,
    var vodka: Int,
    var maxim: Int,
    var capital: Int, // книги по сценарию Революция
    var necronomicon: Int, // книги по сценарию Некрономикон
    var neisvestno: Int, // ресурс на будущее
    var relationship: Int // отношение в диалоге
)


