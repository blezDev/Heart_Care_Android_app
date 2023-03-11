package com.blez.heart_care.repository

import com.blez.heart_care.data.api.HeartAPI
import com.blez.heart_care.data.model.HeartInput
import javax.inject.Inject

class HeartRepository(val heartAPI: HeartAPI){
    suspend fun getPrediction(data : HeartInput) = heartAPI.getPrediction(data)
}