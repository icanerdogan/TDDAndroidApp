package com.ibrahimcanerdogan.tddandroidapp.post

import com.ibrahimcanerdogan.tddandroidapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.lang.RuntimeException

class PostServiceShould: BaseUnitTest(){

    private lateinit var service: PostService
    // Eşitlenen sınıf mocklanıyor!
    private val api: PostAPI = mock()
    private val posts: List<Post> = mock()

    @Test
    fun fetchPlaylistFromAPI() = runTest {
        service = PostService(api)

        service.fetchPosts().first()

        verify(api, times(1)).fetchAllPosts()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem() = runTest {
        fetPostsSuccessCase()

        assertEquals(Result.success(posts), service.fetchPosts().first())
    }

    private fun fetPostsSuccessCase() {
        // Her zamaan bunu döndür!
        whenever(api.fetchAllPosts()).thenReturn(posts)

        service = PostService(api)
    }

    @Test
    fun emitsErrorResultWhenNetworkFails() = runTest {
        fetchPostsFailCase()

        assertEquals(
            "Wrong!",
            service.fetchPosts().first().exceptionOrNull()?.message
        )
    }

    private fun fetchPostsFailCase() {
        whenever(api.fetchAllPosts()).thenThrow(RuntimeException("Damn!"))

        service = PostService(api)
    }
}