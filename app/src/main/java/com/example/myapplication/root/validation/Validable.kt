package com.example.myapplication.root.validation

import io.reactivex.Single

interface Validable {
    fun validate(): Single<Boolean>
}