package com.example.scale1.ui.scale

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import com.example.scale1.extensions.convertMillisToLocalDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDatePicker(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {

    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.convertMillisToLocalDate()

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                selectedDate?.let(onDateSelected)
                onDismiss()
            }

            ) {
                Text(text = Defines.TEXT_OK)
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = Defines.TEXT_CANCEL)
            }
        },
        content = {
            DatePicker(state = datePickerState)
        }
    )
}