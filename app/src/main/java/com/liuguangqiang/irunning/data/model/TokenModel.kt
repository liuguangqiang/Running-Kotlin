package com.liuguangqiang.irunning.data.model

import com.liuguangqiang.irunning.data.entity.Token
import com.liuguangqiang.irunning.data.service.TokenService
import com.liuguangqiang.kotlindemo.domian.RetrofitClient
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eric on 2017/7/11.
 */
class TokenModel : TokenService {

    var service: TokenService

    @Inject
    constructor()

    init {
        service = RetrofitClient.instance.create(TokenService::class.java)
    }

    override fun post(username: String, password: String): Observable<Token> {
        return service.post(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}