package com.replica.pinlite.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.replica.pinlite.data.Pin
import com.replica.pinlite.ui.components.MasonryGrid
import com.replica.pinlite.ui.theme.*

@Composable
fun HomeScreen(
    pins: List<Pin>,
    onSavePin: (Pin) -> Unit,
    onPinClick: (Pin) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Reference Kicktrack Top Floating Capsule Banner
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(9999.dp))
                .background(NeonLime)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(DarkBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Alert",
                    tint = NeonLime,
                    modifier = Modifier.size(16.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Curated visual inspiration feed",
                color = TextDark,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Explore",
                tint = TextDark,
                modifier = Modifier.size(18.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Hero Typography
        Text(
            text = "discover",
            color = TextWhite,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = (-0.5).sp
        )
        Text(
            text = "High-definition visual feed • 0 Ads • Clean stream",
            color = TextMuted,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Masonry Feed
        MasonryGrid(
            pins = pins,
            onSaveClick = onSavePin,
            onPinClick = onPinClick
        )

        Spacer(modifier = Modifier.height(100.dp)) // padding for bottom pill nav
    }
}
