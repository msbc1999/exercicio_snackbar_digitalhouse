package me.mateus.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import me.mateus.mywallet.core.MyWalletCore

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bugCode = MyWalletCore()
        startActivity(bugCode.createIntent(this, LoginActivity::class.java))
        finish()
    }
}