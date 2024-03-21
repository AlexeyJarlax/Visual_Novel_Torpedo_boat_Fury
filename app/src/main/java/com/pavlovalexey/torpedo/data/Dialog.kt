package com.pavlovalexey.torpedo.data

// модель для диалога
data class Dialog(val characterName: String, val text: String, val choices: List<String>? = null)