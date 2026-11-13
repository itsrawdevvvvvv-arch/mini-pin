package com.replica.pinlite

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.replica.pinlite.data.Board
import com.replica.pinlite.data.Pin
import com.replica.pinlite.data.PinRepository
import com.replica.pinlite.ui.components.PillNavigation
import com.replica.pinlite.ui.screens.HomeScreen
import com.replica.pinlite.ui.screens.MoodBoardScreen
import com.replica.pinlite.ui.screens.SearchScreen
import com.replica.pinlite.ui.theme.DarkBg
import com.replica.pinlite.ui.theme.DarkSurface
import com.replica.pinlite.ui.theme.NeonLime
import com.replica.pinlite.ui.theme.PinLiteTheme
import com.replica.pinlite.ui.theme.TextDark
import com.replica.pinlite.ui.theme.TextMuted
import com.replica.pinlite.ui.theme.TextWhite
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PinLiteTheme {
                var selectedTab by remember { mutableStateOf(0) }
                val pins by remember { mutableStateOf(PinRepository.samplePins) }
                var boards by remember { mutableStateOf(PinRepository.boards) }

                var pinToSave by remember { mutableStateOf<Pin?>(null) }
                var activePinDetail by remember { mutableStateOf<Pin?>(null) }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBg)
                ) {
                    when (selectedTab) {
                        0 -> HomeScreen(
                            pins = pins,
                            onSavePin = { pinToSave = it },
                            onPinClick = { activePinDetail = it }
                        )
                        1 -> SearchScreen(
                            allPins = pins,
                            onSavePin = { pinToSave = it },
                            onPinClick = { activePinDetail = it }
                        )
                        2 -> MoodBoardScreen(
                            boards = boards,
                            allPins = pins,
                            onCreateBoard = { name, isMood ->
                                val newBoard = Board(
                                    id = UUID.randomUUID().toString(),
                                    name = name,
                                    isMoodBoard = isMood
                                )
                                boards = (boards + newBoard).toMutableList()
                                Toast.makeText(this@MainActivity, "Created board '$name'", Toast.LENGTH_SHORT).show()
                            },
                            onPinClick = { activePinDetail = it }
                        )
                    }

                    // Floating Pill Navigation Dock
                    PillNavigation(
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it },
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )

                    // Save Pin Modal
                    pinToSave?.let { pin ->
                        AlertDialog(
                            onDismissRequest = { pinToSave = null },
                            containerColor = DarkSurface,
                            title = {
                                Text(
                                    text = "Save to Board",
                                    color = TextWhite,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            text = {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Text(
                                        text = "Select a board for '${pin.title}':",
                                        color = TextMuted,
                                        fontSize = 13.sp
                                    )
                                    boards.forEach { b ->
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(DarkBg)
                                                .clickable {
                                                    val updatedBoard = b.copy(pinIds = (b.pinIds + pin.id).distinct())
                                                    boards = boards.map { if (it.id == b.id) updatedBoard else it }.toMutableList()
                                                    Toast.makeText(this@MainActivity, "Saved to ${b.name}", Toast.LENGTH_SHORT).show()
                                                    pinToSave = null
                                                }
                                                .padding(horizontal = 14.dp, vertical = 12.dp)
                                        ) {
                                            Text(
                                                text = b.name,
                                                color = TextWhite,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            },
                            confirmButton = {},
                            dismissButton = {
                                TextButton(onClick = { pinToSave = null }) {
                                    Text("Cancel", color = TextMuted)
                                }
                            }
                        )
                    }

                    // High-Res Pin Detail View Modal
                    activePinDetail?.let { pin ->
                        Dialog(onDismissRequest = { activePinDetail = null }) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.95f)
                                    .clip(RoundedCornerShape(28.dp))
                                    .background(DarkSurface)
                                    .padding(16.dp)
                            ) {
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(pin.aspectRatio)
                                            .clip(RoundedCornerShape(20.dp))
                                    ) {
                                        AsyncImage(
                                            model = pin.imageUrl,
                                            contentDescription = pin.title,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = pin.title,
                                        color = TextWhite,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Curated by @${pin.author} • ${pin.category}",
                                        color = TextMuted,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(bottom = 14.dp)
                                    )
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Button(
                                            onClick = {
                                                pinToSave = pin
                                                activePinDetail = null
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = NeonLime,
                                                contentColor = TextDark
                                            ),
                                            shape = RoundedCornerShape(9999.dp),
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text("Save to Board", fontWeight = FontWeight.Bold)
                                        }

                                        Button(
                                            onClick = { activePinDetail = null },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = DarkBg,
                                                contentColor = TextWhite
                                            ),
                                            shape = RoundedCornerShape(9999.dp)
                                        ) {
                                            Text("Close")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
