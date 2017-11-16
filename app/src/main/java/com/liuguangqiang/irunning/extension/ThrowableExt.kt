package com.liuguangqiang.irunning.extension

import retrofit2.adapter.rxjava.HttpException

/**
 * Created by eric on 25/10/2017.
 */

fun Throwable.httpStatusCode(): Int {
    if (this is HttpException) {
        return this.code()
    }
    return 0
}
