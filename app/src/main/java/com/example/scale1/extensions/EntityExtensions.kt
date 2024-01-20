package com.example.scale1.extensions

import com.example.scale1.model.People
import com.example.scale1.model.WeekData
import com.example.scale1.storage.PeopleEntity
import com.example.scale1.storage.WeekDataEntity
fun List<PeopleEntity>.toListPeople(): List<People>{
    return this.map{
        People(
            name = it.name,
            mode = it.mode
        )
    }
}

fun List<WeekDataEntity>.toListWeekData(): List<WeekData> {
    return this.map {
        WeekData(
            monday = it.monday.toListPeople(),
            tuesday = it.tuesday.toListPeople(),
            wednesday = it.wednesday.toListPeople(),
            thursday = it.tuesday.toListPeople(),
            friday = it.friday.toListPeople()
        )
    }
}