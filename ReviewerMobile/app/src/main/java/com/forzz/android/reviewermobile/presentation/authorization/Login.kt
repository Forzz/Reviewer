package com.forzz.android.reviewermobile.presentation.authorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.forzz.android.reviewermobile.databinding.ActivityLoginBinding
import com.forzz.android.reviewermobile.presentation.main_screen.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login : AppCompatActivity() {

    private lateinit var activityLoginBinding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        if (viewModel.isTokenExists()) {
            Log.d("TOKEN", "Token exists")
        } else {
            Log.d("TOKEN", "Token not exists")
        }

        activityLoginBinding.buttonSignIn.setOnClickListener {
            Log.d("BTN", "Clicked SIGN IN")
            doLogin()
        }

        viewModel.isError.observe(this, Observer { isError ->
            if (isError) {
                Toast.makeText(this, "Invalid login or password", Toast.LENGTH_LONG).show()
            }
        })

        viewModel.isLoad.observe(this, Observer { isLoad ->
            if (isLoad) {
                startActivity(Intent(this, MainActivity::class.java))
            }
        })

        viewModel.tokenFromResponse.observe(this, Observer { token ->
            if (token != null) {
                viewModel.saveTokenToPreferences(token)
            }
        })

        if (viewModel.getTokenFromPreferences() == null) {
            setContentView(activityLoginBinding.root)
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun doLogin() {
        val login = activityLoginBinding.edittextPersonName.text.toString()
        val password = activityLoginBinding.passwordEdittext.text.toString()
        viewModel.saveData(login, password)
        viewModel.performLogin(login, password)
    }
}