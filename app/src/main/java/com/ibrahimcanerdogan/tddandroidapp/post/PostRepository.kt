package com.ibrahimcanerdogan.tddandroidapp.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class PostRepository @Inject constructor(
    private val service: PostService
) {

    fun getPosts(): Flow<Result<List<Post>>> {
        return service.fetchPosts()
    }

}
