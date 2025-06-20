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
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.fitnesscentarux.ui.theme.FitnessCentarUXTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.random.Random

/*
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
fun Background(
               modifier: Modifier = Modifier,
          ) {
    val scrollState = rememberScrollState()

    var topTextOffsetY by remember { mutableStateOf(0f) }
    var showNewsOverlay by remember { mutableStateOf(false) }
    var showLeaderboardOverlay by remember { mutableStateOf(false) }
    var showCoachesOverlay by remember { mutableStateOf(false) }
    var showGraphOverlay by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        Box(){
            BackgroundScrollableContent(
                scrollState = scrollState,
                onTopTextPositioned = { topTextOffsetY = it },
                onShowNewsOverlayChange = { showNewsOverlay = it},
                showNewsOverlay,
                onLeaderboardOverlayChange = { showLeaderboardOverlay = it},
                showLeaderboardOverlay,
                onCoachesOverlayChange = { showCoachesOverlay = it},
                showCoachesOverlay,
                showGraphOverlay = showGraphOverlay,
                onshowGraphOverlayChange = { showGraphOverlay = it}
            )
        }

    }
}



@Composable
fun BackgroundScrollableContent(
    scrollState: androidx.compose.foundation.ScrollState,
    onTopTextPositioned: (Float) -> Unit,
    onShowNewsOverlayChange: (Boolean) -> Unit,
    showNewsOverlay:Boolean,
    onLeaderboardOverlayChange: (Boolean) -> Unit,
    showLeaderboardOverlay:Boolean,
    onCoachesOverlayChange: (Boolean) -> Unit,
    showCoachesOverlay:Boolean,
    onshowGraphOverlayChange: (Boolean) -> Unit,
    showGraphOverlay: Boolean
) {
    val animatedColor = rememberAnimatedGradientColor()

    val newsItems = listOf(
        mapOf("title" to "Ne radimo 17.6", "imageUrl" to "https://scontent.fzag4-1.fna.fbcdn.net/v/t1.6435-9/96379603_159721362199244_1576151752367931392_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=833d8c&_nc_ohc=sQAuHjV-qSIQ7kNvwE9QuQA&_nc_oc=Adk_8r6iSXlg34M4EWUX2HSGwZ6DoW_wHCggcGy_3rCFvu4aG2mRDaxCanvbvSqzoLQ&_nc_zt=23&_nc_ht=scontent.fzag4-1.fna&_nc_gid=tJ4_peV25w8GbDfBi7uThg&oh=00_AfORP5uxJN24xOtS9UQFGyT_yId7q34aFsPhN8DEXLlwEA&oe=687959D0"),
        mapOf("title" to "Pravila", "imageUrl" to "https://scontent.fzag4-1.fna.fbcdn.net/v/t1.6435-9/96379603_159721362199244_1576151752367931392_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=833d8c&_nc_ohc=sQAuHjV-qSIQ7kNvwE9QuQA&_nc_oc=Adk_8r6iSXlg34M4EWUX2HSGwZ6DoW_wHCggcGy_3rCFvu4aG2mRDaxCanvbvSqzoLQ&_nc_zt=23&_nc_ht=scontent.fzag4-1.fna&_nc_gid=tJ4_peV25w8GbDfBi7uThg&oh=00_AfORP5uxJN24xOtS9UQFGyT_yId7q34aFsPhN8DEXLlwEA&oe=687959D0"),
        mapOf("title" to "Nova smith sprava", "imageUrl" to "https://scontent.fzag4-1.fna.fbcdn.net/v/t1.6435-9/96379603_159721362199244_1576151752367931392_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=833d8c&_nc_ohc=sQAuHjV-qSIQ7kNvwE9QuQA&_nc_oc=Adk_8r6iSXlg34M4EWUX2HSGwZ6DoW_wHCggcGy_3rCFvu4aG2mRDaxCanvbvSqzoLQ&_nc_zt=23&_nc_ht=scontent.fzag4-1.fna&_nc_gid=tJ4_peV25w8GbDfBi7uThg&oh=00_AfORP5uxJN24xOtS9UQFGyT_yId7q34aFsPhN8DEXLlwEA&oe=687959D0")
    )

    val sampleUsers = listOf(
        mapOf("username" to "Lekaa29", "points" to "1500"),
        mapOf("username" to "benediktiner", "points" to "1200"),
        mapOf("username" to "agavaga", "points" to "1100"),
        mapOf("username" to "luleCR7", "points" to "950"),
        mapOf("username" to "spaja2", "points" to "800"),
        mapOf("username" to "sancho17", "points" to "750"),
        mapOf("username" to "radja", "points" to "700"),
        mapOf("username" to "ega00", "points" to "650"),
        mapOf("username" to "anaa11", "points" to "600"),
        mapOf("username" to "nikol22", "points" to "550")
    )

    val coaches = listOf(
        mapOf("name" to "Anton Parat", "price" to "12", "url" to "https://scontent.fzag4-1.fna.fbcdn.net/v/t39.30808-6/457669530_1031801975615405_5482562526053612964_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=6ee11a&_nc_ohc=gvsFDXJEwr0Q7kNvwFxPZRv&_nc_oc=AdkI9wdlHu6O_NY5t1k73e9dvV3xu4Agm-_q8Z9SqGo_PYW1soSZh_aO1dCZZWMbOCs&_nc_zt=23&_nc_ht=scontent.fzag4-1.fna&_nc_gid=P3ER2tylAL2l5_UIIXHMOw&oh=00_AfPPXrxwWEfoYIIDB39GD6p7Tk_5-PeqwJKZNZnlwEagnA&oe=6858E4ED", "programCount" to "5", "description" to "Fokus na treninge za muškarce, razvoj mišićne mase, mršavljenje, oporavak od ozljeda, pripreme za specifične sportove..."),
        mapOf("name" to "Filip Zdep", "price" to "15", "url" to "https://scontent.fzag1-2.fna.fbcdn.net/v/t39.30808-6/493212790_1227841952678072_3967906942302841225_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=833d8c&_nc_ohc=F0SIXpuopIAQ7kNvwFAUYSm&_nc_oc=AdmsQ98l-RUCY0Rbhr7HGKYE7YOYsLJ8fuCIwEqQkTqBiYmeqQxNlUSXIVkQsi1JLbc&_nc_zt=23&_nc_ht=scontent.fzag1-2.fna&_nc_gid=vYyql7YJmewUZTDYAptjTw&oh=00_AfM60sm4iwlNcBEpAHZ6JzEEVAJj5oDDavcowl9XeCxpTA&oe=6858E0D3", "programCount" to "7", "description" to "Pokrivam veliku vrstu treninga za svaku dob, ciljeve, intenzitete i oporavke."),
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
                            Color(0xFF000000),
                            Color(0xFF000000),
                        )
                    )
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = CenterHorizontally
        ) {
            BackgroundTopBar(
                showNewsOverlay
            )
            Spacer(modifier = Modifier.padding(20.dp))
            TopTextSection(onTopTextPositioned = onTopTextPositioned, onViewGraphClick = { onshowGraphOverlayChange(true) })
            TopActionsContainer()
            Spacer(modifier = Modifier.padding(12.dp))
            Line(Color.White)
            Spacer(modifier = Modifier.padding(4.dp))

            // Pass the callback to show overlay
            NewsSection(
                newsList = newsItems,
                onShowAllClick = { onShowNewsOverlayChange(true) }
            )

            Spacer(modifier = Modifier.padding(4.dp))
            CoachesSection(
                coaches = coaches,
                onShowPrograms = { onCoachesOverlayChange(true) }
            )
            SimpleLeaderboard(users = sampleUsers,
                onViewTableClick = { onLeaderboardOverlayChange(true) },
                detail = false)

            Spacer(modifier = Modifier.padding(20.dp))

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

        AnimatedVisibility(
            visible = showCoachesOverlay,
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
            CoachesOverlay(
                coaches = coaches,
                onBackClick = { onCoachesOverlayChange(false) }
            )
        }

        AnimatedVisibility(
            visible = showGraphOverlay,
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
            DateRangeAnalyzer(
                onBackClick = { onshowGraphOverlayChange(false) }
            )

        }

    }
}






@Composable
fun BackgroundTopBar(
    showNewsOverlay: Boolean
) {


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .graphicsLayer(alpha = 1.0f)
            .zIndex(1f)
    ) {
        if(showNewsOverlay==false){
            TopBarContent(

            )

        }

    }
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
fun TopTextSection(onTopTextPositioned: (Float) -> Unit, onViewGraphClick: () -> Unit) {
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

            transparentButton(text = "View activity", onViewGraphClick)

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

    Box (
        modifier = Modifier.height(114.dp)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(0.7f), // Ensures the row spans the width
            horizontalArrangement = Arrangement.SpaceBetween, // Even spacing
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,

                ) {
                Row(){
                    Text(text = "4", fontSize = 72.sp, color = Color(0xFFFFFFFF))

                    Image(
                        painter = painterResource(id = R.drawable.icons8_exit_100),
                        contentDescription = "Star Icon",
                        modifier = Modifier.padding(start=4.dp)
                    )
                }
                Text(text = "soon leaving", fontSize = 12.sp, color = Color(0xF2BDBDBD))
            }

            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,

                ) {
                Row(
                ){
                    Image(
                        painter = painterResource(id = R.drawable.icons8_qr_100),
                        contentDescription = "Star Icon",
                        modifier = Modifier.size(84.dp)
                    )
                }
                Text(text = "scan QR", fontSize = 12.sp, color = Color(0xF2BDBDBD))
            }
        }
    }

}









@Composable
fun CoachesSection(coaches: List<Map<String, String>>,
                onShowPrograms: () -> Unit
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
                text = "Coaches & Programs",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
            Row(
                modifier = Modifier.clickable { onShowPrograms() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Programs",
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
            items(coaches) { item ->
                val name = item["name"] ?: "No name"
                val url = item["url"] ?: "No url"

                Box(
                    modifier = Modifier
                        .width(180.dp)
                        .height(100.dp)
                        .background(Color.Gray)

                ) {
                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color(0x44FFFFFF),
                                        Color(0xFF000000),
                                    )
                                )
                            )
                            .padding(12.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = name,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}


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
                .background(color = Color(0xF0000000))
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
fun CoachesOverlay(
    coaches: List<Map<String, String>>,
    onBackClick: () -> Unit
) {
    var selectedArticle by remember { mutableStateOf<Map<String, String>?>(null) }
    val programs = listOf(
        mapOf("name" to "Full Body Muškarci", "price" to "15", "durationWeeks" to "7", "description" to "8 tjedana push pull legs 6 treninga tjedno"),
        mapOf("name" to "Weight Loss", "price" to "15", "durationWeeks" to "7", "description" to "8 tjedana push pull legs 6 treninga tjedno"),
    )

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
                            Color(0xF0131313),
                            Color(0xF0131313),
                        )
                    )
                )
        ) {
            if (selectedArticle != null) {

                AnimatedVisibility(
                    visible = true,
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
                    SingleCoachView(
                        programs = programs,
                        onBackClick = { selectedArticle = null }
                    )
                }

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
                            text = "Coaches & Programs",
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
                        items(coaches) { Item ->
                            CoachesItemRow(
                                name = Item["name"] ?: "",
                                price = Item["price"] ?: "0.0",
                                programCount = Item["programCount"] ?: "0",
                                imageUrl = Item["url"] ?: "",
                                description = Item["description"] ?: "no description",
                                onClick = { selectedArticle = Item }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Programs() {
    val programs = listOf(
        mapOf("name" to "Filip Zdep", "price" to "15", "durationWeeks" to "7", "description" to "8 tjedana push pull legs 6 treninga tjedno"),
        mapOf("name" to "Filip Zdep", "price" to "15", "durationWeeks" to "7", "description" to "8 tjedana push pull legs 6 treninga tjedno"),
    )
    FitnessCentarUXTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            ProgramItemRow(name = "Full body Muškarci", price = "15", durationWeeks = "8", description = "8 tjedana push pull legs 6 treninga tjedno") {
            }
        }
    }
}



@Composable
fun ProgramItemRow(
    name: String,
    price: String,
    durationWeeks: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.95f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .padding(16.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                ){
                    Column {
                        Text(
                            text = name,
                            color = Color.White,
                            fontSize = 36.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            modifier= Modifier
                                .fillMaxWidth()
                                .height(72.dp)
                        )
                        Row(){
                            Text(
                                text = "Duration:     $durationWeeks",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.ExtraBold,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "weeks",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier.padding(start = 6.dp),
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Row(){
                            Text(
                                text = "Price:          $price",
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "E/hr",
                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                fontSize = 18.sp,
                                modifier = Modifier.padding(start = 6.dp),

                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Row {
                            Text(
                                text = "Description:",
                                color = Color.White,
                                fontWeight = FontWeight.ExtraBold,
                                lineHeight = 14.sp,
                                fontSize = 16.sp,
                                maxLines = 3,
                                modifier = Modifier.padding(top=8.dp),
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "$description",
                                color = Color.White,
                                lineHeight = 14.sp,
                                fontSize = 16.sp,
                                maxLines = 3,
                                modifier = Modifier.padding(top=8.dp, start = 8.dp),
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                    }
                }

            }
        }
    }
}


@Composable
fun CoachesItemRow(
    name: String,
    price: String,
    imageUrl: String,
    programCount: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(256.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.95f)
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
                contentDescription = price,
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight()
                    .background(Color.Yellow),
                contentScale = ContentScale.Crop
            )


            // Text on the right (half width)
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxHeight(),
            ) {
                Row(
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.padding(top=8.dp)
                ){
                    Column {
                        Text(
                            text = name,
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "$programCount programs",
                            color = Color.White,
                            fontSize = 12.sp,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = "$price E/hr",
                            color = Color.White,
                            fontSize = 12.sp,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Text(
                    text = "$description",
                    color = Color.White,
                    lineHeight = 14.sp,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    maxLines = 3,
                    modifier = Modifier.padding(top=30.dp),
                    overflow = TextOverflow.Ellipsis
                )
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
                            Color(0xF0131313),
                            Color(0xF0131313),
                        )
                    )
                )
        ) {
            if (selectedArticle != null) {
                AnimatedVisibility(
                    visible = true,
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
                    SingleArticleView(
                        article = selectedArticle!!,
                        onBackClick = { selectedArticle = null }
                    )
                }
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
            containerColor = Color.Black.copy(alpha = 0.95f)
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
fun SingleCoachView(
    programs: List<Map<String, String>>,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

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
        transparentButton(text = "Contact") {
            
        }
        // Scrollable content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(programs) { Item ->
                ProgramItemRow(
                    name = Item["name"] ?: "",
                    price = Item["price"] ?: "0.0",
                    durationWeeks = Item["durationWeeks"] ?: "0",
                    description = Item["description"] ?: "no description",
                    onClick = { }
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

@Composable
fun ProfileButton() {
    Box(
        modifier = Modifier
            .size(46.dp)
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
fun DateRangeAnalyzer(onBackClick: () -> Unit) {
    var weekOffset by remember { mutableStateOf(0) }

    val dataList = remember { generateDataList(100) }

    val now = LocalDateTime.now().minusWeeks(weekOffset.toLong())
    val startOfWeek = now.minusDays((now.dayOfWeek.value - 1).toLong()) // Monday
    val endOfWeek = startOfWeek.plusDays(6) // Sunday

    val filteredData = dataList.filter { dateTime ->
        val date = dateTime.toLocalDate()
        date.isEqual(startOfWeek.toLocalDate()) ||
                date.isEqual(endOfWeek.toLocalDate()) ||
                (date.isAfter(startOfWeek.toLocalDate()) && date.isBefore(endOfWeek.toLocalDate()))
    }

    val dailyCounts = (0..6).map { dayOffset ->
        val targetDate = startOfWeek.plusDays(dayOffset.toLong())
        filteredData.count { dateTime ->
            dateTime.toLocalDate() == targetDate.toLocalDate()
        }
    }

    val average = if (dailyCounts.isNotEmpty()) dailyCounts.average().toFloat() else 0f
    val maxCount = dailyCounts.maxOrNull() ?: 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xF8131313))
            .padding(16.dp)
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
                text = "Attendance Graph",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(40.dp))
        }



        Spacer(modifier = Modifier.height(32.dp))

        // Header with date range and navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { weekOffset++ },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xF5AAAAAA))
            ) {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Previous weeks",
                    tint = Color(0xFF000000)
                )
            }

            Text(
                text = "${startOfWeek.format(DateTimeFormatter.ofPattern("d.M"))}-${endOfWeek.format(DateTimeFormatter.ofPattern("d.M"))}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF9B9B9B)
            )

            IconButton(
                onClick = { weekOffset-- },
                enabled = weekOffset > 0,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(if (weekOffset > 0) Color(0xF5AAAAAA) else Color(0xF5AAAAAA))
            ) {
                Icon(
                    Icons.Default.KeyboardArrowRight,
                    contentDescription = "Next weeks",
                    tint = if (weekOffset > 0) Color(0xFF000000) else Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Chart
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF000000)),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Daily Distribution",
                    fontSize = 18.sp,
                    color = Color(0xFACACACA)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Canvas(
                    modifier = Modifier.fillMaxSize()
                            .padding(top=16.dp)

                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height - 40.dp.toPx()
                    val barWidth = canvasWidth / 7f * 0.7f
                    val barSpacing = canvasWidth / 7f

                    // Draw bars
                    dailyCounts.forEachIndexed { index, count ->
                        val barHeight = if (maxCount > 0) (count.toFloat() / maxCount) * canvasHeight else 0f
                        val x = index * barSpacing + barSpacing * 0.15f
                        val y = canvasHeight - barHeight

                        drawRect(
                            color = Color(0xFFF3B821),
                            topLeft = Offset(x, y),
                            size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
                        )

                        // Draw count text on top of bar
                        drawContext.canvas.nativeCanvas.drawText(
                            count.toString(),
                            x + barWidth / 2,
                            y - 10.dp.toPx(),
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.parseColor("#FFFFFF")
                                textSize = 12.sp.toPx()
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                        )
                    }

                    // Draw average line
                    if (maxCount > 0) {
                        val avgY = canvasHeight - (average / maxCount) * canvasHeight
                        drawLine(
                            color = Color(0xC9D1D1D1),
                            start = Offset(0f, avgY),
                            end = Offset(canvasWidth, avgY),
                            strokeWidth = 1.dp.toPx(),
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 5f), 0f)
                        )

                        // Average label
                        drawContext.canvas.nativeCanvas.drawText(
                            "Avg: ${String.format("%.1f", average)}",
                            canvasWidth - 60.dp.toPx(),
                            avgY - 8.dp.toPx(),
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.parseColor("#D1D1D1")
                                textSize = 11.sp.toPx()
                            }
                        )
                    }

                    // Draw day labels
                    val dayLabels = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
                    dayLabels.forEachIndexed { index, day ->
                        val x = index * barSpacing + barSpacing / 2
                        drawContext.canvas.nativeCanvas.drawText(
                            day,
                            x,
                            canvasHeight + 25.dp.toPx(),
                            android.graphics.Paint().apply {
                                color = android.graphics.Color.parseColor("#666666")
                                textSize = 10.sp.toPx()
                                textAlign = android.graphics.Paint.Align.CENTER
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Stats
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatCard("Total", filteredData.size.toString(), Color(0xFFFFFFFF))
            StatCard("Average", String.format("%.1f", average), Color(0xFFFDC282))
            StatCard("Max Day", maxCount.toString(), Color(0xFFFFFFFF))
        }
    }
}

@Composable
fun StatCard(label: String, value: String, color: Color) {
    Card(
        modifier = Modifier.width(100.dp).height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF000000)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
            Text(
                text = label,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
    }
}

fun generateDataList(size: Int): List<LocalDateTime> {
    val now = LocalDateTime.now()
    val startDate = now.minusWeeks(4) // Generate data for last 4 weeks

    return (1..size).map {
        val randomDaysBack = Random.nextLong(0, ChronoUnit.DAYS.between(startDate, now))
        val randomHours = Random.nextLong(0, 24)
        val randomMinutes = Random.nextLong(0, 60)

        startDate.plusDays(randomDaysBack).plusHours(randomHours).plusMinutes(randomMinutes)
    }.sortedDescending()
}

@Preview(showBackground = true)
@Composable
fun DateRangeAnalyzerPreview() {
    MaterialTheme {
        DateRangeAnalyzer({})
    }
}

*/


