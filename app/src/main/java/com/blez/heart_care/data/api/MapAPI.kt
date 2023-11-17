package com.blez.heart_care.data.api

import com.blez.heart_care.data.model.Location
import com.blez.heart_care.data.model.NearByModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MapAPI {
    @GET("/maps/api/place/nearbysearch/json")
    suspend fun fetchDoctor(@Query("keyword") keyword : String,@Query("location") location: String,@Query("radius") radius : String,@Query("key") key : String) : Response<NearByModel>
}