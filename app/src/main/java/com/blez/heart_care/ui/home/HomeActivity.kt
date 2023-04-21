package com.blez.heart_care.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blez.heart_care.R
import com.blez.heart_care.databinding.ActivityHomeBinding
import com.blez.heart_care.util.CredentialManager
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

     lateinit var credentialManager: CredentialManager
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        credentialManager = CredentialManager(this)
        binding.nameText.text = "Hello, ${credentialManager.getUsername()}"




    }
}