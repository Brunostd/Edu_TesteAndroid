package com.deny.eduedu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.core.os.HandlerCompat
import androidx.core.os.HandlerCompat.postDelayed
import java.util.*
import kotlin.concurrent.schedule


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var intent: Intent = Intent(applicationContext, MainActivity::class.java)

        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        Timer().schedule(2000) {
            startActivity(intent)
            finish()
        }
    }
}