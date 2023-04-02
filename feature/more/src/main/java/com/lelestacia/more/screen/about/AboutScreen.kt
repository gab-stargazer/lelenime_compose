package com.lelestacia.more.screen.about

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.lelestacia.common.R.*
import com.lelestacia.common.R.drawable.github

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavHostController
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "About",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }
                }
            )
        }
    ) { paddingValue ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            Image(
                painter = painterResource(id = drawable.lelenime),
                contentDescription = "Application Icon",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .heightIn(min = 128.dp, max = 176.dp)
                    .padding(top = 24.dp)
            )
            Text(
                text = "Version 1.0.0-beta",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
            Text(
                text = "Pocket anime index made with Kotlin for you",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Powered by Jikan API and MAL",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Developed By",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 48.dp,
                        start = 24.dp
                    ),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        color = MaterialTheme.colorScheme.onBackground.copy(
                            alpha = 0.1f
                        )
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = "https://avatars.githubusercontent.com/u/85838849?v=4",
                    contentDescription = "Developer Github Profile Picture",
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(BorderStroke(1.dp, MaterialTheme.colorScheme.primary), CircleShape)
                )

                Column {
                    Text(
                        text = "LeleStacia",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(text = "Kamil-Malik", style = MaterialTheme.typography.titleSmall)
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://github.com/Kamil-Malik/")
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor =
                            if (isSystemInDarkTheme()) Color.White
                            else Color.Black
                        ),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = github),
                                contentDescription = "Github Icon",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = "Github",
                                style = MaterialTheme.typography.titleSmall,
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
fun PreviewMoreScreen() {
    AboutScreen(navController = rememberNavController())
}