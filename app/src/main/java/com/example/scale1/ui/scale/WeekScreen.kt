package com.example.scale1.ui.scale

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scale1.domain.models.People
import com.example.scale1.domain.models.WeekData
import com.example.scale1.extensions.getDistinctNames
import com.example.scale1.ui.scale.Defines.CURRENT_WEEK
import com.example.scale1.ui.scale.Defines.DATE_FORMAT
import com.example.scale1.ui.scale.Defines.FILTERED_PEOPLE
import com.example.scale1.ui.scale.Defines.FILTER_PEOPLE
import com.example.scale1.ui.scale.Defines.FIND_DATE
import com.example.scale1.ui.scale.Defines.FRIDAY
import com.example.scale1.ui.scale.Defines.MONDAY
import com.example.scale1.ui.scale.Defines.NEXT
import com.example.scale1.ui.scale.Defines.PREVIOUS
import com.example.scale1.ui.scale.Defines.SCALE_WEEK
import com.example.scale1.ui.scale.Defines.TEXT_ALL_FILTER
import com.example.scale1.ui.scale.Defines.THURSDAY
import com.example.scale1.ui.scale.Defines.TUESDAY
import com.example.scale1.ui.scale.Defines.WEDNESDAY
import com.example.scale1.ui.theme.DarkBlue
import com.example.scale1.ui.theme.LightBlue
import com.example.scale1.ui.theme.LightGray
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeekScreen(viewModel: WeekViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    val dateFormatted = uiState.localDate?.format(formatter)

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color.LightGray,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {

                        Text("$SCALE_WEEK $dateFormatted")
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxSize()
            ) {
                with(uiState) {

                    if (loading) {
                        ShimmerLoadingExample()
                    } else if (errorMessage != null) {
                        Text(
                            text = "Error: $errorMessage",
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        )
                    } else {
                        weekData?.let {
                            FilterButtons(
                                names = it.getDistinctNames(),
                                nameSelected = selectedName,
                                onNameSelected = viewModel::onNameSelected,
                                onDateSelected = viewModel::onDateSelected,
                                refreshDate = viewModel::refreshActualWeekDate,
                                nextWeek = viewModel::nextWeek,
                                previousWeek = viewModel::previousWeek
                            )

                            Spacer(modifier = Modifier.height(16.dp))
                            TableWeek(it, selectedName)
                        }
                    }
                }
            }
        })
}

@Composable
fun TableWeek(
    weekData: List<WeekData>, selectedName: String?
) {
    LazyColumn {
        items(weekData) { weekData ->
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
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
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

        data.forEachIndexed { index, person ->
            PersonRowWeek(
                index = index,
                person = person
            )
        }
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


