package com.example.vix_schoters_taufikdito.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.vix_schoters_taufikdito.R
import com.example.vix_schoters_taufikdito.ui.home.HomeActivity

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(applicationContext, HomeActivity::class.java))
            finish()
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }, 4000)
    }
}