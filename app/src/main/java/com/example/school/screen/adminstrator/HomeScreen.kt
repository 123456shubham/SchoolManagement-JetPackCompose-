package com.example.school.screen.adminstrator

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.school.R
import com.example.school.model.post.Post

import com.example.school.viewModel.HomePageViewModel
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
    val homeState by homePageViewModel.postObservable.collectAsState()

// Load initial data
    LaunchedEffect(Unit) {
        homePageViewModel.postObserver("0", "10", null, null)
    }

    var isLoading by remember { mutableStateOf(false) }


    // LazyListState for pagination
    val listState = rememberLazyListState()

    // Pagination trigger when user reaches the bottom
    LaunchedEffect(listState.firstVisibleItemIndex) {
        if (!isLoading && listState.layoutInfo.visibleItemsInfo.isNotEmpty()) {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.last()
            if (lastVisibleItem.index == postList!!.size - 1) {
//                loadMorePosts(homePageViewModel, postList.size, 10)
            }
        }
    }

//    LaunchedEffect(homeState) {
//        when {
//            homeState?.postList != null -> {
//                postList = homeState.postList
//            }
//            else -> {
//                Toast.makeText(context, "Failed to load posts or no posts available.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


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
//            .verticalScroll(rememberScrollState())

    ) {

        Column(modifier = Modifier
            .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
            .background(color = colorResource(id = R.color.white))



        ) {
//
//            Row {
//                Image( Icons.Filled.Add, contentDescription =null )
//            }


            // Banner Section
            BannerSection(
                listOfBanner = listOfBanner,
                pageState = pageState,
                scope = scope
            )

////             Post List Section
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(top = 0.dp)) {
                itemsIndexed(homeState.postList ?: emptyList()) { index, item ->
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
            .height(150.dp)
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
    PageIndicator(
        pageCount = listOfBanner.size,
        currentPage = pageState.currentPage
    )
}

// Page Indicator
@Composable
fun PageIndicator(pageCount: Int, currentPage: Int) {
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
@SuppressLint("DiscouragedApi")
@Composable
fun PostScreen(list: Post) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(
                        context,
                        "Clicked Item ${list.studentRegister?.name}",
                        Toast.LENGTH_SHORT
                    )
                    .show()
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
                        .padding(top = 10.dp, start = 10.dp)
                ) {
//                    val profileImageUrl = list.studentRegister?.img // This is your URL string, e.g., "https://example.com/image.png"
                    val profileImageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0"

                    Image(
                        painter = rememberAsyncImagePainter(
                            model = profileImageUrl,
                            placeholder = painterResource(R.drawable.ic_launcher_background), // Placeholder while loading
                            error = painterResource(R.drawable.ic_launcher_background) // Fallback on error
                        ),
                        contentDescription = null,
                        modifier = Modifier

                            .clip(CircleShape)
                            .size(40.dp)
//                            .height(50.dp)
//                            .width(50.dp)
                            .padding(start = 0.dp)
                            .align(Alignment.CenterVertically),
                        contentScale = ContentScale.Crop // Crop the image to fit the circle perfectly

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

                val profileImageUrl = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0"

                Image(
                    painter = rememberAsyncImagePainter(
                        model = profileImageUrl,
                        placeholder = painterResource(R.drawable.ic_launcher_background), // Placeholder while loading
                        error = painterResource(R.drawable.ic_launcher_background) // Fallback on error
                    ),
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

