package com.example.scale1.domain.models

data class WeekData(
    val monday: List<People>,
    val tuesday: List<People>,
    val wednesday: List<People>,
    val thursday: List<People>,
    val friday: List<People>
)

data class People(
    val name : String,
    val mode: String
)
