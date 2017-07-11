package com.liuguangqiang.irunning.data.service

import com.liuguangqiang.irunning.data.entity.Token
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Eric on 2017/7/11.
 */
interface TokenService {

    @FormUrlEncoded
    @POST("tokens")
    fun post(@Field("username") username: String,
             @Field("password") password: String): Observable<Token>

}