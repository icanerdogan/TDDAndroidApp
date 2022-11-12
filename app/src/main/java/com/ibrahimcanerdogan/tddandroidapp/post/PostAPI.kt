package com.ibrahimcanerdogan.tddandroidapp.post

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface PostAPI {

    @GET("posts")
    fun fetchAllPosts(): List<Post>
}