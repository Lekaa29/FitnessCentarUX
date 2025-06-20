package com.example.fitnesscentarux


import android.R.attr.start
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.example.fitnesscentarux.ui.theme.FitnessCentarUXTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Locale
import kotlin.math.max
import kotlin.random.Random

data class SelectItem(
    val title: String,
    val imageUrl: String
)

data class NameItem(
    val title: String,
    val name: String
)


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

    var editProfileOverlay by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(){
            BackgroundScrollableContent(
                scrollState = scrollState,
                onTopTextPositioned = { topTextOffsetY = it },
                onEditProfileChange = { editProfileOverlay = it},
                editProfileOverlay = editProfileOverlay
            )
        }

    }
}



@Composable
fun BackgroundScrollableContent(
    scrollState: androidx.compose.foundation.ScrollState,
    onTopTextPositioned: (Float) -> Unit,
    onEditProfileChange: (Boolean) -> Unit,
    editProfileOverlay: Boolean
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

    val user = mapOf("username" to "Lekaa29",
        "firstName" to "Lovro",
        "lastName" to "Lesic",
        "bestStreak" to mapOf("inspector" to "6", "dux" to 14),
        "streak" to mapOf("inspector" to "0", "dux" to 6),
        "points" to "1256",
        "url" to "https://i.pinimg.com/736x/7a/64/5a/7a645adf8176d3a127e116af3a371af9.jpg")

    val selectItems = listOf(
        SelectItem("INSPECTOR VINKOVCI", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgef7rkwKeWEoJN9N05f9fIBNN5DIK4-ushA&s"),
        SelectItem("DUX FITNESS", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQgmrVlUh9hjDw6TY8CVQ_xn1VSSPB7bVLT0Q&s"),
        SelectItem("XXL FITNESS", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2LXASLnv8WG6a7ynMAR3kkBqFCrhKC2JDEg&s"),
        SelectItem("BLUEGYM", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuTNY0QjgGCgIwZ4JmAFmH5Arlun9LlVCBgQ&s")
    )

    val nameItems = listOf(
        NameItem("INSPECTOR VINKOVCI", "12"),
        NameItem("DUX FITNESS", "17"),
        NameItem("XXL FITNESS", "25"),
        NameItem("BLUEGYM", "2")
    )

    // State management
    var selectedIndex by remember { mutableIntStateOf(0) }
    val selectedName = nameItems.find { it.title == selectItems[selectedIndex].title }?.name ?: "Unknown"


    val attendances: List<Attendance> = emptyList()
    Box(modifier = Modifier.fillMaxSize()) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xF00C1813),
                            Color(0xFF000000),
                            Color(0xFF000000),
                            Color(0xFF000000),
                        )
                    )
                ),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(20.dp))
            TopTextSection(user=user,onTopTextPositioned = onTopTextPositioned, onEditProfileChange = { onEditProfileChange(true)})
            Spacer(modifier = Modifier.padding(20.dp))

            MobileSelect(
                items = selectItems,
                selectedIndex = selectedIndex,
                onSelectionChanged = { selectedIndex = it }
            )
            Spacer(modifier = Modifier.padding(20.dp))

            StreakBoxes(selectedName, selectedName)
            
            AttendanceCalendarGrid(attendances = attendances)

        }
        AnimatedVisibility(
            visible = editProfileOverlay,
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
            EditUserOverlay(
                user = user,
                onBackClick = { onEditProfileChange(false) }
            )
        }


    }
}



@Composable
fun EditUserOverlay(user: Map<String, Any>, onBackClick: (Boolean) -> Unit) {
    val userName = user["username"] as? String ?: "noname"
    val userFirstName = user["firstName"] as? String ?: ""
    val userLastName = user["lastName"] as? String ?: ""
    val profileUrl = user["url"] as? String ?: ""

    var usernameInput by remember { mutableStateOf(userName) }
    var firstNameInput by remember { mutableStateOf(userFirstName) }
    var lastNameInput by remember { mutableStateOf(userLastName) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.6f)
                .background(
                    Color(0xFF1A1A1A),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp)
        ) {
            // Back button and title
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackClick(false) },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Text(
                    text = "Edit Profile",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(32.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Profile section
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Profile picture
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxHeight(0.5f)
                ){
                    AsyncImage(
                        model = profileUrl,
                        contentDescription = "Profile picture",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF333333)),
                        contentScale = ContentScale.Crop
                    )
                    Button(
                        onClick = {
                            // Handle save logic here
                            onBackClick(true)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xF216707C)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.height(40.dp).width(100.dp)
                    ) {
                        Text(
                            text = "Upload",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,

                        )
                    }
                }

                // User info inputs
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Username
                    Text(
                        text = "Username",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    SimpleTextField(
                        value = usernameInput,
                        onValueChange = { usernameInput = it },
                        placeholder = "Enter username"
                    )

                    // First name
                    Text(
                        text = "First Name",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    SimpleTextField(
                        value = firstNameInput,
                        onValueChange = { firstNameInput = it },
                        placeholder = "Enter first name"
                    )

                    // Last name
                    Text(
                        text = "Last Name",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    SimpleTextField(
                        value = lastNameInput,
                        onValueChange = { lastNameInput = it },
                        placeholder = "Enter last name"
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Save button
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        // Handle save logic here
                        onBackClick(true)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF444444)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.height(48.dp)
                ) {
                    Text(
                        text = "Save Profile",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color(0xFF888888)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedContainerColor = Color(0xFF2A2A2A),
            unfocusedContainerColor = Color(0xFF2A2A2A),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedPlaceholderColor = Color(0xFF888888),
            unfocusedPlaceholderColor = Color(0xFF888888)
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                Color(0xFF444444),
                shape = RoundedCornerShape(8.dp)
            ),
        singleLine = true
    )
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
fun TopTextSection(user: Map<String,Any>, onTopTextPositioned: (Float) -> Unit, onEditProfileChange: () -> Unit) {
    val selectItems = listOf(
        SelectItem("Apple", "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=100&h=100&fit=crop&crop=center"),
        SelectItem("Google", "https://images.unsplash.com/photo-1573804633927-bfcbcd909acd?w=100&h=100&fit=crop&crop=center"),
        SelectItem("Microsoft", "https://images.unsplash.com/photo-1633419461186-7d40a38105ec?w=100&h=100&fit=crop&crop=center"),
        SelectItem("Amazon", "https://images.unsplash.com/photo-1523474253046-8cd2748b5fd2?w=100&h=100&fit=crop&crop=center")
    )



    Row(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .fillMaxWidth()
            .onGloballyPositioned {
                onTopTextPositioned(it.positionInParent().y)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(start=50.dp)
        ) {
            AsyncImage(
                model = user["url"] ?: "",
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp) // Set a specific size instead of fillMaxSize
                    .clip(CircleShape) // Makes the image circular
                    .background(Color(0xFFFFFF00))
            )
        }

        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxHeight()
                .width(160.dp)

                .padding(start = 30.dp)

        ) {
            // Add username at the top

            Text(
                text = user["username"]?.toString() ?: "Unknown User",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,

            )
            Text(
                text = user["joined"]?.toString() ?: "joined 08/2023",
                color = Color(0xFFB9B9B9),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top=20.dp)
                )


        }

        Box(
            modifier = Modifier
                .size(52.dp)
                .padding(bottom = 20.dp, end = 10.dp)
                .clickable {
                    onEditProfileChange()
                    Log.d("clicked", "clicked")
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icons8_edit_100),
                contentDescription = "Edit Icon",
                modifier = Modifier.size(32.dp) // Smaller than the clickable area
            )
        }
    }

}


@Composable
fun StreakBoxes(streakNumber: String, pointsNumber: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Streak Box with border
        Box(
            modifier = Modifier
                .height(124.dp) // 120 + 2dp top + 2dp bottom
                .width(144.dp)  // 140 + 2dp left + 2dp right
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0x2AFF8400),
                            Color(0xFFFF8400),
                            Color(0xFFFF8400),
                        )
                    ), // your desired border color
                    shape = RoundedCornerShape(18.dp) // slightly more rounded
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .height(120.dp)
                    .width(140.dp)
                    .background(
                        Color(0xFF0F0F0F),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                AnimatedContent(
                    targetState = streakNumber,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(1500)) togetherWith
                                fadeOut(animationSpec = tween(150))
                    },
                    label = "streak_animation"
                ) { animatedStreakNumber ->
                    Text(
                        text = animatedStreakNumber,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                Text(
                    text = "streak",
                    fontSize = 12.sp,
                    color = Color(0xFF747474),
                )
            }
        }

        // Points Box with border
        Box(
            modifier = Modifier
                .height(124.dp)
                .width(144.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(18.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .height(120.dp)
                    .width(140.dp)
                    .background(
                        Color(0xFF0F0F0F),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                AnimatedContent(
                    targetState = pointsNumber,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(1000)) togetherWith
                                fadeOut(animationSpec = tween(150))
                    },
                    label = "points_animation"
                ) { animatedPointsNumber ->
                    Text(
                        text = "27$animatedPointsNumber",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                Text(
                    text = "points",
                    fontSize = 12.sp,
                    color = Color(0xFF747474),
                )
            }
        }
    }
}

@Composable
fun MobileSelect(
    items: List<SelectItem>,
    selectedIndex: Int,
    onSelectionChanged: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        CustomCompactDropdownField(
            title = items[selectedIndex].title,
            imageUrl = items[selectedIndex].imageUrl,
            onClick = { expanded = !expanded }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF000000))
                .fillMaxWidth(0.85f)
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    modifier = Modifier.background(Color.Black),
                    text = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AsyncImage(
                                model = item.imageUrl,
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(36.dp),
                                contentScale = ContentScale.Crop
                            )
                            Text(item.title, fontSize = 8.sp, color = Color.White)
                        }
                    },
                    onClick = {
                        onSelectionChanged(index)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CustomCompactDropdownField(
    title: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .height(36.dp)
            .border(
                width = 1.dp,
                color = Color(0xFF9C9C9C),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier.size(36.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = title,
                fontSize = 10.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Dropdown",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
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




@Preview(showBackground = true)
@Composable
fun BackgroundPreview() {
    FitnessCentarUXTheme {
        MainScreen()
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp)

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
        modifier = Modifier
            .width(100.dp)
            .height(80.dp),
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

@Composable
fun AttendanceCalendarGrid(
    attendances: List<Attendance>,
    currentMonth: Int = Calendar.getInstance().get(Calendar.MONTH) + 1,
    currentYear: Int = Calendar.getInstance().get(Calendar.YEAR),
    onMonthChanged: (month: Int, year: Int) -> Unit = { _, _ -> }
) {
    var displayMonth by remember { mutableStateOf(currentMonth) }
    var displayYear by remember { mutableStateOf(currentYear) }

    // Parse attendance dates for the current month
    val attendanceDates = remember(attendances, displayMonth, displayYear) {
        attendances.mapNotNull { attendance ->
            attendance.Timestamp?.let { timestamp ->
                try {
                    // Assuming timestamp format is ISO format like "2024-01-15T10:30:00"
                    val dateTime = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).parse(timestamp)
                    val calendar = Calendar.getInstance().apply { time = dateTime!! }

                    if (calendar.get(Calendar.MONTH) + 1 == displayMonth &&
                        calendar.get(Calendar.YEAR) == displayYear) {
                        calendar.get(Calendar.DAY_OF_MONTH)
                    } else null
                } catch (e: Exception) {
                    // Try alternative format if the first one fails
                    try {
                        val dateTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(timestamp)
                        val calendar = Calendar.getInstance().apply { time = dateTime!! }

                        if (calendar.get(Calendar.MONTH) + 1 == displayMonth &&
                            calendar.get(Calendar.YEAR) == displayYear) {
                            calendar.get(Calendar.DAY_OF_MONTH)
                        } else null
                    } catch (e2: Exception) {
                        null
                    }
                }
            }
        }.toSet()
    }

    // Get calendar info for current month
    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, displayYear)
        set(Calendar.MONTH, displayMonth - 1) // Calendar month is 0-based
        set(Calendar.DAY_OF_MONTH, 1)
    }

    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val startDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // Convert to 0-6 (Sunday-Saturday)

    val monthName = DateFormatSymbols().months[displayMonth - 1]

    Column(
        modifier = Modifier
            .fillMaxWidth(0.85F)
            .height(500.dp)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Month header with navigation
        Row(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (displayMonth == 1) {
                        displayMonth = 12
                        displayYear -= 1
                    } else {
                        displayMonth -= 1
                    }
                    onMonthChanged(displayMonth, displayYear)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous month",
                    tint = Color.White

                )
            }

            Text(
                text = "$monthName $displayYear",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                color = Color(0xFFFFFFFF)
            )

            IconButton(
                onClick = {
                    if (displayMonth == 12) {
                        displayMonth = 1
                        displayYear += 1
                    } else {
                        displayMonth += 1
                    }
                    onMonthChanged(displayMonth, displayYear)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next month",
                    tint = Color.White

                )
            }
        }

        // Days of week header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val daysOfWeek = listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
            daysOfWeek.forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFFFFFF)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Calendar grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(7),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Empty cells for days before the first day of the month
            items(startDayOfWeek) {
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(
                            Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                )
            }

            // Days of the month
            items(daysInMonth) { dayIndex ->
                val day = dayIndex + 1
                val hasAttendance = attendanceDates.contains(day)

                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(
                            color = if (hasAttendance) Color(0xFFFFFFFF) else Color(0xD3535353),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Gray.copy(alpha = 0.3f),
                            shape = RoundedCornerShape(4.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                }
            }
        }
    }
}


data class Attendance(
    val IdAttendance: Int,
    val UserId: Int,
    val FitnessCentarId: Int,
    val Timestamp: String?,
)

val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
fun randomTimestampWithinLast30Days(): String {
    val now = LocalDateTime.now()
    val daysAgo = Random.nextInt(0, 30)
    val hoursAgo = Random.nextInt(0, 24)
    val minutesAgo = Random.nextInt(0, 60)
    val randomTime = now.minusDays(daysAgo.toLong())
        .minusHours(hoursAgo.toLong())
        .minusMinutes(minutesAgo.toLong())
    return randomTime.truncatedTo(ChronoUnit.SECONDS).format(formatter)
}

