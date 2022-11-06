package com.ibrahimcanerdogan.tddandroidapp

data class Post(
    val id: String,
    val title: String,
    val body: String,
    val image: Int = R.drawable.post_image
)
