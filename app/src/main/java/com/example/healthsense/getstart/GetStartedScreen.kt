package com.example.healthsense.getstart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

data class OnboardingPage(
    val imageRes: Int, // Use your drawable resource IDs
    val title: String,
    val description: String
)

val pages = listOf(
    OnboardingPage(
        imageRes = android.R.drawable.ic_menu_compass,
        title = "Discover New Things",
        description = "Explore a world of possibilities with our curated content just for you."
    ),
    OnboardingPage(
        imageRes = android.R.drawable.ic_menu_send,
        title = "Connect Instantly",
        description = "Reach out to your friends and family with a single tap from anywhere."
    )
)

@Composable
fun GetStartScreen(
    rootNavController: NavController,
    onFinished: () -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Horizontal Pager for the content
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { position ->
            PagerContent(page = pages[position])
        }

        // A Row to hold the dots
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pages.size) { iteration ->
                // Check if this dot is the one the user is currently looking at
                val isSelected = pagerState.currentPage == iteration

                // Define the color and shape
                val color = if (isSelected) MaterialTheme.colorScheme.primary else Color.LightGray

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape) // Use CircleShape for circles or RoundedCornerShape for rectangles
                        .background(color)
                        .size(
                            width = if (isSelected) 24.dp else 8.dp, // Active is long, inactive is a dot
                            height = 8.dp
                        ) // Make the active one slightly bigger
                )
            }
        }

        // 2. Navigation Section (Buttons)
        Spacer(modifier = Modifier.height(24.dp))

        if (pagerState.currentPage < pages.size - 1) {
            // "Next" Button for the first screen
            Button(
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                },
                modifier = Modifier.fillMaxWidth().height(56.dp)
            ) {
                Text("Next")
            }
        } else {
            // "Get Started" Button for the final screen
            Button(
                onClick = onFinished,
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text("Get Started")
            }
        }
    }
}

@Composable
fun PagerContent(page: OnboardingPage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = null,
            modifier = Modifier.size(250.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}