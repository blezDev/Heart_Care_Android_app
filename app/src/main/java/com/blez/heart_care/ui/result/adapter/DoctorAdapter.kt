package com.blez.heart_care.ui.result.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blez.heart_care.data.model.DoctorName
import com.blez.heart_care.databinding.DoctorLayoutBinding

class DoctorAdapter(private val doctorData : List<DoctorName>,private val context: Context) : RecyclerView.Adapter<DoctorAdapter.ItemView>()  {
    private lateinit var binding: DoctorLayoutBinding

    inner class ItemView(binding : DoctorLayoutBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemView {
         binding = DoctorLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemView(binding)
    }

    override fun onBindViewHolder(holder: ItemView, position: Int) {

          with(doctorData[position]){
              binding.doctorName.text = this.name
              binding.hospitalName.text= this.hospitalName
          }
        binding.doctorName.setOnClickListener {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https:\\\\www.google.com/search?q=${doctorData[position].name}+and+${doctorData[position].hospitalName}"))
            context.startActivity(intent)
            doctorData[position]
        }


    }

    override fun getItemCount(): Int {
      return doctorData.size
    }
}