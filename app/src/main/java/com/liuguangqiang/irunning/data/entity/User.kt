package com.liuguangqiang.irunning.data.entity

import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by Eric on 2017/7/11.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
class User {

    lateinit var id: String

    lateinit var username: String

    var age: Int = 0

    var weight: Int = 0

    var height: Int = 0
}