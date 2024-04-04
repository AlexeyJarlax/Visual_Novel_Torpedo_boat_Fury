package com.pavlovalexey.torpedo.model

/** ресурсы игры, критическое количество некоторых приведет к поражению или усложнит игру. В разных ситуациях диалогов могут обмениваться.*/

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


