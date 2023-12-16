package com.edilson.huaman.poketinder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edilson.huaman.poketinder.data.FirebaseRemoteConfigRepository

class SplashViewModel: ViewModel() {

    private var firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()

    init {
        firebaseRemoteConfigRepository.int()
    }

    fun getIsUnderMaintenace(): MutableLiveData<Boolean> {
        return firebaseRemoteConfigRepository.isUnderMaintenanceLiveData
    }
}

