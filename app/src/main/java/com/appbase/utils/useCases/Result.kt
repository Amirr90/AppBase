package com.appbase.utils.useCases

data class Result(
    val success: Boolean,
    val error: String? = null,
)