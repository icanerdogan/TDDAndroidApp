package com.ibrahimcanerdogan.tddandroidapp.post

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class PostViewModel(
    private val repository: PostRepository
) : ViewModel() {

    var posts = MutableLiveData<Result<List<Post>>>()

/*   val posts = liveData<Result<List<Post>>> {
        emitSource(repository.getPosts().asLiveData())
    }*/

    init {
        viewModelScope.launch {
            repository.getPosts()
                .collect {
                    posts.value = it
                }
        }
    }
}
