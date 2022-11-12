package com.ibrahimcanerdogan.tddandroidapp.post

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

class PostService(
    private val postAPI: PostAPI
){

    fun fetchPosts(): Flow<Result<List<Post>>> {
        return flow {
            // List<Post> to Flow<Result<List<Post>>>
            emit(Result.success(postAPI.fetchAllPosts()))
        }.catch {
            emit(Result.failure(RuntimeException("Wrong!")))
        }
        // catch ile hatalar yakalanır!
    }

}
