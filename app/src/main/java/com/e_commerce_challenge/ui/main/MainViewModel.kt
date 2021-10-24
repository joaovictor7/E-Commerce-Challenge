package com.e_commerce_challenge.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val mLogin = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = mLogin

    fun login(username: String, password: String) {
        mLogin.value = username.trim() == "master" && password.trim() == "master"
    }
}