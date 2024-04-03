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
) {
    fun getRelationship(): Any {
        return relationship
    }

//    fun getRubles(): Int {
//        return rubles
//    }
//
//    fun getFame(): Int {
//        return fame
//    }
//
//    fun getTeamLoyalty(): Int {
//        return teamLoyalty
//    }
//
//    fun getVodka(): Int {
//        return vodka
//    }
//
//    fun getMaxim(): Int {
//        return maxim
//    }
//
//    fun getCapital(): Int {
//        return capital
//    }
//
//    fun getNecronomicon(): Int {
//        return necronomicon
//    }
//
//    fun getNeisvestno(): Int {
//        return neisvestno
//    }
//
//    fun getRelationship(): Int {
//        return relationship
//    }

//    companion object {
//        fun getRelationship(): Int {
//            val relationship = getRelationship()
//            return relationship
//        }
//    }
}


