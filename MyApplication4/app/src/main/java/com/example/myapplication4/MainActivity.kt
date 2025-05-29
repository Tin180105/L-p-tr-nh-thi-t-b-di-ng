package com.example.myapplication4

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import coil.compose.AsyncImage // Nếu bạn dùng AsyncImage trong ImageDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "welcome") {
                    composable("welcome") {
                        WelcomeScreen(onContinue = {
                            navController.navigate("ui_components")
                        })
                    }
                    composable("ui_components") {
                        UIComponentsListScreen(
                            onTextClick = {
                                navController.navigate("text_detail")
                            },
                            onImageClick = {
                                navController.navigate("image_detail")
                            },
                            onTextFieldClick = {
                                navController.navigate("textfield_detail")
                            },
                            onRowClick = { // THÊM callback cho Row
                                navController.navigate("row_detail")
                            }
                            // Thêm onColumnClick nếu bạn tạo ColumnDetailScreen
                        )
                    }
                    composable("text_detail") {
                        TextDetailScreen(navController)
                    }
                    composable("image_detail") {
                        ImageDetailScreen(navController) // Thêm NavController nếu cần nút back
                    }
                    composable("textfield_detail") {
                        TextFieldDetailScreen(navController)
                    }
                    composable("row_detail") { // THÊM Route cho RowDetailScreen
                        RowDetailScreen(navController)
                    }
                    // composable("column_detail") { ColumnDetailScreen(navController) }
                }
            }
        }
    }
}

@Composable
fun WelcomeScreen(onContinue: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Image(
            painter = painterResource(id = R.drawable.avatar), // Đảm bảo có R.drawable.avatar
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(160.dp),
            contentScale = ContentScale.Fit
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Jetpack Compose",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Jetpack Compose is a modern UI toolkit for\n" +
                        "building native Android applications using\n" +
                        "a declarative programming approach.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1DA1F2),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(25.dp)
        ) {
            Text(text = "I'm ready")
        }
    }
}

@Composable
fun UIComponentsListScreen(
    onTextClick: () -> Unit,
    onImageClick: () -> Unit,
    onTextFieldClick: () -> Unit,
    onRowClick: () -> Unit // THÊM tham số callback onRowClick
    // val onColumnClick: () -> Unit // Nếu bạn có Column
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // Thêm scroll nếu danh sách dài
    ) {
        Text(
            text = "UI Components List",
            fontSize = 20.sp,
            color = Color(0xFF1DA1F2),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Category(
            title = "Display",
            items = listOf(
                "Text" to "Displays text",
                "Image" to "Displays an image"
            ),
            onItemClick = { label ->
                when (label) {
                    "Text" -> onTextClick()
                    "Image" -> onImageClick()
                }
            }
        )

        Category(
            title = "Input",
            items = listOf(
                "TextField" to "Input field for text",
                "PasswordField" to "Input field for passwords"
            ),
            onItemClick = { label ->
                when (label) {
                    "TextField" -> onTextFieldClick()
                    // "PasswordField" -> onPasswordFieldClick()
                }
            }
        )

        Category(
            title = "Layout",
            items = listOf(
                "Column" to "Arranges elements vertically",
                "Row" to "Arranges elements horizontally"
            ),
            onItemClick = { label ->
                Log.d("UIComponentsListScreen", "Layout item clicked: $label") // Log để debug
                when (label) {
                    // "Column" -> onColumnClick()
                    "Row" -> onRowClick() // GỌI onRowClick khi "Row" được chọn
                }
            }
        )
    }
}

@Composable
fun Category(
    title: String,
    items: List<Pair<String, String>>,
    onItemClick: ((String) -> Unit)? = null
) {
    Text(
        text = title,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color.Black,
        modifier = Modifier.padding(top = 12.dp, bottom = 6.dp)
    )
    items.forEach { (label, desc) ->
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .background(color = Color(0xFFD6ECFF), shape = RoundedCornerShape(8.dp))
                .clickable {
                    Log.d("Category", "Clicked on item: $label from category: $title")
                    onItemClick?.invoke(label)
                }
                .padding(12.dp)
        ) {
            Column {
                Text(text = label, fontWeight = FontWeight.Bold, color = Color.Black)
                Text(text = desc, fontSize = 13.sp, color = Color.DarkGray)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Text Component Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    pushStyle(SpanStyle(color = Color.Black, fontSize = 22.sp))
                    append("The ")
                    pop()

                    pushStyle(SpanStyle(color = Color.Gray, textDecoration = TextDecoration.LineThrough, fontSize = 22.sp))
                    append("quick ")
                    pop()

                    pushStyle(SpanStyle(color = Color(0xFFB86B00), fontWeight = FontWeight.Bold, fontSize = 22.sp))
                    append("Brown\n")
                    pop()

                    pushStyle(SpanStyle(color = Color.Blue, fontSize = 22.sp))
                    append("fox ")
                    pop()

                    pushStyle(SpanStyle(letterSpacing = 3.sp, color = Color.DarkGray, fontSize = 22.sp))
                    append("j u m p s ")
                    pop()

                    pushStyle(SpanStyle(fontWeight = FontWeight.Bold, background = Color.Yellow, fontSize = 22.sp))
                    append("over\n")
                    pop()

                    pushStyle(SpanStyle(color = Color.Red, textDecoration = TextDecoration.Underline, fontSize = 22.sp))
                    append("the ")
                    pop()

                    pushStyle(SpanStyle(fontStyle = FontStyle.Italic, color = Color.Magenta, fontSize = 22.sp))
                    append("lazy ")
                    pop()

                    pushStyle(SpanStyle(color = Color.Black, fontSize = 22.sp))
                    append("dog.")
                    pop()
                },
                textAlign = TextAlign.Center
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageDetailScreen(navController: NavController) { // Thêm NavController
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Images") },
                navigationIcon = { // Thêm nút back
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Image from URL (AsyncImage):",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            AsyncImage(
                model = "https://picsum.photos/seed/compose/400/300", // URL ảnh mẫu
                contentDescription = "Image from URL",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 8.dp)
                    .background(Color.LightGray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.avatar) // Placeholder
            )
            Text(
                text = "https://picsum.photos/seed/compose/400/300",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Image from Drawable Resource:",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            Image(
                painter = painterResource(id = R.drawable.avatar1), // Đảm bảo có avatar1
                contentDescription = "In app image 1",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 8.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "R.drawable.avatar1",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.avatar2), // Đảm bảo có avatar2
                contentDescription = "In app image 2",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .padding(vertical = 8.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "R.drawable.avatar2",
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDetailScreen(navController: NavController) {
    var text by rememberSaveable { mutableStateOf("") } // Sử dụng rememberSaveable

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("TextField", color = Color(0xFF1DA1F2)) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFF1DA1F2))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter information") },
                placeholder = { Text("e.g., Your Name") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp), // Điều chỉnh bo góc
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1DA1F2),
                    unfocusedBorderColor = Color.LightGray,
                    focusedLabelColor = Color(0xFF1DA1F2),
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "You entered: $text",
                color = Color.DarkGray,
                fontSize = 16.sp
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Row Layout Examples") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Thêm scroll nếu nội dung dài
        ) {
            Text(
                text = "Row Layout Examples",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1DA1F2),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            ExampleRowSection("Arrangement.SpaceEvenly", Arrangement.SpaceEvenly)
            Spacer(modifier = Modifier.height(24.dp))

            ExampleRowSection("Arrangement.SpaceBetween", Arrangement.SpaceBetween)
            Spacer(modifier = Modifier.height(24.dp))

            ExampleRowSection("Arrangement.Center", Arrangement.Center)
            Spacer(modifier = Modifier.height(24.dp))

            ExampleRowSection("Arrangement.Start", Arrangement.Start)
            Spacer(modifier = Modifier.height(24.dp))

            ExampleRowSection("Arrangement.End", Arrangement.End)
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Row with Weights",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f))
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color(0xFF90CAF9))
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) { Text("1f") }
                Box(
                    modifier = Modifier
                        .weight(2f)
                        .fillMaxHeight()
                        .background(Color(0xFF1E88E5))
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) { Text("2f", color = Color.White) }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color(0xFF90CAF9))
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) { Text("1f") }
            }
        }
    }
}

@Composable
fun ExampleRowSection(title: String, arrangement: Arrangement.Horizontal) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Row Layout",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF1DA1F2),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        repeat(5) { // 5 dòng giống ảnh
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0..2) {
                    Box(
                        modifier = Modifier
                            .size(width = 70.dp, height = 40.dp)
                            .background(
                                color = if (i == 1) Color(0xFF2196F3) else Color(0xFFBBDEFB), // giữa: đậm, còn lại: nhạt
                                shape = RoundedCornerShape(12.dp)
                            )
                    )
                }
            }
        }
    }
}
