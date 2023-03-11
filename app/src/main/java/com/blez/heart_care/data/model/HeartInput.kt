package com.blez.heart_care.data.model


data class HeartInput(
    val age: String,
    val chest_pain_type: String,
    val cholesterol: String,
    val exercise_induced_angina: String,
    val fasting_blood_sugar: String,
    val max_heart_rate_achieved: String,
    val num_major_vessels: String,
    val rest_ecg: String,
    val resting_blood_pressure: String,
    val sex: String,
    val st_depression: String,
    val st_slope: String,
    val thalassemia: String
)

data class Status(
    val message : String
)