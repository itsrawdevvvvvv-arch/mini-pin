package com.replica.pinlite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.replica.pinlite.data.Board
import com.replica.pinlite.data.Pin
import com.replica.pinlite.ui.components.MasonryGrid
import com.replica.pinlite.ui.theme.*

@Composable
fun MoodBoardScreen(
    boards: List<Board>,
    allPins: List<Pin>,
    onCreateBoard: (String, Boolean) -> Unit,
    onPinClick: (Pin) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedBoardId by remember { mutableStateOf(boards.firstOrNull()?.id ?: "") }
    var showNewBoardDialog by remember { mutableStateOf(false) }
    var newBoardName by remember { mutableStateOf("") }
    var isNewBoardMoodBoard by remember { mutableStateOf(true) }

    val activeBoard = boards.find { it.id == selectedBoardId } ?: boards.firstOrNull()
    val boardPins = allPins.filter { pin -> activeBoard?.pinIds?.contains(pin.id) == true }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "mood boards",
                    color = TextWhite,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = (-0.5).sp
                )
                Text(
                    text = "Curate personal collections & collage layouts",
                    color = TextMuted,
                    fontSize = 12.sp
                )
            }

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(NeonLime)
                    .clickable { showNewBoardDialog = true },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New Board",
                    tint = TextDark,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Kicktrack Bento Card Pair (White Metric Card + Dark Moodboard Card)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // White Card (Total Saved)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(WhiteSurface)
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Collections,
                            contentDescription = null,
                            tint = TextDark,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Saved Items",
                            color = TextDark.copy(alpha = 0.7f),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${allPins.size}",
                        color = TextDark,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }

            // Dark Card with Lime Progress (Mood Boards Count)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(DarkSurface)
                    .padding(16.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            tint = NeonLime,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Collage Boards",
                            color = TextMuted,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "${boards.count { it.isMoodBoard }} active",
                        color = TextWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Board Selector Tabs
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            boards.forEach { b ->
                val isSelected = b.id == selectedBoardId
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(9999.dp))
                        .background(if (isSelected) NeonLime else DarkSurface)
                        .clickable { selectedBoardId = b.id }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = b.name,
                        color = if (isSelected) TextDark else TextWhite,
                        fontSize = 13.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Selected Board Content (Moodboard Collage or Grid)
        if (boardPins.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(DarkSurface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No pins saved in this board yet.\nTap the bookmark on any pin to add it.",
                    color = TextMuted,
                    fontSize = 13.sp,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            MasonryGrid(
                pins = boardPins,
                onSaveClick = {},
                onPinClick = onPinClick
            )
        }

        Spacer(modifier = Modifier.height(100.dp))
    }

    // Quick Board Creation Modal
    if (showNewBoardDialog) {
        AlertDialog(
            onDismissRequest = { showNewBoardDialog = false },
            containerColor = DarkSurface,
            title = {
                Text(text = "Create New Board", color = TextWhite, fontWeight = FontWeight.Bold)
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = newBoardName,
                        onValueChange = { newBoardName = it },
                        placeholder = { Text("Board Name...", color = TextMuted) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = TextWhite,
                            unfocusedTextColor = TextWhite,
                            cursorColor = NeonLime,
                            focusedBorderColor = NeonLime,
                            unfocusedBorderColor = BorderInput
                        ),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isNewBoardMoodBoard,
                            onCheckedChange = { isNewBoardMoodBoard = it },
                            colors = CheckboxDefaults.colors(checkedColor = NeonLime, checkmarkColor = TextDark)
                        )
                        Text(text = "Collage / Mood Board Layout", color = TextWhite, fontSize = 13.sp)
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newBoardName.isNotBlank()) {
                            onCreateBoard(newBoardName.trim(), isNewBoardMoodBoard)
                            newBoardName = ""
                            showNewBoardDialog = false
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = NeonLime, contentColor = TextDark)
                ) {
                    Text("Create", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showNewBoardDialog = false }) {
                    Text("Cancel", color = TextMuted)
                }
            }
        )
    }
}
