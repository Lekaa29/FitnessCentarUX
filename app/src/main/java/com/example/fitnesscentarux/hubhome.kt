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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.*
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.fitnesscentarux.ui.theme.FitnessCentarUXTheme
import java.time.format.TextStyle

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
    var isMapMode by remember { mutableStateOf(false) }
    val recentSearches = remember { mutableStateListOf("Yoga", "Cardio", "Weights", "Stretching") }
    var isSearchExpanded by remember { mutableStateOf(false) }

    var searchText by remember { mutableStateOf("") }

    if (isMapMode) {
        MapScreen(onBackClick = { isMapMode = false })
    } else {
        Background(onMapClick = { isMapMode = true },
            isSearchExpanded = isSearchExpanded,
            searchText = searchText,
            recentSearches = recentSearches,
            onSearchExpandedChange = { isSearchExpanded = it },
            onSearchTextChange = { searchText = it }

        )

    }
}

// MARK: - Map Screen Components

@Composable
fun MapScreen(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        MapTopBar(onBackClick = onBackClick)
        MapContent()
    }
}

@Composable
fun MapTopBar(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .zIndex(1f)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp, top = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            MapSearchButton()
            MapButton(onBackClick = onBackClick)
            ProfileButton()
        }
    }
}

@Composable
fun MapSearchButton() {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                Color(0xAA808080),
                CircleShape
            )
            .clickable { /* Search */ },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun MapButton(onBackClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                Color(0x99291CE6),
                CircleShape
            )
            .clickable { onBackClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )

    }
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
fun MapContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 56.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = CenterHorizontally
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

// MARK: - Background Screen Components

@Composable
fun Background(onMapClick: () -> Unit,
               modifier: Modifier = Modifier,
               isSearchExpanded: Boolean,
               searchText: String,
               onSearchExpandedChange: (Boolean) -> Unit,
               onSearchTextChange: (String) -> Unit,
               recentSearches: List<String>) {
    val scrollState = rememberScrollState()
    val scrollAnimationState = rememberScrollAnimationState()

    var topTextOffsetY by remember { mutableStateOf(0f) }
    val scrollProgress = calculateScrollProgress(scrollState, topTextOffsetY, scrollAnimationState)


    Box(modifier = Modifier.fillMaxSize()) {
        BackgroundTopBar(
            scrollProgress = scrollProgress,
            searchText = searchText,
            onMapClick = onMapClick,
            isSearchExpanded = isSearchExpanded,
            onSearchExpandedChange = onSearchExpandedChange,
            onSearchTextChange = onSearchTextChange
        )
        Box(){
            BackgroundScrollableContent(
                scrollState = scrollState,
                scrollAnimationState = scrollAnimationState,
                onTopTextPositioned = { topTextOffsetY = it }
            )
        }
        Box(){
            if (isSearchExpanded) {
                // Fullscreen semi-transparent gray overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xF2111111))
                        .clickable { onSearchExpandedChange(false) }
                )

                // Column with SearchBar and recent searches
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xD90F0F0F))
                        .padding(16.dp)
                ) {
                    /*
                    SearchBar(
                        isExpanded = isSearchExpanded,
                        searchText = searchText,
                        onSearchTextChange = onSearchTextChange,
                        onExpandChange = { onSearchExpandedChange(true) },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                     */

                    Spacer(modifier = Modifier
                        .height(12.dp)
                        .padding(top = 24.dp))

                    Text(
                        "Recent searches",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp, top = 24.dp)
                    )

                    androidx.compose.foundation.lazy.LazyColumn {
                        items(recentSearches.size) { index ->
                            Text(
                                recentSearches[index],
                                color = Color.LightGray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onSearchTextChange(recentSearches[index])
                                        // Maybe do search here
                                    }
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
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
    searchText: String,
    isSearchExpanded: Boolean,
    onSearchExpandedChange: (Boolean) -> Unit,
    onSearchTextChange: (String) -> Unit,

    onMapClick: () -> Unit
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
        TopBarBackground(scrollColor = scrollColor)

        TopBarContent(
            isSearchExpanded = isSearchExpanded,
            searchText = searchText,
            onSearchTextChange = { onSearchTextChange(it) },
            onSearchExpandChange = { onSearchExpandedChange(true)  },
            onMapClick = onMapClick
        )
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
    )
}

@Composable
fun TopBarContent(
    isSearchExpanded: Boolean,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchExpandChange: (Boolean) -> Unit,
    onMapClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp, top = 8.dp)
    ) {
        // Left-aligned title
        Box(
            modifier = Modifier
                .background(
                    Color(0x00000000),
                    RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            if(isSearchExpanded == false){
            Text(
                text = "FitnessCentar",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )}
        }


        // Right-aligned row of icons/search bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            SearchBar(
                isExpanded = isSearchExpanded,
                searchText = searchText,
                onSearchTextChange = onSearchTextChange,
                onExpandChange = onSearchExpandChange
            )

            Spacer(modifier = Modifier.size(8.dp))
            TopBarMapButton(onMapClick = onMapClick)
            Spacer(modifier = Modifier.size(8.dp))
            ProfileButton()
        }
    }
}


@Composable
fun TopBarMapButton(onMapClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                Color(0xA3808080),
                CircleShape
            )
            .clickable { onMapClick() },
            contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun BackgroundScrollableContent(
    scrollState: androidx.compose.foundation.ScrollState,
    scrollAnimationState: ScrollAnimationState,
    onTopTextPositioned: (Float) -> Unit
) {
    val firstAnimatedColor = rememberAnimatedGradientColor(Color(0xFF0E033F), Color(0xFF021674), 4000)
    val secondAnimatedColor = rememberAnimatedGradientColor(Color(0xFF0B0218), Color(0xFF0B0216), 5000)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(
                brush = Brush.linearGradient(
                    listOf(
                        firstAnimatedColor,
                        secondAnimatedColor,
                        secondAnimatedColor

                    )
                )
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(80.dp))

        UserMembershipsRow()
        Spacer(modifier = Modifier.height(10.dp))
        PromoRow()
        PromotionMembershipsRow()

    }
}
@Composable
fun PromoRow() {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.55f)
            .height(40.dp)

    ) {
        // Blurred rounded background
        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(RoundedCornerShape(12.dp)) // 👈 Rounded corners
                .background(
                    color = Color(0x16CCCCCC)
                )
                .blur(30.dp)
        )

        // Text on top
        Text(
            text = "PROMOTIONS & DISCOUNTS",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .align(Alignment.Center)

        )
    }
}





@Composable
fun UserMembershipsRow() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val itemWidth = screenWidth * 0.85f
    val itemHeight = screenHeight * 0.3f

    val gyms = remember {
        mutableStateListOf(
            "INSPECTOR" to "https://atlantisstrength.com/app/uploads/2022/02/gym-equipment-scaled-1920x1080.jpg",
            "DUX" to "https://watermark.lovepik.com/photo/20211126/large/lovepik-gym-fitness-equipment-picture_501101990.jpg",
            "XXL FITNESS" to "https://wallpapercave.com/wp/wp8834081.jpg",
            "BLUEGYM" to "https://watermark.lovepik.com/photo/20211123/large/lovepik-gym-picture_500868937.jpg"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight + 20.dp) // ensures same height as background
    ) {
        // Blurred background
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color(0x0AFFFFFF))
                .blur(40.dp)
        )

        Text(
            text = "Your membership's",
            color = Color(0xFFA2A2A2),
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .padding(start = 16.dp, top=10.dp, bottom=10.dp)
        )

        // Centered LazyRow vertically
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 20.dp), // ← Center the LazyRow in the parent Box
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(gyms) { (gymName, imageUrl) ->
                gymCard(gymName, "Expires in 18 days...", imageUrl, itemWidth, itemHeight-36.dp)
            }
        }
    }
}

@Composable
fun PromotionMembershipsRow() {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    val itemWidth = screenWidth * 0.8f
    val itemHeight = screenHeight * 0.2f

    val gyms = remember {
        mutableStateListOf(
            "BLUEGYM" to "https://watermark.lovepik.com/photo/20211123/large/lovepik-gym-picture_500868937.jpg",
            "XXL FITNESS" to "https://wallpapercave.com/wp/wp8834081.jpg",
            "INSPECTOR" to "https://atlantisstrength.com/app/uploads/2022/02/gym-equipment-scaled-1920x1080.jpg",
            "DUX" to "https://watermark.lovepik.com/photo/20211126/large/lovepik-gym-fitness-equipment-picture_501101990.jpg"
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(itemHeight) // ensures same height as background
    ) {
        // Blurred background
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color(0x0A000000))
                .blur(40.dp)
        )


        // Centered LazyRow vertically
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(top = 20.dp), // ← Center the LazyRow in the parent Box
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(gyms) { (gymName, imageUrl) ->
                gymPromoCard("20% MONTH", gymName, imageUrl, itemWidth, itemHeight-36.dp)
            }
        }
    }
}



@Composable
fun gymCard(gymName: String, description: String, imageUrl: String, itemWidth: Dp, itemHeight: Dp) {
    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
    ) {
        // Background Image
        AsyncImage(
            model = imageUrl,
            contentDescription = gymName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 50f // adjust this as needed for effect
                    )
                )
        )

        // Text content
        Column(
            modifier = Modifier
                .align(alignment = Alignment.BottomStart)
                .padding(start = 32.dp, bottom = 32.dp)
        ) {
            Text(
                text = gymName,
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = description,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}



@Composable
fun gymPromoCard(
    gymName: String,
    description: String,
    imageUrl: String,
    itemWidth: Dp,
    itemHeight: Dp
) {
    val grayScaleMatrix = ColorMatrix().apply { setToSaturation(0f) }

    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(itemHeight)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Gray)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = gymName,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
            colorFilter = ColorFilter.colorMatrix(grayScaleMatrix)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 300f
                    )
                )
        )

        // Text content
        Column(
            modifier = Modifier
                .align(alignment = Alignment.Center)

        ) {
            Text(
                text = gymName,
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = description,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }
    }
}


@Composable
fun rememberAnimatedGradientColor(initalValue: Color, targetValue:Color, milis: Int): Color {
    val infiniteTransition = rememberInfiniteTransition()

    return infiniteTransition.animateColor(
        initialValue = initalValue,//Color(0xFFFFB74A),
        targetValue = targetValue,//Color(0xFFF8E16C),
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = milis, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    ).value
}

@Composable
fun TopTextSection(onTopTextPositioned: (Float) -> Unit) {
    Row(
        modifier = Modifier
            .padding(vertical = 124.dp)
            .onGloballyPositioned {
                onTopTextPositioned(it.positionInParent().y)
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "14", color = Color.White)
    }
}

@Composable
fun ContentSections() {
    Spacer(modifier = Modifier.height(200.dp))
    Text("INSPECTOR VINKOVCI", color = Color.White)

}

@Composable
fun LeaderboardSection() {
    Column {
        Text("LEADERBOARD", color = Color.White, modifier = Modifier.padding(vertical = 10.dp))
        Text("LEKAA29 1000PTS", color = Color.White, modifier = Modifier.padding(vertical = 20.dp))
    }
}

// MARK: - Search Bar Component

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    isExpanded: Boolean,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onExpandChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val width by animateDpAsState(
        targetValue = if (isExpanded) 300.dp else 36.dp,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        modifier = modifier
            .size(height = 36.dp, width = width)
            .background(
                color = Color(0xAA808080),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable(enabled = !isExpanded) { onExpandChange(true) },
        contentAlignment = Alignment.CenterStart
    ) {
        if (isExpanded) {
            ExpandedSearchField(
                searchText = searchText,
                onSearchTextChange = onSearchTextChange,
                onExpandChange = onExpandChange
            )
        } else {
            CollapsedSearchButton(isExpanded, onExpandChange)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandedSearchField(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onExpandChange: (Boolean) -> Unit
) {
    androidx.compose.material3.TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        placeholder = { Text("Search") },
        colors = androidx.compose.material3.TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.LightGray
        ),
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Close Search",
                tint = Color.White,
                modifier = Modifier.clickable {
                    onExpandChange(false)
                }
            )
        }
    )
}

@Composable
fun CollapsedSearchButton(
    isExpanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                Color(0x00808080),
                CircleShape
            )
            .clickable {
                onExpandChange(true)

                Log.d("CLICK", "CLICKED")
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackgroundPreview() {
    FitnessCentarUXTheme {
        MainScreen()
    }
}*/