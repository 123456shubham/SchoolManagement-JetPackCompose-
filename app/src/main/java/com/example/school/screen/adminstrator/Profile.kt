package com.example.school.screen.adminstrator

import android.provider.ContactsContract.Profile
import android.widget.ImageView
import android.widget.TableRow
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.school.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Profile(){


    val profileTabItem= listOf(
        profileTabItem("Features",Icons.Filled.FavoriteBorder,Icons.Outlined.Favorite),
        profileTabItem("Teacher",Icons.Outlined.Face,Icons.Filled.Face),
        profileTabItem("Other Staff",Icons.Filled.FavoriteBorder,Icons.Outlined.Favorite),
    )

    var selectedTabIndex by remember{
        mutableIntStateOf(0)
    }

    val pageState = rememberPagerState(pageCount = { profileTabItem.size })

    LaunchedEffect(selectedTabIndex) {
        pageState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pageState.currentPage) {
        if (!pageState.isScrollInProgress){
            selectedTabIndex=pageState.currentPage

        }
    }

    Box (modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.red))){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 80.dp)
                .clip(RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp))
                .fillMaxSize()
                .background(color = colorResource(id = R.color.white))) {

            Box(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .size(130.dp) // Match the size of the Image
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Circular Image",
                    modifier = Modifier
                        .matchParentSize()
                        .clip(CircleShape)
                )

                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    contentDescription = "Add Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.BottomEnd)
                        .padding(4.dp)
                )
            }

            TabRow(selectedTabIndex, modifier = Modifier.padding(10.dp)){
                profileTabItem.forEachIndexed { index, profileTabItem ->

                    Tab(modifier = Modifier.background(Color.White),selected = index ==selectedTabIndex,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Text(text = profileTabItem.title)
                        },
                        icon = {
                            Icon(imageVector =
                            if (index == selectedTabIndex)
                            {profileTabItem.selectedIcon
                            }else{profileTabItem.unSelectedIcon},
                                contentDescription =profileTabItem.title )
                        }
                    )
                }
            }

            HorizontalPager(state = pageState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)) {
                index->
                
            }

        }
    }

}

data class profileTabItem(val title:String,val unSelectedIcon:ImageVector,val selectedIcon:ImageVector)