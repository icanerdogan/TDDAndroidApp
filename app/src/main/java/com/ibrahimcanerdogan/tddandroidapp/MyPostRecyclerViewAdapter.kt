package com.ibrahimcanerdogan.tddandroidapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ibrahimcanerdogan.tddandroidapp.databinding.PostsItemBinding


class MyPostRecyclerViewAdapter(
    private val values: List<Post>
) : RecyclerView.Adapter<MyPostRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(PostsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        holder.postTitle.text = item.title
        holder.postBody.text = item.body
        holder.postImage.setImageResource(item.image)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: PostsItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val postTitle: TextView = binding.postTitle
        val postBody: TextView = binding.postBody
        val postImage: ImageView = binding.postImage

    }

}