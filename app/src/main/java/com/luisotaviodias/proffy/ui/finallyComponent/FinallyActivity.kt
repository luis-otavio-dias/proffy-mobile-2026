package com.luisotaviodias.proffy.ui.finallyComponent

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.luisotaviodias.proffy.R
import com.luisotaviodias.proffy.databinding.ActivityFinallyBinding
import com.luisotaviodias.proffy.ui.login.LoginActivity

class FinallyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFinallyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinallyBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val buttonGoToLogin = binding.buttonGoToLogin
        val textViewTitle = binding.textViewTitleFinally
        val textViewSubtitle = binding.textViewSubtitle

        val title = intent.getStringExtra("textViewTitleFinally")
            ?: getString(R.string.forgot_finally_title)
        val subtitle = intent.getStringExtra("textViewSubtitleFinally")
            ?: getString(R.string.forgot_finally_subtitle)
        val titleButton = intent.getStringExtra("titleButton")
            ?: getString(R.string.forgot_finally_button)

        textViewTitle.text = title
        textViewSubtitle.text = subtitle
        buttonGoToLogin.text = titleButton

        buttonGoToLogin.setOnClickListener {
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}