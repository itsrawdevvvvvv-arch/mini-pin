package com.replica.pinlite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.replica.pinlite.ui.theme.*

@Composable
fun PillNavigation(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 20.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .shadow(elevation = 16.dp, shape = RoundedCornerShape(9999.dp), spotColor = NeonLime)
                .clip(RoundedCornerShape(9999.dp))
                .background(DarkSurfaceElevated)
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            val tabs = listOf(
                Icons.Default.Home to "Home",
                Icons.Default.Search to "Search",
                Icons.Default.Dashboard to "Boards"
            )

            tabs.forEachIndexed { index, (icon, desc) ->
                val isSelected = selectedTab == index
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(CircleShape)
                        .background(if (isSelected) NeonLime else DarkSurfaceElevated)
                        .clickable { onTabSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = desc,
                        tint = if (isSelected) TextDark else TextMuted,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
