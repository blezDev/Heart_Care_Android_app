package com.blez.heart_care.repository

import com.blez.heart_care.data.api.HeartAPI
import com.blez.heart_care.data.model.HeartInput
import com.blez.heart_care.data.model.Status
import retrofit2.Response
import javax.inject.Inject

class HeartRepository(val heartAPI: HeartAPI){
    suspend fun getPrediction(data : HeartInput): Response<Status> {
        try {
           return heartAPI.getPrediction(data)
        }catch (e : Exception){
            throw IllegalAccessError("Network Error")
        }
    }

}