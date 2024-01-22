package com.example.scale1.ui.scale

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scale1.model.People
import com.example.scale1.model.WeekData
import com.example.scale1.ui.scale.Defines.CURRENT_WEEK
import com.example.scale1.ui.scale.Defines.DATE_FORMAT
import com.example.scale1.ui.scale.Defines.FILTERED_PEOPLE
import com.example.scale1.ui.scale.Defines.FILTER_PEOPLE
import com.example.scale1.ui.scale.Defines.FIND_DATE
import com.example.scale1.ui.scale.Defines.FRIDAY
import com.example.scale1.ui.scale.Defines.LOADING
import com.example.scale1.ui.scale.Defines.MONDAY
import com.example.scale1.ui.scale.Defines.NEXT
import com.example.scale1.ui.scale.Defines.PREVIOUS
import com.example.scale1.ui.scale.Defines.SCALE_WEEK
import com.example.scale1.ui.scale.Defines.TEXT_ALL_FILTER
import com.example.scale1.ui.scale.Defines.TEXT_CANCEL
import com.example.scale1.ui.scale.Defines.TEXT_OK
import com.example.scale1.ui.scale.Defines.THURSDAY
import com.example.scale1.ui.scale.Defines.TUESDAY
import com.example.scale1.ui.scale.Defines.WEDNESDAY
import com.example.scale1.ui.theme.DarkBlue
import com.example.scale1.ui.theme.LightBlue
import com.example.scale1.ui.theme.LightGray
import com.example.scale1.ui.theme.Purple40
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekScreen(viewModel: WeekViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val selectedName by viewModel.selectedName.collectAsState()
    val localDate by viewModel.localDate.collectAsState()

    val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val dateFormatted = localDate.format(formatter)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("$SCALE_WEEK $dateFormatted") },
                modifier = Modifier.padding(horizontal = 24.dp)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxSize()
            ) {

                (uiState as? UiState.Success)?.let {
                    FilterButtons(
                        names = getDistinctNames(it.weekData),
                        nameSelected = selectedName,
                        onNameSelected = viewModel::onNameSelected,
                        onDateSelected = viewModel::onDateSelected,
                        refreshDate = viewModel::refreshDate,
                        nextWeek = viewModel::nextWeek,
                        previousWeek = viewModel::previousWeek
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                when (uiState) {
                    is UiState.Loading -> {
                        ShimmerLoadingExample()
                    }

                    is UiState.Success -> {
                        TableWeek(uiState = uiState as UiState.Success, selectedName)
                    }

                    is UiState.Error -> {
                        Text(
                            text = "Error: ${(uiState as UiState.Error).message}",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    }
                }
            }

        })
}

@Composable
fun ShimmerLoadingExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = 0.1f))
            .padding(16.dp),
    ) {
        Text(
            LOADING, fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
    }
}

@Composable
fun TableWeek(
    uiState: UiState.Success, selectedName: String?
) {
    LazyColumn {
        items(uiState.weekData) { weekData ->
            WeekColumn(weekData, selectedName)
        }
    }
}

@Composable
fun WeekColumn(
    weekData: WeekData,
    selectedName: String?
) {
    Column(
        modifier = Modifier.border(1.dp, Purple40)
    ) {
        DayColumn(dayData = weekData.monday, day = MONDAY, selectedName)
        DayColumn(dayData = weekData.tuesday, day = TUESDAY, selectedName)
        DayColumn(dayData = weekData.wednesday, day = WEDNESDAY, selectedName)
        DayColumn(dayData = weekData.thursday, day = THURSDAY, selectedName)
        DayColumn(dayData = weekData.friday, day = FRIDAY, selectedName)
    }
}

@Composable
fun DayColumn(
    dayData: List<People>,
    day: String,
    selectedName: String?
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Text(
            text = day, fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkBlue)
                .padding(3.dp)
        )

        val data = if (selectedName != TEXT_ALL_FILTER) {
            dayData.filter { selectedName.isNullOrBlank() || it.name == selectedName }
        } else dayData

        data.forEachIndexed { index, person -> PersonRowWeek(index = index, person = person) }
    }
}

@Composable
fun PersonRowWeek(index: Int, person: People) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, LightBlue)
            .background(color = if (index % 2 == 0) LightGray else Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = person.name,
            modifier = Modifier
                .weight(1f)
                .padding(3.dp),
            color = Color.DarkGray
        )
        Text(text = person.mode, color = Color.Blue, modifier = Modifier.padding(3.dp))
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun FilterButtons(
    names: List<String>,
    nameSelected: String?,
    onNameSelected: (String?) -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    refreshDate: () -> Unit,
    nextWeek: () -> Unit,
    previousWeek: () -> Unit,
) {

    var openBottomSheet by remember { mutableStateOf(false) }
    var onOpenCalendar by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(32.dp))

    val textName = nameSelected?.let {
        "$FILTERED_PEOPLE $it"
    } ?: FILTER_PEOPLE

    if (openBottomSheet) {
        BottomSheetClickable(
            items = names,
            onItemClick = { item ->
                onNameSelected(item)
                openBottomSheet = false
            },
            onDismiss = {
                openBottomSheet = false
            },
            sheetState = false
        )
    }

    if (onOpenCalendar) {
        CalendarDatePicker(
            onDateSelected,
            onDismiss = {
                onOpenCalendar = false
            },
        )
    }

    Row {
        Button(onClick = {
            openBottomSheet = true
        }) {
            Text(text = textName)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = {
            onOpenCalendar = true
        }) {
            Text(text = FIND_DATE)
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
    Row {
        Button(onClick = {
            refreshDate()
        }) {
            Text(text = CURRENT_WEEK)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = {
            previousWeek()
        }) {
            Text(text = PREVIOUS)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(onClick = {
            nextWeek()
        }) {
            Text(text = NEXT)
        }
    }
    Spacer(modifier = Modifier.height(32.dp))
}

fun getDistinctNames(weekData: List<WeekData>): List<String> {
    return weekData
        .flatMap {
            listOf(
                People(
                    name = TEXT_ALL_FILTER,
                    mode = "*"
                )
            ) + it.monday + it.tuesday + it.wednesday + it.thursday + it.friday
        }
        .map { it.name }
        .distinct()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetClickable(
    items: List<String>,
    onItemClick: (String) -> Unit,
    onDismiss: () -> Unit,
    sheetState: Boolean
) {
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(sheetState),
        content = {
            Column {
                items.forEach { item ->
                    Text(
                        text = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onItemClick(item)
                            }
                            .padding(16.dp)
                    )
                    Divider()
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = LocalDensity.current.run { 56.dp.toPx() }.dp),
        shape = MaterialTheme.shapes.large,
        scrimColor = Color.Black.copy(alpha = 0.5f),
        onDismissRequest = {
            onDismiss()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarDatePicker(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit
) {

    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    }

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                selectedDate?.let(onDateSelected)
                onDismiss()
            }

            ) {
                Text(text = TEXT_OK)
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = TEXT_CANCEL)
            }
        },
        content = {
            DatePicker(state = datePickerState)
        }
    )
}

private fun convertMillisToDate(millis: Long): LocalDate {
    val instant = Instant.ofEpochMilli(millis)
    return instant.atZone(ZoneId.of("UTC")).toLocalDate()
}

object Defines {
    const val CURRENT_WEEK = "Semana corrente"
    const val FIND_DATE = "Filtrar data"
    const val TEXT_ALL_FILTER = "Todos"
    const val TEXT_OK = "OK"
    const val TEXT_CANCEL = "Cancelar"
    const val SCALE_WEEK = "Escala Semanal :"
    const val DATE_FORMAT = "dd/MM/yyyy"
    const val MONDAY = "Segunda-Feira"
    const val TUESDAY = "Terça-Feira"
    const val WEDNESDAY = "Quarta-Feira"
    const val THURSDAY = "Quinta-Feira"
    const val FRIDAY = "Sexta-Feira"
    const val LOADING = "Carregando..."
    const val FILTER_PEOPLE = "Filtrar pessoas"
    const val FILTERED_PEOPLE = "Filtro pessoas :"
    const val NEXT = ">>"
    const val PREVIOUS = "<<"
}