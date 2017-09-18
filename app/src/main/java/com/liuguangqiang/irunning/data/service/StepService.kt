package com.liuguangqiang.irunning.data.service

import com.liuguangqiang.irunning.data.entity.CreateResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Eric on 2017/7/11.
 */
interface StepService {

    @FormUrlEncoded
    @POST("steps")
    fun post(@Field("userId") userId: String,
             @Field("count") count: Int,
             @Field("date") date: String): Observable<CreateResponse>

}