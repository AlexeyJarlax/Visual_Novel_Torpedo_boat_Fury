package com.pavlovalexey.torpedo.model

/** карты, используемые в бою, дают бонус к исходу боя */

data class Card(
    val title: String,
    val emoji: String
)

val cards = listOf(
    Card("Грязные махинации", "🎌"),
    Card("РЭБ", "📻"),
    Card("Корабль на разграбление", "🏴‍☠️"),
    Card("Оскал капитализма", "💵"),
    Card("Призыв Ктулху", "🐙"),
    Card("Кровавый ритуал", "🦈"),
    Card("Нечистивое преимущество", "⚰️"),
    Card("Мозговые креветки", "🦐"),
    Card("Всеми любимые тентакли", "🦑"),
)