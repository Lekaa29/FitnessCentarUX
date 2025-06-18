package com.example.fitnesscentarux

import android.R.attr.start
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.fitnesscentarux.ui.theme.FitnessCentarUXTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnessCentarUXTheme {
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

    Background(

    )


}

@Composable
fun ProfileButton() {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                Color(0xFFFF0000),
                CircleShape
            )
            .clickable { /* Profile */ },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "LL",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}



@Composable
fun Background(
               modifier: Modifier = Modifier,
          ) {
    val scrollState = rememberScrollState()
    val scrollAnimationState = rememberScrollAnimationState()

    var topTextOffsetY by remember { mutableStateOf(0f) }
    val scrollProgress = calculateScrollProgress(scrollState, topTextOffsetY, scrollAnimationState)
    var showNewsOverlay by remember { mutableStateOf(false) }
    var showLeaderboardOverlay by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundTopBar(
            scrollProgress = scrollProgress,
            showNewsOverlay
        )
        Box(){
            BackgroundScrollableContent(
                scrollState = scrollState,
                scrollAnimationState = scrollAnimationState,
                onTopTextPositioned = { topTextOffsetY = it },
                onShowNewsOverlayChange = { showNewsOverlay = it},
                showNewsOverlay,
                onLeaderboardOverlayChange = { showLeaderboardOverlay = it},
                showLeaderboardOverlay
            )
        }

    }
}

@Composable
fun rememberScrollAnimationState(): ScrollAnimationState {
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current

    return remember {
        ScrollAnimationState(
            animationScrollRangePx = with(density) {
                (configuration.screenHeightDp.dp * 0.20f).toPx()
            }
        )
    }
}

data class ScrollAnimationState(
    val animationScrollRangePx: Float
)

@Composable
fun calculateScrollProgress(
    scrollState: androidx.compose.foundation.ScrollState,
    topTextOffsetY: Float,
    scrollAnimationState: ScrollAnimationState
): Float {
    return remember(scrollState.value, topTextOffsetY) {
        val scrollOffset = scrollState.value - topTextOffsetY
        (scrollOffset / scrollAnimationState.animationScrollRangePx).coerceIn(0f, 0.9f)
    }
}

@Composable
fun BackgroundTopBar(
    scrollProgress: Float,
    showNewsOverlay: Boolean
    ) {
    val scrollColor = remember(scrollProgress) {
        Color(0xA820201F).copy(alpha = scrollProgress)
    }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .graphicsLayer(alpha = 1.0f)
            .zIndex(1f)
    ) {
        if(showNewsOverlay==false){
            TopBarBackground(scrollColor = scrollColor)
            TopBarContent(

            )

        }

    }
}

@Composable
fun TopBarBackground(scrollColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer(alpha = 1.0f)
            .drawWithContent {
                drawContent()
                drawRect(scrollColor, blendMode = BlendMode.SrcOver)
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        scrollColor,
                        scrollColor.copy(alpha = 0f)
                    )
                )
            )
            .blur(24.dp)
    )
}

@Composable
fun TopBarContent(
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 8.dp, top = 2.dp)
            .wrapContentHeight(Alignment.CenterVertically),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {


        ProfileButton()
    }
}



@Composable
fun BackgroundScrollableContent(
    scrollState: androidx.compose.foundation.ScrollState,
    scrollAnimationState: ScrollAnimationState,
    onTopTextPositioned: (Float) -> Unit,
    onShowNewsOverlayChange: (Boolean) -> Unit,
    showNewsOverlay:Boolean,
    onLeaderboardOverlayChange: (Boolean) -> Unit,
    showLeaderboardOverlay:Boolean
) {
    val animatedColor = rememberAnimatedGradientColor()

    val newsItems = listOf(
        mapOf("title" to "Ne radimo 17.6", "imageUrl" to "https://scontent.fzag4-1.fna.fbcdn.net/v/t1.6435-9/96379603_159721362199244_1576151752367931392_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=833d8c&_nc_ohc=sQAuHjV-qSIQ7kNvwE9QuQA&_nc_oc=Adk_8r6iSXlg34M4EWUX2HSGwZ6DoW_wHCggcGy_3rCFvu4aG2mRDaxCanvbvSqzoLQ&_nc_zt=23&_nc_ht=scontent.fzag4-1.fna&_nc_gid=tJ4_peV25w8GbDfBi7uThg&oh=00_AfORP5uxJN24xOtS9UQFGyT_yId7q34aFsPhN8DEXLlwEA&oe=687959D0"),
        mapOf("title" to "Pravila", "imageUrl" to "https://scontent.fzag4-1.fna.fbcdn.net/v/t1.6435-9/96379603_159721362199244_1576151752367931392_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=833d8c&_nc_ohc=sQAuHjV-qSIQ7kNvwE9QuQA&_nc_oc=Adk_8r6iSXlg34M4EWUX2HSGwZ6DoW_wHCggcGy_3rCFvu4aG2mRDaxCanvbvSqzoLQ&_nc_zt=23&_nc_ht=scontent.fzag4-1.fna&_nc_gid=tJ4_peV25w8GbDfBi7uThg&oh=00_AfORP5uxJN24xOtS9UQFGyT_yId7q34aFsPhN8DEXLlwEA&oe=687959D0"),
        mapOf("title" to "Nova smith sprava", "imageUrl" to "https://scontent.fzag4-1.fna.fbcdn.net/v/t1.6435-9/96379603_159721362199244_1576151752367931392_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=833d8c&_nc_ohc=sQAuHjV-qSIQ7kNvwE9QuQA&_nc_oc=Adk_8r6iSXlg34M4EWUX2HSGwZ6DoW_wHCggcGy_3rCFvu4aG2mRDaxCanvbvSqzoLQ&_nc_zt=23&_nc_ht=scontent.fzag4-1.fna&_nc_gid=tJ4_peV25w8GbDfBi7uThg&oh=00_AfORP5uxJN24xOtS9UQFGyT_yId7q34aFsPhN8DEXLlwEA&oe=687959D0")
    )

    val sampleUsers = listOf(
        mapOf("username" to "Player1", "points" to "1500"),
        mapOf("username" to "Player2", "points" to "1200"),
        mapOf("username" to "Player3", "points" to "1100"),
        mapOf("username" to "Player4", "points" to "950"),
        mapOf("username" to "Player5", "points" to "800"),
        mapOf("username" to "Player6", "points" to "750"),
        mapOf("username" to "Player7", "points" to "700"),
        mapOf("username" to "Player8", "points" to "650"),
        mapOf("username" to "Player9", "points" to "600"),
        mapOf("username" to "Player10", "points" to "550")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            animatedColor,
                            Color(0xFF000000),
                            Color(0xFF000000),
                            Color(0xFF000000),
                            Color(0xFF000000),
                            Color(0xFF41400C),
                            animatedColor
                        )
                    )
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(32.dp))
            TopTextSection(onTopTextPositioned = onTopTextPositioned)
            TopActionsContainer()
            Spacer(modifier = Modifier.padding(12.dp))
            Line(Color.White)
            Spacer(modifier = Modifier.padding(4.dp))


            // Pass the callback to show overlay
            SimpleLeaderboard(users = sampleUsers,
                onViewTableClick = { onLeaderboardOverlayChange(true) },
                detail = false)
            NewsSection(
                newsList = newsItems,
                onShowAllClick = { onShowNewsOverlayChange(true) }
            )

            Spacer(modifier = Modifier.padding(4.dp))

            ContentSections()
            LeaderboardSection()
        }

        // Overlay
        AnimatedVisibility(
            visible = showNewsOverlay,
            enter = slideInHorizontally(
                initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ),
            exit = slideOutHorizontally(
                targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(
                    durationMillis = 250,
                    easing = FastOutSlowInEasing
                )
            )
        ) {
            NewsDetailOverlay(
                newsItems = newsItems,
                onBackClick = { onShowNewsOverlayChange(false) }
            )
        }

        AnimatedVisibility(
            visible = showLeaderboardOverlay,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = 250,
                    easing = FastOutSlowInEasing
                )
            )
        ) {
            LeaderboardOverlay(
                rankedUser = sampleUsers,
                onBackClick = { onLeaderboardOverlayChange(false) }
            )
        }





    }
}


@Composable
fun SimpleLeaderboard(users: List<Map<String, String>>,
                      onViewTableClick: () -> Unit,
                      detail: Boolean) {
    val sortedUsers = users.sortedByDescending {
        it["points"]?.toIntOrNull() ?: 0
    }.take(if (detail) 50 else 5)


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Title
        if(detail==false){
            Text(
                text = "Leaderboard",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Top 5 users
        sortedUsers.forEachIndexed { index, user ->
            Line(Color(0xAE222222))
            LeaderboardItem(
                rank = index + 1,
                username = user["username"] ?: "Unknown",
                points = user["points"] ?: "0",
                detail = detail
            )

        }
        Line(Color(0x7E414141))
        if(!detail){
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(8.dp)
            ){
                transparentButton(text = "View table", onViewTableClick)
            }
        }

    }
}

@Composable
fun LeaderboardItem(rank: Int, username: String, points: String, detail: Boolean) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF000000)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Rank with special styling for top 3

                Box(
                    modifier = Modifier
                        .size(28.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = rank.toString(),
                        color =
                        if(detail){
                        when (rank) {
                            1 -> Color(0xFFFFD700) // Gold
                            2 -> Color(0xFFC0C0C0) // Silver/Gray
                            3 -> Color(0xFFCD7F32) // Brown/Bronze
                            else -> Color.White
                        }}else{
                           Color(0xFFFFFFFF)
                              },                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column {
                    if(detail){
                        Text(
                            text = username,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        // Add emoji for top 3
                        if (rank <= 3) {
                            Text(
                                text = when (rank) {
                                    1 -> "👑"
                                    2 -> "🥈"
                                    3 -> "🥉"
                                    else -> ""
                                },
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                    }
                    else {
                        Text(
                            text = username,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Text(
                text = "$points pts",
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// Sample usage
@Preview
@Composable
fun SimpleLeaderboardPreview() {
    val sampleUsers = listOf(
        mapOf("username" to "GameMaster2024", "points" to "1250"),
        mapOf("username" to "ProPlayer99", "points" to "1180"),
        mapOf("username" to "SkillWizard", "points" to "1050"),
        mapOf("username" to "EliteGamer", "points" to "980"),
        mapOf("username" to "ChampionX", "points" to "920"),
        mapOf("username" to "Player123", "points" to "850"),
        mapOf("username" to "NinjaCoder", "points" to "780")
    )

    SimpleLeaderboard(users = sampleUsers, {}, detail = true)
}

@Composable
fun Line(color: Color){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(1.dp)
        .background(color)
        .padding(top = 8.dp, bottom = 8.dp))
            {

    }
}



@Composable
fun rememberAnimatedGradientColor(): Color {
    val infiniteTransition = rememberInfiniteTransition()

    return infiniteTransition.animateColor(
        initialValue = Color(0xFF807D24),
        targetValue = Color(0xFF5B5F21),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 3000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    ).value
}

@Composable
fun TopTextSection(onTopTextPositioned: (Float) -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .onGloballyPositioned {
                onTopTextPositioned(it.positionInParent().y)
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Live attendance", color = Color(0xF2BDBDBD), fontSize=12.sp, fontWeight=FontWeight.Light)
            Text(text = "14", color = Color.White, fontSize=84.sp)

            transparentButton(text = "View activity", {})

        }
    }
}

@Composable
fun transparentButton(text: String, onClick: () -> Unit){
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .height(44.dp) ,// smaller height
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White.copy(alpha = 0.1f), // translucent background
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp), // compact padding
        shape = RoundedCornerShape(18.dp) // optional: small round corners
    ) {
        Text(
            text = text,
            color = Color.White.copy(alpha = 0.9f), // slightly transparent text
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun TopActionsContainer() {

    Box {
        Row(
            modifier = Modifier.fillMaxWidth(), // Ensures the row spans the width
            horizontalArrangement = Arrangement.SpaceEvenly, // Even spacing
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "4 *", fontSize = 72.sp, color = Color(0xFFFFFFFF))
                Text(text = "soon leaving", fontSize = 12.sp, color = Color(0xFFFFFFFF))
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "[X]", fontSize = 72.sp, color = Color(0xFFFFFFFF))
                Text(text = "scan QR", fontSize = 12.sp, color = Color(0xFFFFFFFF))
            }
        }
    }

}




@Composable
fun ContentSections() {
    Spacer(modifier = Modifier.height(200.dp))
    Text("INSPECTOR VINKOVCI", color = Color.White)
    Spacer(modifier = Modifier.height(50.dp))
    Text("INSPECTOR VINKOVCI", color = Color.White)
    Spacer(modifier = Modifier.height(50.dp))
    Text("INSPECTOR VINKOVCI", color = Color.White)
    Spacer(modifier = Modifier.height(500.dp))
    Text("INSPECTOR VINKOVCI", color = Color.White)
}

@Composable
fun LeaderboardSection() {
    Column {
        Text("LEADERBOARD", color = Color.White, modifier = Modifier.padding(vertical = 10.dp))
        Text("LEKAA29 1000PTS", color = Color.White, modifier = Modifier.padding(vertical = 20.dp))
    }
}

data class NewsItem(val title: String, val imageUrl: String)


@Composable
fun NewsSection(newsList: List<Map<String, String>>,
                onShowAllClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        // Header row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "News & Rules",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Row(
                modifier = Modifier.clickable { onShowAllClick() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "View All",
                    color = Color.White
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            items(newsList.take(5)) { item ->
                val title = item["title"] ?: ""
                val imageUrl = item["imageUrl"] ?: ""

                Box(
                    modifier = Modifier
                        .width(200.dp)
                        .height(120.dp)
                        .background(Color.Gray)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .background(Color(0xCC000000))
                            .padding(4.dp)
                    ) {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun LeaderboardOverlay (
    rankedUser: List<Map<String, String>>,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // Create a blurred background effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    // Draw a stronger semi-transparent overlay
                    drawRect(
                        color = Color(0xCC000000), // 80% opacity black
                        size = size
                    )
                }
        )
        // Content with glassmorphism effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color=Color(0xF0000000))
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Top Bar with Back Button and Title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Text(
                        text = "Leaderboard",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.size(40.dp))
                }

                SimpleLeaderboard(users = rankedUser, {}, detail = true)

            }
        }

    }
}

@Composable
fun NewsDetailOverlay(
    newsItems: List<Map<String, String>>,
    onBackClick: () -> Unit
) {
    var selectedArticle by remember { mutableStateOf<Map<String, String>?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        // Create a blurred background effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawBehind {
                    // Draw a stronger semi-transparent overlay
                    drawRect(
                        color = Color(0xCC000000), // 80% opacity black
                        size = size
                    )
                }
        )

        // Content with glassmorphism effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0x73C2C2C2),
                            Color(0x73818181)
                        )
                    )
                )
        ) {
            if (selectedArticle != null) {
                SingleArticleView(
                    article = selectedArticle!!,
                    onBackClick = { selectedArticle = null }
                )
            } else {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Top Bar with Back Button and Title
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Text(
                            text = "News & Articles",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.size(48.dp))
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(vertical = 16.dp)
                    ) {
                        items(newsItems) { newsItem ->
                            NewsItemRow(
                                title = newsItem["title"] ?: "",
                                imageUrl = newsItem["imageUrl"] ?: "",
                                onClick = { selectedArticle = newsItem }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NewsItemRow(
    title: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(136.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.85f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Image on the left (half width)
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )


            // Text on the right (half width)
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "16.08.2025",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
fun SingleArticleView(
    article: Map<String, String>,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Top Bar with Back Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to articles",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // Scrollable content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            item {
                // Article Image
                AsyncImage(
                    model = article["imageUrl"] ?: "",
                    contentDescription = article["title"],
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Article Title
                Text(
                    text = article["title"] ?: "",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Article Content (placeholder)
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.\n\nDuis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\n\nSed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
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

@Composable
fun NewsDetailOverlayPreview() {
    FitnessCentarUXTheme {
        val newsItems = listOf(
            mapOf("title" to "Ne radimo 17.6", "imageUrl" to "https://via.placeholder.com/200x120"),
            mapOf("title" to "Pravila", "imageUrl" to "https://via.placeholder.com/200x120"),
            mapOf("title" to "Nova smith sprava", "imageUrl" to "https://via.placeholder.com/200x120")
        )
        Box(){
            Box(
                modifier = Modifier.background(color=Color.White)
            ){
                Text(text = "hahahaha", fontSize=100.sp, color=Color.White)
            }
            Box(){
                NewsDetailOverlay(newsItems, {})
            }
        }
    }
}





