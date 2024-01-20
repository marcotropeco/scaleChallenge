package com.example.scale1.ui.scale

import androidx.lifecycle.ViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date

class ScaleViewModel : ViewModel() {
    // Adicione os dados que você deseja exibir na tabela
    val dadosDaTabela get() = run {

            var localDate = LocalDate.now()

            if( localDate.dayOfWeek == DayOfWeek.SATURDAY ){
                localDate = localDate.plusDays(2)
            }

            if( localDate.dayOfWeek == DayOfWeek.SUNDAY ){
                localDate = localDate.plusDays(1)
            }
        
            val currentDate = Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC))

            val weekNumber = getWeekNumber(currentDate)

             if (weekNumber % 2 == 1) {
                mutableListOf(
                    TabelaItem("Item 1", "Valor 1"),
                    TabelaItem("Item 2", "Valor 2"),
                    // Adicione mais itens conforme necessário
                )
            } else {
                mutableListOf(
                    TabelaItem("Item 3", "Valor 2"),
                )
            }


        }

    private fun getWeekNumber(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.WEEK_OF_YEAR)
    }
}