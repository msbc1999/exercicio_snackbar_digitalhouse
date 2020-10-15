package me.mateus.bugcode

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.login.view.*
import me.mateus.bugcode.core.BugCodeCore


class LoginActivity : AppCompatActivity() {

    private lateinit var core: BugCodeCore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        core = BugCodeCore.fromIntent(intent)



        layLogin.lblCadastrar.setOnClickListener {
            abrirCadastro()
        }



        layLogin.imgFacebook.setOnClickListener {
            BugCodeCore.showSnackbar(layoutLoginSnack, "Realizar login usando Facebook")
        }
        layLogin.imgGooglePlus.setOnClickListener {
            BugCodeCore.showSnackbar(layoutLoginSnack, "Realizar login usando Google+")
        }
        layLogin.imgInstagram.setOnClickListener {
            BugCodeCore.showSnackbar(layoutLoginSnack, "Realizar login usando Instagram")
        }

        layLogin.btnLogin.setOnClickListener {
            verificarLogin()
        }

    }

    private fun verificarLogin() {
        val usuario = layLogin.txtUsuario.editText?.text.toString()
        val senha = layLogin.txtSenha.editText?.text.toString()

        if (!core.contemUsuario(usuario)) {
            BugCodeCore.showSnackbar(layoutLoginSnack,
                "Usuário não existe!",
                "Cadastrar-se",
                View.OnClickListener {
                    abrirCadastro()
                })
        } else {
            val acc = core.checkLoginValido(usuario, senha)
            if (acc != null) {
                core.contaAtual = acc
                abrirMain()
            } else {
                BugCodeCore.showSnackbar(layoutLoginSnack, "Senha inválida!")
            }
        }
    }


    private fun abrirCadastro() {
        startActivityForResult(
            core.createIntent(this, CadastroActivity::class.java),
            1
        )
    }

    private fun abrirMain() {
        startActivity(core.createIntent(this, MainActivity::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (data == null) return
            core = BugCodeCore.fromIntent(data)
        }
    }


}