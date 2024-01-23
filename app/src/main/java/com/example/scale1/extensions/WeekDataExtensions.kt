package com.example.scale1.extensions

import com.example.scale1.domain.models.People
import com.example.scale1.domain.models.WeekData
import com.example.scale1.storage.PeopleEntity
import com.example.scale1.storage.WeekDataEntity
import com.example.scale1.ui.scale.Defines

fun List<PeopleEntity>.toListPeople(): List<People>{
    return this.map{
        People(
            name = it.name,
            mode = it.mode
        )
    }
}

fun List<WeekDataEntity>.toListWeekData(): List<WeekData> {
    return this.map  {
        WeekData(
            monday = it.monday.toListPeople(),
            tuesday = it.tuesday.toListPeople(),
            wednesday = it.wednesday.toListPeople(),
            thursday = it.thursday.toListPeople(),
            friday = it.friday.toListPeople()
        )
    }
}

fun List<WeekData>.getDistinctNames(): List<String> {
    return this
        .flatMap {
            listOf(
                People(
                    name = Defines.TEXT_ALL_FILTER,
                    mode = "*"
                )
            ) + it.monday + it.tuesday + it.wednesday + it.thursday + it.friday
        }
        .map { it.name }
        .distinct()
}