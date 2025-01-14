package com.example.school.screen.adminstrator.model

import com.example.school.R

data class DummyPost(var img:Int,var  name:String,var categoryName:String,var postImg:Int)


fun  getAllPost() : List<DummyPost> {
    return listOf(
        DummyPost(R.drawable.school_logo,"Shubham Chauhan (Ist)","Happy New Year",R.drawable.banner),
        DummyPost(R.drawable.school_logo,"Shubham Chauhan (Ist)","Happy New Year",R.drawable.banner),
        DummyPost(R.drawable.school_logo,"Shubham Chauhan (Ist)","Happy New Year",R.drawable.banner),
        DummyPost(R.drawable.school_logo,"Shubham Chauhan (Ist)","Happy New Year",R.drawable.banner),
    )
}