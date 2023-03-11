package com.blez.heart_care.data.api

import com.blez.heart_care.data.model.HeartInput
import com.blez.heart_care.data.model.Status
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HeartAPI {

    @POST("/predict")
    suspend fun getPrediction(@Body data : HeartInput) : Response<Status>
}