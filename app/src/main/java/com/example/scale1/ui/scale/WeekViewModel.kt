package com.example.scale1.ui.scale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scale1.model.WeekData
import com.example.scale1.service.WeekUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date

sealed class UiState {
    object Loading : UiState()
    data class Success(val weekData: List<WeekData>) : UiState()
    data class Error(val message: String) : UiState()
}


class WeekViewModel(private val useCase: WeekUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private val _selectedName = MutableStateFlow<String?>(null)
    val selectedName: StateFlow<String?> = _selectedName.asStateFlow()

    private val _onOpenBottomSheet = MutableStateFlow(false)
    val onOpenBottomSheet: StateFlow<Boolean> = _onOpenBottomSheet.asStateFlow()

    private val _onOpenCalendar = MutableStateFlow(false)
    val onOpenCalendar: StateFlow<Boolean> = _onOpenCalendar.asStateFlow()

    private val _localDate = MutableStateFlow(getLocalDate())
    val localDate: StateFlow<LocalDate> = _localDate

    private fun getLocalDate(): LocalDate {
        var localDate = LocalDate.now()

        if (localDate.dayOfWeek == DayOfWeek.SATURDAY) {
            localDate = localDate.plusDays(2)
        }
        if (localDate.dayOfWeek == DayOfWeek.SUNDAY) {
            localDate = localDate.plusDays(1)
        }
        return localDate
    }

    private fun getWeekNumber(localDate: LocalDate): Int {
        val currentDate = Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC))
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        return calendar.get(Calendar.WEEK_OF_YEAR)
    }

    fun refreshDate(){
        onDateSelected(getLocalDate())
    }

    fun previousWeek(){
        val date = _localDate.value
        onDateSelected(date.minusDays(7))
    }

    fun nextWeek(){
        val date = _localDate.value
        onDateSelected(date.plusDays(7))
    }

    fun onDateSelected(date: LocalDate){
        viewModelScope.launch {
            try {
                var localDate = date
                if (localDate.dayOfWeek == DayOfWeek.SATURDAY) {
                    localDate = localDate.plusDays(2)
                }
                if (localDate.dayOfWeek == DayOfWeek.SUNDAY) {
                    localDate = localDate.plusDays(1)
                }

                _localDate.value = localDate
                val scaleWeek = useCase.getScaleWeek(getWeekNumber(localDate))
                _uiState.value = UiState.Success(scaleWeek)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }

    init {
        onDateSelected(_localDate.value)
    }

    fun onNameSelected(name: String?) {
        _selectedName.value = name
    }

    fun onOpenBottomSheet(status: Boolean) {
        _onOpenBottomSheet.value = status
    }

    fun onOpenCalendar(status: Boolean) {
        _onOpenCalendar.value = status
    }
}