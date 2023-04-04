package com.lelestacia.more.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lelestacia.more.component.DeveloperCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    navController: NavHostController
) {
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
                painter = painterResource(id = com.lelestacia.common.R.drawable.lelenime),
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

            DeveloperCard(
                name = "Lelestacia as Programmer",
                nickName = "Kamil-Malik",
                imageURL = "https://avatars.githubusercontent.com/u/85838849?v=4",
                githubURL = "https://github.com/Kamil-Malik/",
                facebookURL = "https://www.facebook.com/KamilGC"
            )

            DeveloperCard(
                name = "Kaori Miyazono as Consultant",
                nickName = "Kao chan",
                imageURL = "https://scontent.fcgk6-2.fna.fbcdn.net/v/t39.30808-6/292270909_895812138479977_1493775890634793840_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=09cbfe&_nc_eui2=AeGO4detcXf77S1uKx7-L31yDhwPM2rS1jcOHA8zatLWN8oL2o9NlrojoSslVUH7W2xm1aBbQ_vwN7icyoM-q5Pe&_nc_ohc=rEJLvEUr6ywAX8HzQNF&_nc_ht=scontent.fcgk6-2.fna&oh=00_AfD8XlZc-mfxDbbSVFm7vqzwrPBeCpqlVZIkqOmXdSreTA&oe=643055C0",
                githubURL = null,
                facebookURL = "https://www.facebook.com/kaori.miyazono.37266136"
            )

            DeveloperCard(
                name = "Chat GPT as Consultant",
                nickName = "GPT3",
                imageURL = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUIwD84MUO1g9n6U0VWNJKRK0pPFVGTXsBeQ3KTeeGTpxX7VKB3-rMoW1J2bvU2blIFiM&usqp=CAU",
                githubURL = null,
                facebookURL = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMoreScreen() {
    AboutScreen(navController = rememberNavController())
}