package com.blez.heart_care.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.blez.heart_care.MainActivity
import com.blez.heart_care.databinding.ActivityHomeBinding
import com.blez.heart_care.ui.main.MainViewModel
import com.blez.heart_care.ui.query.QuestionFragment
import com.blez.heart_care.ui.result.ResultActivity
import com.blez.heart_care.util.CredentialManager
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    lateinit var credentialManager: CredentialManager
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        credentialManager = CredentialManager(this)
        binding.nameText.text = "Hello, ${credentialManager.getUsername()}"
        binding.progressBar.isVisible = false
        binding.imageView.setOnClickListener {
            QuestionFragment().show(supportFragmentManager, "TAG")
            subscribeToUI(this)

        }
        binding.logoutImg.setOnClickListener {
            credentialManager.deteleCredit()
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun subscribeToUI(context: Context) {
        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.heartStatus.collect { events ->
                when (events) {
                    is MainViewModel.SetupEvent.HeartData -> {

                        Log.e("TAG", events.output.message.toString())
                        binding.progressBar.isVisible = false

                        val intent = Intent(this@HomeActivity,ResultActivity::class.java)
                        startActivity(intent)


                        mainViewModel.setHeartResult(events.output.message)
                        mainViewModel.setOnLoadingSetupEvent()
                    }

                    is MainViewModel.SetupEvent.LoadingState -> {
                    }

                    MainViewModel.SetupEvent.FailState -> {

                        binding.progressBar.visibility =View.INVISIBLE
                    }
                    MainViewModel.SetupEvent.NoEventState -> Unit
                }
            }
        }
    }
}