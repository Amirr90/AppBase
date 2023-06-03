package com.appbase.utils.useCases

import java.util.regex.Pattern

fun String.isValidEmail(): Boolean {
    val emailPattern = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    val pattern = Pattern.compile(emailPattern)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.validateEmail() = ValidateEmail().invoke(this)
fun String.validatePassword() = ValidatePassword().invoke(this)
