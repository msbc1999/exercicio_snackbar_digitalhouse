package me.mateus.bugcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.mateus.bugcode.core.BugCodeCore

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bugCode = BugCodeCore()
        startActivity(bugCode.createIntent(this, LoginActivity::class.java))
        finish()
    }
}