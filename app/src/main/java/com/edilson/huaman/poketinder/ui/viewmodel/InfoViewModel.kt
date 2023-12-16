package com.edilson.huaman.poketinder.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.edilson.huaman.poketinder.data.FirebaseRemoteConfigRepository

class InfoViewModel: ViewModel()  {

  private val firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()

  init {
      firebaseRemoteConfigRepository.int()
  }

 fun getUrlPokemon(): MutableLiveData<String> {
  return firebaseRemoteConfigRepository.getUrlPokemonLiveData
 }
}