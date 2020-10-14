package me.mateus.snackbar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login.view.*


class MainActivity : AppCompatActivity() {

    lateinit var contas: MutableList<Conta>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        contas = mutableListOf()

        layLogin.lblCadastrar.setOnClickListener {
            cadastrarContaTeste()
        }

        layLogin.imgFacebook.setOnClickListener {
            showSnackbar("Realizar login usando Facebook")
        }
        layLogin.imgGooglePlus.setOnClickListener {
            showSnackbar("Realizar login usando Google+")
        }
        layLogin.imgInstagram.setOnClickListener {
            showSnackbar("Realizar login usando Instagram")
        }

        layLogin.btnLogin.setOnClickListener {
            verificarLogin()
        }

    }

    private fun verificarLogin() {
        val usuario = layLogin.txtUsuario.editText?.text.toString()
        val senha = layLogin.txtSenha.editText?.text.toString()

        if (!contemUsuario(usuario)) {
            showSnackbar(
                "Usuário não existe!",
                "Cadastrar-se",
                View.OnClickListener {
                    cadastrarContaTeste()
                })
        } else if (checkLogin(usuario, senha)) {
            showSnackbar("Login realizado com sucesso!")
        } else {
            showSnackbar("Senha inválida!")
        }
    }

    private fun showSnackbar(mensagem: String) {
        Snackbar.make(layoutSnack, mensagem, Snackbar.LENGTH_LONG).show()
    }

    private fun showSnackbar(mensagem: String, botao: String, action: View.OnClickListener) {
        Snackbar.make(layoutSnack, mensagem, Snackbar.LENGTH_LONG).setAction(botao, action).show()
    }

    private fun checkLogin(usuario: String, senha: String): Boolean {
        return contas.find {
            it.usuario.equals(usuario, true) && it.senha == senha
        } != null
    }

    private fun contemUsuario(usuario: String): Boolean {
        return contas.find {
            it.usuario.equals(usuario, true)
        } != null
    }

    private fun cadastrarContaTeste() {
        if (contemUsuario("Teste")) {
            showSnackbar("Conta Teste/1234 já existe!")
        } else {
            contas.add(Conta("Teste", "1234"))
            showSnackbar("Conta Teste/1234 criada com sucesso!")
        }
    }


    class Conta(var usuario: String, var senha: String)
}