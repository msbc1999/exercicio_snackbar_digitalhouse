package me.mateus.mywallet

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cadastro.*
import kotlinx.android.synthetic.main.cadastro.view.*
import me.mateus.mywallet.core.MyWalletCore

class CadastroActivity : AppCompatActivity() {

    private lateinit var core: MyWalletCore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        core = MyWalletCore.fromIntent(intent)


        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        layCadastro.btnCadastrar.setOnClickListener {
            validarForm()
        }

    }


    private fun validarForm() {
        val usuario = layCadastro.txtUsuario.editText?.text.toString()
        val email = layCadastro.txtEmail.editText?.text.toString()
        val senha = layCadastro.txtSenha.editText?.text.toString()
        val repetirSenha = layCadastro.txtRepetirSenha.editText?.text.toString()

        if (senha != repetirSenha) {
            MyWalletCore.showSnackbar(
                layoutCadastroSnack,
                "Repita a senha corretamente"
            )
            return
        }
        if (core.contemUsuario(usuario)) {
            MyWalletCore.showSnackbar(
                layoutCadastroSnack,
                "Usuário já em uso"
            )
            return
        }
        if (core.contemEmail(email)) {
            MyWalletCore.showSnackbar(
                layoutCadastroSnack,
                "Email já em uso"
            )
            return
        }
        try {
            core.cadastrarConta(usuario, email, senha)
            updateResult()
            MyWalletCore.showSnackbar(
                layoutCadastroSnack,
                "Conta criada com sucesso!"
                , "Voltar", View.OnClickListener {
                    finish()
                })
        } catch (ex: Exception) {
            MyWalletCore.showSnackbar(
                layoutCadastroSnack,
                "Erro ao cadastrar a conta!"
            )
            return
        }


    }

    private fun updateResult() {
        setResult(Activity.RESULT_OK, core.createIntent())
    }

}