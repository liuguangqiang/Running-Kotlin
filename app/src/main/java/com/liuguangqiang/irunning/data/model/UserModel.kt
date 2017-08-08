package com.liuguangqiang.irunning.data.model

import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.irunning.data.service.UserService
import com.liuguangqiang.kotlindemo.domian.RetrofitClient
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eric on 2017/7/11.
 */
class UserModel : UserService {

    var service: UserService

    @Inject
    constructor()

    init {
        service = RetrofitClient.instance.create(UserService::class.java)
    }

    override fun get(userId: String): Observable<User> {
        return service.get(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


}