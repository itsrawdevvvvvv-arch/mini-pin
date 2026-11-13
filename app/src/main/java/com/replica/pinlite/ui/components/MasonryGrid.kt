package com.replica.pinlite.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.replica.pinlite.data.Pin

@Composable
fun MasonryGrid(
    pins: List<Pin>,
    onSaveClick: (Pin) -> Unit,
    onPinClick: (Pin) -> Unit,
    modifier: Modifier = Modifier
) {
    val col1Pins = pins.filterIndexed { index, _ -> index % 2 == 0 }
    val col2Pins = pins.filterIndexed { index, _ -> index % 2 != 0 }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            col1Pins.forEach { pin ->
                PinCard(
                    pin = pin,
                    onSaveClick = { onSaveClick(pin) },
                    onPinClick = { onPinClick(pin) }
                )
            }
        }

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            col2Pins.forEach { pin ->
                PinCard(
                    pin = pin,
                    onSaveClick = { onSaveClick(pin) },
                    onPinClick = { onPinClick(pin) }
                )
            }
        }
    }
}
