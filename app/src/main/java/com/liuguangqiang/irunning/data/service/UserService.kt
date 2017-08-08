package com.liuguangqiang.irunning.data.service

import com.liuguangqiang.irunning.data.entity.User
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 * Created by eric on 8/8/2017.
 */
interface UserService {

    @GET("/users/{user_id}")
    fun get(@Path("user_id") userId: String): Observable<User>

}