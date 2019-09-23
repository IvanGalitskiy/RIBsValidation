package com.example.myapplication.root.validation

import io.reactivex.Single

interface ValidationListener {
    fun onValidationReady(validationSingle: Single<Boolean>)
}