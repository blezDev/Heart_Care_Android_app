package com.blez.heart_care.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.blez.heart_care.R
import com.blez.heart_care.data.model.DoctorName
import com.blez.heart_care.databinding.FragmentResultBinding
import com.blez.heart_care.ui.main.MainViewModel
import com.blez.heart_care.ui.result.adapter.DoctorAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ResultFragment : BottomSheetDialogFragment() {
private lateinit var binding : FragmentResultBinding
    private lateinit var mainViewModel : MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        val result = mainViewModel.getHeartResult()
        if (result == "no"){
            binding.resultText.text = "No worries. You’re doing great! If you want, you can schedule an appointment with a doctor."
        }else{
            binding.resultText.text = "It would be a good idea to schedule an appointment with a doctor."
        }
        val doctors = listOf(
            DoctorName("Dr. Naresh Trehan","Medanta – The Medicity, Gurgaon"),
            DoctorName("Subhash Chandra","Subhash Chandra"),
            DoctorName("Dr. S.K Sinha", "Max Super Specialty Hospital, New Delhi"),
            DoctorName("Dr. Murtaza A. Chishti","Artemis Hospital, Gurgaon"),
            DoctorName("Dr. Ajay Kaul", "BLK Super Speciality Hospital, New Delhi")
        )
        binding.doctorRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        binding.doctorRecyclerview.adapter = DoctorAdapter(doctors,requireActivity())



    }
    override fun getTheme(): Int {
        return R.style.DialogStyle;
    }
}