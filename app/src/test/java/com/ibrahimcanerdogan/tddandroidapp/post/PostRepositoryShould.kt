package com.ibrahimcanerdogan.tddandroidapp.post

import com.ibrahimcanerdogan.tddandroidapp.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.lang.RuntimeException

class PostRepositoryShould: BaseUnitTest() {
    private val service : PostService = mock()
    private val posts = mock<List<Post>>()
    private val exception = RuntimeException("Somethinf went wrong...")
    @Test
    fun getsPlaylistsFromService() = runTest {
        val repository = PostRepository(service)

        repository.getPosts()
        verify(service, times(1)).fetchPosts()
    }

    @Test
    fun emitPlaylistsFromService() = runTest {

        whenever(service.fetchPosts()).thenReturn(
            flow {
                emit(Result.success(posts))
            }
        )

        val repository = PostRepository(service)
        assertEquals(posts, repository.getPosts().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runTest {
        whenever(service.fetchPosts()).thenReturn(
            flow {
                emit(Result.failure<List<Post>>(exception))
            }
        )

        val repository = PostRepository(service)
        assertEquals(exception, repository.getPosts().first().exceptionOrNull())
    }
}