package com.ibrahimcanerdogan.tddandroidapp.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class PostRepository(
    private val service: PostService
) {

    fun getPosts(): Flow<Result<List<Post>>> {
        return service.fetchPosts()
    }

}
