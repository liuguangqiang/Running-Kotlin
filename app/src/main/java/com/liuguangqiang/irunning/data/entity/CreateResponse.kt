package com.liuguangqiang.irunning.data.entity

import com.bluelinelabs.logansquare.annotation.JsonObject

/**
 * Created by Eric on 2017/7/11.
 */
@JsonObject(fieldDetectionPolicy = JsonObject.FieldDetectionPolicy.NONPRIVATE_FIELDS)
class CreateResponse {

    lateinit var id: String
}