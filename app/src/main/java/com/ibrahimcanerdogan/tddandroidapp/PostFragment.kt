package com.ibrahimcanerdogan.tddandroidapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView.Recycler

/**
 * A fragment representing a list of Items.
 */
class PostFragment : Fragment() {

    lateinit var viewModel: PostViewModel
    lateinit var viewModelFactory: PostViewModelFactory

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
        viewModelFactory = PostViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory)[PostViewModel::class.java]
    }

    companion object {

        @JvmStatic
        fun newInstance() = PostFragment().apply {}
    }
}