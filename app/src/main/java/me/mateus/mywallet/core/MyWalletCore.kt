package me.mateus.mywallet.core

import android.content.Context
import android.content.Intent
import android.view.View
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable
import java.lang.IllegalArgumentException

class MyWalletCore : Serializable {

    val contas: MutableList<Conta> = mutableListOf() // APENAS PARA TESTES
    var contaAtual: Conta? = null


    fun cadastrarConta(usuario: String, email: String, senha: String): Conta {
        if (contemUsuario(usuario)) throw IllegalArgumentException("Usuário já cadastrado")
        if (contemEmail(email)) throw IllegalArgumentException("Email já cadastrado")

        val acc = Conta(usuario, email, senha)
        contas.add(acc)
        return acc
    }

    fun checkLoginValido(usuario: String, senha: String): Conta? {
        return contas.find {
            it.usuario.equals(usuario, true) && it.senha == senha
        }
    }

    fun contemUsuario(usuario: String): Boolean {
        return contas.find {
            it.usuario.equals(usuario, true)
        } != null
    }

    fun contemEmail(email: String): Boolean {
        return contas.find {
            it.email.equals(email, true)
        } != null
    }

    fun createIntent(context: Context, destino: Class<*>): Intent {
        val i = Intent(context, destino)
        i.putExtra("core", this)
        return i
    }

    fun createIntent(): Intent {
        val i = Intent()
        i.putExtra("core", this)
        return i
    }


    companion object Static {
        fun fromIntent(intent: Intent): MyWalletCore {
            val b = intent.extras;
            if (b == null) throw IllegalArgumentException("A intent informada não possui extras")
            if (!b.containsKey("core")) throw IllegalArgumentException("A intent informada não possui uma instancia do MyWalletCore")
            return b.getSerializable("core") as MyWalletCore
        }

        fun showSnackbar(view: View, mensagem: String) {
            Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG).show()
        }

        fun showSnackbar(
            view: View,
            mensagem: String,
            botao: String,
            action: View.OnClickListener
        ) {
            Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG).setAction(botao, action).show()
        }
    }

}

data class Conta(var usuario: String, var email: String, var senha: String) : Serializable