package com.ibrahimcanerdogan.tddandroidapp.post

import com.ibrahimcanerdogan.tddandroidapp.utils.BaseUnitTest
import com.ibrahimcanerdogan.tddandroidapp.post.Post
import com.ibrahimcanerdogan.tddandroidapp.post.PostRepository
import com.ibrahimcanerdogan.tddandroidapp.post.PostViewModel
import com.ibrahimcanerdogan.tddandroidapp.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.times
import java.lang.RuntimeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PostViewModelShould : BaseUnitTest() {

    //private val viewModel: PostViewModel
    private val repository: PostRepository = mock()
    private val posts = mock<List<Post>>()
    private val expected = Result.success(posts)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPostFromRepository() = runTest {
        val viewModel = postViewModel()

        viewModel.posts.getValueForTest()

        verify(repository, times(1)).getPosts()
    }

    @Test
    fun emitsPlaylistsFromReposityory() = runTest {
        val viewModel = postViewModel()

        assertEquals(expected, viewModel.posts.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiverError() {
        runBlocking {
            whenever(repository.getPosts()).thenReturn(
                flow {
                    emit(Result.failure<List<Post>>(exception))
                }
            )
        }

        val viewModel = PostViewModel(repository)
        assertEquals(exception, viewModel.posts.getValueForTest()!!.exceptionOrNull())
    }
    private fun postViewModel(): PostViewModel {
        runBlocking {
            whenever(repository.getPosts()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        val viewModel = PostViewModel(repository)
        return viewModel
    }
}