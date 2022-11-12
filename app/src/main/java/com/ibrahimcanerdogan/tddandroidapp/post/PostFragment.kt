package com.ibrahimcanerdogan.tddandroidapp.post

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.ibrahimcanerdogan.tddandroidapp.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A fragment representing a list of Items.
 */
class PostFragment : Fragment() {

    private lateinit var viewModel: PostViewModel
    private lateinit var viewModelFactory: PostViewModelFactory

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:3000/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(PostAPI::class.java)

    private val service = PostService(api)
    private val repository: PostRepository = PostRepository(service)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_posts, container, false)
        setupViewModel()

        viewModel.posts.observe(this as LifecycleOwner) { posts ->
            if (posts.getOrNull() != null) setupList(view, posts.getOrNull()!!)
        }

        return view
    }

    private fun setupList(
        view: View?,
        posts: List<Post>
    ) {
        with(view as RecyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyPostRecyclerViewAdapter(posts)
        }
    }

    private fun setupViewModel() {
        viewModelFactory = PostViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]
    }

    companion object {

        @JvmStatic
        fun newInstance() = PostFragment().apply {}
    }
}