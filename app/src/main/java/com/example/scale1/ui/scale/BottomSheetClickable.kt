package com.example.scale1.ui.scale

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

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