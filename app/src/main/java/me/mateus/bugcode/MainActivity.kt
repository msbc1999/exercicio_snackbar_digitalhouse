package me.mateus.bugcode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.mateus.bugcode.core.BugCodeCore

class MainActivity : AppCompatActivity() {

    private lateinit var core: BugCodeCore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        core = BugCodeCore.fromIntent(intent)
        // TODO CRIAR A TELA DO APP

        if (core.contaAtual == null) {
            BugCodeCore.showSnackbar(layPrincipal, "Ocorreu um problema na integração!")
        } else {
            BugCodeCore.showSnackbar(layPrincipal, "Olá, ${core.contaAtual?.usuario}")
        }
    }
}