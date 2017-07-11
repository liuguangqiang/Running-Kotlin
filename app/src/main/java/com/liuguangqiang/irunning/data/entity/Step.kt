package com.liuguangqiang.irunning.data.entity

import io.realm.RealmObject

/**
 * Created by Eric on 2017/5/19.
 */
open class Step : RealmObject() {

    var count = 0

    /**
     * 201704016
     */
    var date: String? = null

}
