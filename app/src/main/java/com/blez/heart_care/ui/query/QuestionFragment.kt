package com.blez.heart_care.ui.query

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.blez.heart_care.R
import com.blez.heart_care.data.model.HeartInput
import com.blez.heart_care.databinding.FragmentQuestionBinding
import com.blez.heart_care.ui.main.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentQuestionBinding
     private lateinit var mainViewModel : MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =FragmentQuestionBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val checkType = arrayOf("Typical angina","Atypical angina","Non — anginal pain","Asymptotic")
        val gender = arrayOf("Male","Female")
        val genderAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,gender)
        val ChestPainAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,checkType)
        binding.sex.adapter = genderAdapter
        binding.chestType.adapter = ChestPainAdapter

        binding.saveBTN.setOnClickListener {
            if (checkEditBox())
            {
                val data = HeartInput(
                    age = binding.age.text.toString(),
                    chest_pain_type = binding.chestType.selectedItemPosition.toString(),
                    cholesterol = binding.cholesterol.text.toString(),
                    exercise_induced_angina = binding.eInducedAngina.text.toString(),
                    fasting_blood_sugar = binding.bloodSugar.text.toString(),
                    max_heart_rate_achieved = binding.heartRate.text.toString(),
                    num_major_vessels = binding.majorVessel.text.toString(),
                    rest_ecg = binding.restEcg.text.toString(),
                    resting_blood_pressure = binding.restingBloodPressure.text.toString(),
                    sex = if (binding.sex.selectedItemPosition.toString() == "1") "0" else "1",
                    st_depression = binding.stDepression.text.toString(),
                    st_slope = binding.stSlope.text.toString(),
                    thalassemia = binding.thalassemia.text.toString()

                )
                mainViewModel.setHeartInput(data)
                dismissNow()
            }
            else{
                Toast.makeText(requireContext(), "Please fill all the forms", Toast.LENGTH_SHORT).show()
        }




        }

        binding.saveBTNDummy.setOnClickListener {
            val Sample_data = HeartInput(
                age = "20",
                chest_pain_type = "0",
                cholesterol = "212",
                exercise_induced_angina = "0",
                fasting_blood_sugar = "0",
                max_heart_rate_achieved = "168",
                num_major_vessels = "2",
                rest_ecg = "1",
                resting_blood_pressure = "125",
                sex = "1",
                st_depression = "1.0",
                st_slope = "2",
                thalassemia = "3"

            )
            mainViewModel.setHeartInput(Sample_data)
            dismissNow()

        }


    }
    private fun checkEditBox() : Boolean{
    val value =    if (binding.age.text.isNullOrEmpty()) false
        else if (binding.cholesterol.text.isNullOrEmpty()) false
        else if (binding.eInducedAngina.text.isNullOrEmpty()) false
        else if (binding.bloodSugar.text.isNullOrEmpty()) false
        else if (binding.heartRate.text.isNullOrEmpty()) false
        else if (binding.majorVessel.text.isNullOrEmpty()) false
        else if (binding.restEcg.text.isNullOrEmpty()) false
        else if (binding.restingBloodPressure.text.isNullOrEmpty()) false
        else if (binding.stDepression.text.isNullOrEmpty()) false
        else if (binding.stSlope.text.isNullOrEmpty()) false
        else if (binding.thalassemia.text.isNullOrEmpty()) false
        else true
        return value


    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            setCanceledOnTouchOutside(true)
        }

    override fun getTheme(): Int {
        return R.style.DialogStyle;
    }
}

