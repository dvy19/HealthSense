package com.example.healthsense.userDetails

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
import androidx.compose.material3.TextField
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
import com.example.healthsense.getstart.OnboardingPage
import com.example.healthsense.getstart.pages
import kotlinx.coroutines.launch



@Composable
fun GeneralDetails(
    page: OnboardingPage,
    title:String,

    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "",
            label = { Text("Enter age") },
            onValueChange = { /* Handle text change */ },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "",
            label = { Text("Enter city") },
            onValueChange = { /* Handle text change */ },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Composable
fun LocationDetails(
    page: OnboardingPage,
    title:String,

    ) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = "",
            label = { Text("Enter city") },
            onValueChange = { /* Handle text change */ },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = "",
            label = { Text("Enter state") },
            onValueChange = { /* Handle text change */ },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}



@Composable
fun UserDetailsScreen(
    rootNavController: NavController,
    onFinished: () -> Unit

){

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

                when(position){
                    0->{
                        GeneralDetails(
                            page = pages[position],
                            title = "General Details"
                        )

                    }
                    1->{
                        LocationDetails(
                            page = pages[position],
                            title = "Location Details"
                        )

                    }

                }

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





