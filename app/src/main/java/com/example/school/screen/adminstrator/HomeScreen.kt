package com.example.school.screen.adminstrator

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.school.R
import com.example.school.model.post.Post
import com.example.school.screen.adminstrator.model.DummyPost
import com.example.school.screen.adminstrator.model.getAllPost
import com.example.school.sealed.ApiResponse
import com.example.school.viewModel.HomePageViewModel
import com.example.school.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Preview(showBackground = true, showSystemUi = true)
@Composable
 fun HomeScreen() {
//    val postList = getAllPost()

    var postList :List<Post>?=null
    val listOfBanner = listOf(
        R.drawable.school_logo,
        R.drawable.school_logo,
        R.drawable.school_logo
    )
    val pageState = rememberPagerState(pageCount = { listOfBanner.size })
    val scope = rememberCoroutineScope()

    var context= LocalContext.current


    val homePageViewModel: HomePageViewModel = hiltViewModel()
    val loginState = homePageViewModel.postObservable.collectAsState()


//    homePageViewModel.postObserver("0","10",null,null)


    LaunchedEffect(loginState) {
        when (val state = loginState.value) {
            is ApiResponse.Loading -> {
                // Optionally, show a loading indicator
                Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
            }
            is ApiResponse.Success -> {
                // Handle success: store token and navigate
//                Toast.makeText(context, " ${state.data?.message}", Toast.LENGTH_SHORT).show()
                postList= state.data?.postList as List<Post>?
//                LazyColumn(modifier = Modifier.fillMaxSize()) {
//                    itemsIndexed(state.data.postList) { index, item ->
//                        if (item != null) {
//                            PostScreen(item)
//                        }
//                    }
//                }
//                state.data?.postList?.forEach{post->
//                    if (post != null) {
//                        post.let {
//                            PostScreen(it)
//
//                        }
//                    }
//
//                }


//                navController.navigate("home") {
//                    popUpTo("login") { inclusive = true }
//                }
            }
            is ApiResponse.Error -> {
                // Handle error
                Toast.makeText(context, "Error: ${state.errorMessage}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    // Auto-scroll effect
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pageState.currentPage + 1) % pageState.pageCount
            pageState.scrollToPage(nextPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.red))
            .padding(top = 80.dp)
    ) {

        Column(modifier = Modifier
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .background(color = colorResource(id = R.color.white))
            .verticalScroll(rememberScrollState())


        ) {
            // Banner Section
            BannerSection(
                listOfBanner = listOfBanner,
                pageState = pageState,
                scope = scope
            )

////             Post List Section
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(postList ?: emptyList()) { index, item ->
                    PostScreen(item)
                }
            }



        }
    }
}


// Banner Section
@Composable
fun BannerSection(listOfBanner: List<Int>, pageState: PagerState, scope: CoroutineScope) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .background(colorResource(id = R.color.white))
            .padding(top = 0.dp)
    ) {

        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(200.dp)  // Set a fixed height for the banner
        ) { currentPage ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white))
                    .padding(0.dp),

                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Image(
                    painter = painterResource(id = listOfBanner[currentPage]),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(16f / 5f),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // Left navigation button
        IconButton(
            onClick = {
                val prevPage = pageState.currentPage - 1
                if (prevPage >= 0) {
                    scope.launch { pageState.scrollToPage(prevPage) }
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .size(24.dp)
                .align(Alignment.CenterStart)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xFFE60753)
            )
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                tint = Color.LightGray
            )
        }

        // Right navigation button
        IconButton(
            onClick = {
                val nextPage = pageState.currentPage + 1
                if (nextPage < listOfBanner.size) {
                    scope.launch { pageState.scrollToPage(nextPage) }
                }
            },
            modifier = Modifier
                .padding(20.dp)
                .size(24.dp)
                .align(Alignment.CenterEnd)
                .clip(CircleShape),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = Color(0xFFEC1A62)
            )
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                tint = Color.LightGray
            )
        }
    }

    // Centered page indicator
    pageIndicator(
        pageCount = listOfBanner.size,
        currentPage = pageState.currentPage
    )
}

// Page Indicator
@Composable
fun pageIndicator(pageCount: Int, currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp, top = 10.dp)
    ) {
        repeat(pageCount) {
            IndicatorDots(isSelected = it == currentPage)
        }
    }
}

// Indicator Dots
@Composable
fun IndicatorDots(isSelected: Boolean) {
    val size by animateDpAsState(targetValue = if (isSelected) 12.dp else 8.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(size)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFFE01010) else Color(0xFFCCCCCC))
    )
}

// post
@Composable
fun PostScreen(list: Post) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast.makeText(
                    context,
                    "Clicked Item ${list.studentRegister?.name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 5.dp) // Material3
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.white))
            ) {
                // Post Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    // Profile Image
                    val profileImageResId = list.studentRegister?.img?.let {
                        context.resources.getIdentifier(it, "drawable", context.packageName)
                    } ?: R.drawable.ic_launcher_background // Default fallback

                    Image(
                        painter = painterResource(id = profileImageResId),
                        contentDescription = null,
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .padding(start = 10.dp)
                            .align(Alignment.CenterVertically)
                    )

                    // Post Details
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                            .fillMaxWidth()
                            .weight(1f)
                            .align(Alignment.CenterVertically)
                    ) {
                        list.studentRegister?.name?.let {
                            Text(
                                text = it,
                                fontSize = 16.sp,
                                color = colorResource(id = R.color.black)
                            )
                        }

                        list.category?.title?.let {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.black)
                            )
                        }
                    }

                    // More Options Icon
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                // Post Image
                val postImageResId = list.imageName?.let {
                    context.resources.getIdentifier(it, "drawable", context.packageName)
                } ?: R.drawable.banner // Default fallback

                Image(
                    painter = painterResource(id = postImageResId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .aspectRatio(16f / 8f),
                    contentScale = ContentScale.Crop
                )

                // Like Section
                Row(modifier = Modifier.padding(10.dp)) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

