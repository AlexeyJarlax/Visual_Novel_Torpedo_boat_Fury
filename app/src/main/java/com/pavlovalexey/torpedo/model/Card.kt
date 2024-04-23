package com.pavlovalexey.torpedo.model

data class Card(
    val title: String,
    val emoji: String
)

val cards = listOf(
    Card("Призыв Ктулху", "🐙"),
    Card("Кровавый ритуал", "🦈"),
    Card("Всеми любимые тентакли", "🦑"),
    Card("Мозговые креветки", "🦐")
)