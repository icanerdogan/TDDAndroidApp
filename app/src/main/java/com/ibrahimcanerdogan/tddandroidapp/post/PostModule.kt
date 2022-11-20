package com.ibrahimcanerdogan.tddandroidapp.post

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(FragmentComponent::class)
class PostModule {

    @Provides
    fun postAPI(retrofit: Retrofit): PostAPI = retrofit.create(PostAPI::class.java)

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://127.0.0.1:3000/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}