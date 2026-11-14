package com.luisotaviodias.proffy.utils

import android.util.Patterns

object EmailValidator {
    fun isValidEmail(email: String): Boolean {
        val trimmed = email.trim()
        return trimmed.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(trimmed).matches()
    }
}
