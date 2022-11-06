package com.ibrahimcanerdogan.tddandroidapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {

    var posts = MutableLiveData<Result<List<Post>>>()

}
