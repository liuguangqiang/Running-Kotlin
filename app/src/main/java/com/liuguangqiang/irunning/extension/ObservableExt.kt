package com.liuguangqiang.irunning.extension

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by eric on 28/8/2017.
 */
fun <T> Observable<T>.observeOnMainThread(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}