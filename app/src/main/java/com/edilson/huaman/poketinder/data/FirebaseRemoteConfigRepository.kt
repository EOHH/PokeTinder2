package com.edilson.huaman.poketinder.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.get
import com.edilson.huaman.poketinder.R

class FirebaseRemoteConfigRepository {

    val instance = FirebaseRemoteConfig.getInstance()

    val getUrlPokemonLiveData: MutableLiveData<String> = MutableLiveData()
    val isUnderMaintenanceLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun int() {
        instance.setDefaultsAsync(R.xml.remote_config_defaults)
        instance.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                getUrlPokemonLiveData.value = getUrlPokemonLive()
                isUnderMaintenanceLiveData.value = getIsUdnerMainttenace()
            }
        }
    }

    private fun getUrlPokemonLive(): String {
        return instance["url_pokemon_live"].asString()
    }

    private fun getIsUdnerMainttenace(): Boolean {
        return instance["is_under_maintance"].asBoolean()
    }
}