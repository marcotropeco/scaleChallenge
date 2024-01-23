package com.example.scale1.ui.scale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scale1.domain.models.ResultUseCase
import com.example.scale1.domain.models.WeekData
import com.example.scale1.domain.WeekUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.ZoneOffset
import java.util.Calendar
import java.util.Date

data class UiStates(
    val loading: Boolean = false,
    val weekData: List<WeekData>? = null,
    val errorMessage: String? = null,
    val selectedName: String? = null,
    val localDate: LocalDate? = null
)
class WeekViewModel(private val useCase: WeekUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(UiStates(loading = true, localDate = getLocalDate()))
    val uiState: StateFlow<UiStates> = _uiState

    init {
        _uiState.value.localDate?.let {
            onDateSelected(validateWeeks(it))
        }
    }

    fun onNameSelected(name: String?) {
        _uiState.update { state ->
            state.copy(
                selectedName = name
            )
        }
    }
    private fun getLocalDate(): LocalDate {
        val localDate = LocalDate.now()
        return validateWeeks(localDate)
    }

    private fun getYearWeekNumber(localDate: LocalDate): Int {
        val currentDate = Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC))
        val calendar = Calendar.getInstance()
        calendar.time = currentDate
        return calendar.get(Calendar.WEEK_OF_YEAR)
    }

    fun refreshActualWeekDate() {
        onDateSelected(getLocalDate())
    }

    fun previousWeek() {
        _uiState.value.localDate?.let {
            onDateSelected(it.minusDays(7))
        }
    }

    fun nextWeek() {
        _uiState.value.localDate?.let {
            onDateSelected(it.plusDays(7))
        }
    }

    private fun validateWeeks(localDate: LocalDate) =
        when (localDate.dayOfWeek) {
            DayOfWeek.SATURDAY -> {
                localDate.plusDays(2)
            }
            DayOfWeek.SUNDAY -> {
                localDate.plusDays(1)
            }
            else -> {
                localDate
            }
        }

    fun onDateSelected(date: LocalDate) {
        viewModelScope.launch {
            try {
                _uiState.update { state ->
                    state.copy(
                        loading = true,
                        localDate = date
                    )
                }

                when (val result = useCase(getYearWeekNumber(date))) {
                    is ResultUseCase.Success -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                weekData = result.data
                            )
                        }
                    }
                    is ResultUseCase.Failure -> {
                        _uiState.update { state ->
                            state.copy(
                                loading = false,
                                errorMessage = result.exception.message.orEmpty()
                            )
                        }
                    }
                }

            } catch (e: Exception) {
                _uiState.update { state ->
                    state.copy(
                        loading = false,
                        errorMessage = e.message ?: "Erro desconhecido"
                    )
                }
            }
        }
    }
}