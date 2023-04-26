package com.blez.heart_care.ui.home

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.blez.heart_care.MainActivity
import com.blez.heart_care.databinding.ActivityHomeBinding
import com.blez.heart_care.ui.main.MainViewModel
import com.blez.heart_care.ui.query.QuestionFragment
import com.blez.heart_care.ui.result.ResultFragment
import com.blez.heart_care.util.CredentialManager
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

                        ResultFragment()
                            .apply {
                            show(supportFragmentManager, "TAG")
                        }


                        mainViewModel.setOnLoadingSetupEvent()
                    }

                    is MainViewModel.SetupEvent.LoadingState -> {
                        Toast.makeText(this@HomeActivity, "Loading! Please wait", Toast.LENGTH_SHORT).show()

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