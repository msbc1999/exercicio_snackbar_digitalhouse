package me.mateus.mywallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import me.mateus.mywallet.core.MyWalletCore

class MainActivity : AppCompatActivity() {

    private lateinit var core: MyWalletCore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        core = MyWalletCore.fromIntent(intent)
        if (core.contaAtual == null) {
            MyWalletCore.showSnackbar(layPrincipal, "Ocorreu um problema na integração!")
        } else {
            MyWalletCore.showSnackbar(layPrincipal, "Olá, ${core.contaAtual?.usuario}")
        }

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(HomeFragment(), "Home")
        tabAdapter.addFragment(GastosFragment(), "Gastos")

        pager.adapter = tabAdapter

        tabLayout.setupWithViewPager(pager)


    }
}