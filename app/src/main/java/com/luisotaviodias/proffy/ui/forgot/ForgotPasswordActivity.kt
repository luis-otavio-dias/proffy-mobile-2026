package com.luisotaviodias.proffy.ui.forgot

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.luisotaviodias.proffy.R
import com.luisotaviodias.proffy.databinding.ActivityForgotPasswordBinding
import com.luisotaviodias.proffy.ui.finallyComponent.FinallyActivity
import com.luisotaviodias.proffy.utils.EmailValidator

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()
        setContentView(view)

        @Suppress("DEPRECATION")
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBarsAndIme = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            )
            v.setPadding(
                systemBarsAndIme.left,
                systemBarsAndIme.top,
                systemBarsAndIme.right,
                systemBarsAndIme.bottom
            )
            val imeVisible = insets.isVisible(WindowInsetsCompat.Type.ime())
            if (imeVisible) {
                binding.main.post {
                    binding.main.smoothScrollTo(0, binding.buttonSendEmail.bottom)
                }
            }
            insets
        }

        val buttonGoBack = binding.buttonGoBack
        val editTextEmail = binding.textInputEditEmail
        val buttonSendEmail = binding.buttonSendEmail

        val enabledButtonColor = ContextCompat.getColor(this, R.color.green)
        val disabledButtonColor = ContextCompat.getColor(this, R.color.shape_disable)

        setButtonState(false, enabledButtonColor, disabledButtonColor)

        buttonGoBack.setOnClickListener {
            goBack()
        }

        editTextEmail.addTextChangedListener {
            val email = editTextEmail.text?.toString()?.trim() ?: ""
            val isValid = validateEmailField(email, showEmptyError = false)
            setButtonState(isValid, enabledButtonColor, disabledButtonColor)
        }

        editTextEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val email = editTextEmail.text?.toString()?.trim() ?: ""
                val isValid = validateEmailField(email, showEmptyError = true)
                setButtonState(isValid, enabledButtonColor, disabledButtonColor)
            }
        }

        buttonSendEmail.setOnClickListener {
            val email = editTextEmail.text?.toString()?.trim() ?: ""
            if (validateEmailField(email, showEmptyError = true)) {
                handleSendEmail()
            } else {
                setButtonState(false, enabledButtonColor, disabledButtonColor)
            }
        }
    }

    private fun validateEmailField(email: String, showEmptyError: Boolean): Boolean {
        val textInputLayout = binding.textInputLayoutEmail
        val editTextEmail = binding.textInputEditEmail

        if (email.isEmpty()) {
            if (showEmptyError) {
                val errorMessage = getString(R.string.forgot_password_error_empty_email)
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = errorMessage
                textInputLayout.boxStrokeWidth = (2 * resources.displayMetrics.density).toInt()
                editTextEmail.error = errorMessage
            } else {
                clearEmailError()
            }
            return false
        }

        if (!EmailValidator.isValidEmail(email)) {
            val errorMessage = getString(R.string.forgot_password_error_invalid_email)
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = errorMessage
            textInputLayout.boxStrokeWidth = (2 * resources.displayMetrics.density).toInt()
            editTextEmail.error = errorMessage
            return false
        }

        clearEmailError()
        return true
    }

    private fun clearEmailError() {
        binding.textInputLayoutEmail.error = null
        binding.textInputLayoutEmail.isErrorEnabled = false
        binding.textInputLayoutEmail.boxStrokeWidth = 0
        binding.textInputEditEmail.error = null
    }

    private fun setButtonState(enabled: Boolean, enabledColor: Int, disabledColor: Int) {
        val buttonSendEmail = binding.buttonSendEmail
        buttonSendEmail.isEnabled = enabled

        val color = if (enabled) enabledColor else disabledColor
        buttonSendEmail.backgroundTintList = ColorStateList.valueOf(color)

        val textColor = if (enabled) {
            ContextCompat.getColor(this, R.color.shape_01_white)
        } else {
            ContextCompat.getColor(this, R.color.text_complement)
        }
        buttonSendEmail.setTextColor(textColor)

        buttonSendEmail.text = getString(
            if (enabled) R.string.forgot_password_button_send else R.string.forgot_password_button_initial
        )
    }

    private fun handleSendEmail() {
        val intent = Intent(this, FinallyActivity::class.java)
        intent.putExtra("textViewTitleFinally", getString(R.string.forgot_finally_title))
        intent.putExtra("textViewSubtitleFinally", getString(R.string.forgot_finally_subtitle))
        intent.putExtra("titleButton", getString(R.string.forgot_finally_button))
        startActivity(intent)
        finish()
    }

    private fun goBack() {
        finish()
    }
}
