package com.rakaadinugroho.kotlinrxjava.network

import com.rakaadinugroho.kotlinrxjava.models.Base
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Raka on 11/26/17.
 */

interface RequestInterface{
    @GET("news")
    fun getNews(@Query("page") page: String,
                @Query("per_page") perpage: String,
                @Query("order_published_at") order: String) : Observable<Base>
}