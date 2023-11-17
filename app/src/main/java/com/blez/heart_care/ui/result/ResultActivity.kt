package com.blez.heart_care.ui.result

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blez.heart_care.R
import com.blez.heart_care.data.api.MapAPI
import com.blez.heart_care.data.model.DoctorName
import com.blez.heart_care.databinding.ActivityResultBinding
import com.blez.heart_care.ui.main.MainViewModel
import com.blez.heart_care.ui.result.adapter.DoctorAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@AndroidEntryPoint
class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    private lateinit var mainViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val result = mainViewModel.getHeartResult()

        if (result == "no"){
            binding.resultText.text = "No worries. You’re doing great! If you want, you can schedule an appointment with a doctor."
        }else{
            binding.resultText.text = "It would be a good idea to schedule an appointment with a doctor."
        }
     /*   val doctors = listOf(
            DoctorName("Dr. Naresh Trehan","Medanta – The Medicity, Gurgaon"),
            DoctorName("Subhash Chandra","Subhash Chandra"),
            DoctorName("Dr. S.K Sinha", "Max Super Specialty Hospital, New Delhi"),
            DoctorName("Dr. Murtaza A. Chishti","Artemis Hospital, Gurgaon"),
            DoctorName("Dr. Ajay Kaul", "BLK Super Speciality Hospital, New Delhi")
        )
        */
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maps.googleapis.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MapAPI::class.java)

        lifecycleScope.launch {
            val doctors = retrofit.fetchDoctor("heart doctor","19.047547, 83.830891","1500000","AIzaSyDpAa4Nn0bVkXCAhM0Ai_-zOjw-l6sRHtQ").body()?.results
            binding.doctorRecyclerview.layoutManager = LinearLayoutManager(this@ResultActivity)
            val doctorsData = ArrayList<DoctorName>()
            doctors?.forEach {
                val doctor = DoctorName(it.name,it.vicinity)
                doctorsData.add(doctor)
            }
            if (doctorsData.size >1){
                doctorsData.removeLast()
            }
            binding.doctorRecyclerview.adapter = DoctorAdapter(doctorsData.toList(),this@ResultActivity)

        }

    }


}