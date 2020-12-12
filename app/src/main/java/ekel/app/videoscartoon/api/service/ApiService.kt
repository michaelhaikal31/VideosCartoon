package com.pmui.apps.api.service

import ekel.app.videoscartoon.model.mAllChanel
import ekel.app.videoscartoon.model.mDetailChanel
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*
import java.util.*

interface ApiService {

    @get:GET("/api/json/get/NJR6uIb3d")
    val allChanel: Observable<mAllChanel>

    @FormUrlEncoded
    @POST("/ci_rest/api/AllChanel/detail")
    fun getDataDetailChanel(@Field("id_chanel") id: String): Observable<mDetailChanel>

}
