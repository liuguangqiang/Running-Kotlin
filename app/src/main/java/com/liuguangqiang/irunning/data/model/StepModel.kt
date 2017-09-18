package com.liuguangqiang.irunning.data.model

import com.liuguangqiang.irunning.data.entity.CreateResponse
import com.liuguangqiang.irunning.data.service.StepService
import com.liuguangqiang.irunning.extension.observeOnMainThread
import com.liuguangqiang.kotlindemo.domian.RetrofitClient
import rx.Observable
import javax.inject.Inject

/**
 * Created by eric on 29/8/2017.
 */
class StepModel : StepService {

    val service: StepService = RetrofitClient.instance.create(StepService::class.java)

    @Inject constructor()

    override fun post(userId: String, count: Int, date: String): Observable<CreateResponse> {
        return service.post(userId, count, date).observeOnMainThread()
    }
}