package com.blez.heart_care

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.blez.heart_care.data.model.HeartInput
import com.blez.heart_care.databinding.ActivityMainBinding
import com.blez.heart_care.ui.home.HomeActivity
import com.blez.heart_care.util.CredentialManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
/*    private lateinit var googleSigninClient : GoogleSignInClient*/
    private lateinit var alert: androidx.appcompat.app.AlertDialog

    @Inject
    lateinit var tokenManager: CredentialManager

/*


    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)
        }
        else{

            Log.e("TAG",result.toString())
            alert.dismiss()
        }
    }


*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.getStartedBTN.setOnClickListener {
            CredentialManager(this).saveUsername("USER")
            startActivity(Intent(this,HomeActivity::class.java))
            finish()
        }
/*        val locale = resources.configuration.locale
        val countryCode = locale.country

        var colorsResource: String? = null
        colorsResource = if (countryCode == "US") {
            "colors-us"
        } else if (countryCode == "IN") {
            "colors-in"
        } else {
            "colors"
        }
        val color = resources.getColor("$colorsResource:colorPrimary")*/


//        val appLocale: LocaleListCompat = LocaleListCompat.getDefault()
//        AppCompatDelegate.setApplicationLocales(appLocale)


        }









/*





        auth = FirebaseAuth.getInstance()
        if (!tokenManager.getUsername().isNullOrEmpty()) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        val gson = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSigninClient = GoogleSignIn.getClient(this,gson)
        alert = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Process is going on!!")
            .setMessage("Please Wait for few moments.")
            .setCancelable(true)
            .create()




        val data = HeartInput(
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


        binding.getStartedBTN.setOnClickListener {
            alert.show()

            sigInGoogle()
        }

    }


    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }

    }
    private fun sigInGoogle(){
        val siginInIntent = googleSigninClient.signInIntent
        launcher.launch(siginInIntent)

    }
    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken,null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful) {

                account.givenName?.let { it-> tokenManager.saveUsername(it) }
                account.photoUrl?.let { it->tokenManager.savePic(it.toString()) }
                tokenManager.saveUsername(account.email?.replace("@gmail.com","")?.trim()!!)
                tokenManager.saveToken(account.idToken.toString())
                Log.e("TAG",tokenManager.getPic().toString())
                alert.dismiss()
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()



            }else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                Log.e("TAG",it.exception.toString())
                alert.dismiss()
            }
        }

    }*/

   /* private fun subscribeToUI() {
        lifecycleScope.launch(Dispatchers.Main) {
            mainViewModel.heartStatus.collect { events ->
                when (events) {
                    is MainViewModel.SetupEvent.HeartData -> {
                        Toast.makeText(
                            this@MainActivity,
                            events.output.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e("TAG", events.output.message.toString())
                    }

                    else -> Unit
                }
            }
        }
    }*/
}