package com.luisotaviodias.proffy.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luisotaviodias.proffy.R
import com.luisotaviodias.proffy.databinding.ActivityLoginBinding
import com.luisotaviodias.proffy.ui.forgot.ForgotPasswordActivity
import com.luisotaviodias.proffy.ui.register.RegisterStepOneActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonRegister = binding.buttonRegister
        val buttonForgot = binding.buttonForgot

        buttonRegister.setOnClickListener {
            handleRegister()
        }

        buttonForgot.setOnClickListener {
            handleForgot()
        }
    }

    private fun handleRegister() {
        val intent = Intent(this, RegisterStepOneActivity::class.java)
        startActivity(intent)
    }

    private fun handleForgot() {
        val intent = Intent(this, ForgotPasswordActivity::class.java)
        startActivity(intent)
    }
}