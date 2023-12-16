package com.edilson.huaman.poketinder.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.edilson.huaman.poketinder.databinding.ActivitySplashBinding
import com.edilson.huaman.poketinder.ui.viewmodel.SplashViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Log.e("FIREBASE TOKEN", "Token: $token")
            })

        splashViewModel.getIsUnderMaintenace().observe(this) { isUnderMaintenance ->
            if (isUnderMaintenance) {
                Toast.makeText(this, "Bajo mantenimiento", Toast.LENGTH_SHORT).show()
            } else {
                showAnimation()
            }
        }
        FirebaseMessaging.getInstance().subscribeToTopic("Poketinder-1")
    }

    private fun showAnimation() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }, 3000)
    }
}