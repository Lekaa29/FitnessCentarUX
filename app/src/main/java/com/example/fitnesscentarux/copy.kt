package com.example.fitnesscentarux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fitnesscentarux.ui.theme.FitnessCentarUXTheme

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.zIndex
/*

class copy : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessCentarUXTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var isMapMode by remember { mutableStateOf(false) }

    if (isMapMode) {
        MapScreen(onBackClick = { isMapMode = false })
    } else {
        Background(onMapClick = { isMapMode = true })
    }
}

@Composable
fun MapScreen(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Top bar for map screen
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.TopCenter)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color(0xFF040404), Color(0xFF000000), Color(0xFFF5F22E)),
                        startY = 70.0f,
                        endY = 0.0f
                    )
                )
                .zIndex(1f)
        ) {
            Text(
                text = "INSPECTOR VINKOVCI",
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.Center),
                color = Color.White
            )

            // Top right buttons for map screen
            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search button
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            Color.White.copy(alpha = 0.2f),
                            CircleShape
                        )
                        .clickable { /* Search functionality */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // Map button (active state)
                Box(
                    modifier = Modifier
                        .background(
                            Color(0xFF87CEEB).copy(alpha = 0.8f),
                            RoundedCornerShape(18.dp)
                        )
                        .clickable { onBackClick() }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = "MAP",
                        color = Color.White,
                        fontWeight = FontWeight.Medium
                    )
                }

                // Profile image placeholder
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            Color.Gray,
                            CircleShape
                        )
                        .clickable { /* Profile functionality */ },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "CR",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Map content placeholder
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "MAP VIEW",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Map functionality will be implemented here",
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun Background(onMapClick: () -> Unit, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    // Define the scroll range for the animation (10% of screen height)
    val animationScrollRangePx = with(density) {
        (configuration.screenHeightDp.dp * 0.20f).toPx()
    }

    // Track the top text's Y position
    var topTextOffsetY by remember { mutableStateOf(0f) }

    // Calculate animation progress (0f to 1f) based on scroll position
    val scrollProgress = remember(scrollState.value, topTextOffsetY) {
        val scrollOffset = scrollState.value - topTextOffsetY
        (scrollOffset / animationScrollRangePx).coerceIn(0f, 1f)
    }

    // Calculate top bar offset for smooth sliding
    val topBarHeightPx = with(density) { 56.dp.toPx() } // Top bar height
    val topBarOffsetY = (-topBarHeightPx * (1f - scrollProgress)).coerceAtLeast(-topBarHeightPx)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top bar with scroll-driven slide-in animation
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .align(Alignment.TopCenter)
                .graphicsLayer(alpha = scrollProgress)
                .zIndex(1f)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            listOf(Color(0xFF040404), Color(0xFF000000), Color(0xFFF5F22E)),
                            startY = 70.0f,
                            endY = 0.0f
                        )
                    )
            ) {
                Text(
                    text = "INSPECTOR VINKOVCI",
                    modifier = Modifier
                        .padding(0.dp)
                        .align(Alignment.Center),
                    color = Color.White
                )

                // Top right buttons
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Search button
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                CircleShape
                            )
                            .clickable { /* Search functionality */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    // Map button
                    Box(
                        modifier = Modifier
                            .background(
                                Color.White.copy(alpha = 0.2f),
                                RoundedCornerShape(18.dp)
                            )
                            .clickable { onMapClick() }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "MAP",
                            color = Color.White,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Profile image placeholder
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(
                                Color.Gray,
                                CircleShape
                            )
                            .clickable { /* Profile functionality */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "CR",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(
                    brush = Brush.linearGradient(
                        listOf(
                            Color(0xFFB4B221),
                            Color(0xFF0F0F00),
                            Color(0xFF0F0F00),
                            Color(0xFF060606),
                            Color(0xFF000000),
                            Color(0xFFB4B221)
                        ),
                    )
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top text whose offset we track
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .onGloballyPositioned {
                        topTextOffsetY = it.positionInParent().y
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "INSPECTOR VINKOVCI")
            }

            Spacer(modifier = Modifier.height(200.dp))
            Text("INSPECTOR VINKOVCI", color = Color.White)
            Spacer(modifier = Modifier.height(50.dp))
            Text("INSPECTOR VINKOVCI", color = Color.White)
            Spacer(modifier = Modifier.height(50.dp))
            Text("INSPECTOR VINKOVCI", color = Color.White)
            Spacer(modifier = Modifier.height(500.dp))
            Text("INSPECTOR VINKOVCI", color = Color.White)

            Column {
                Text("LEADERBOARD", color = Color.White, modifier = Modifier.padding(vertical = 10.dp))
                Text("LEKAA29 1000PTS", color = Color.White, modifier = Modifier.padding(vertical = 20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BackgroundPreview() {
    FitnessCentarUXTheme {
        MainScreen()
    }
}

*/