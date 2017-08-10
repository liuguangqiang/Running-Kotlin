package com.liuguangqiang.irunning.act.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.act.main.MainActivity
import com.liuguangqiang.irunning.utils.LoginManager
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    lateinit var dialog: ProgressDialog

    @Inject lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        DaggerLoginComponent.builder().loginModule(LoginModule(this)).build().inject(this)

        if (LoginManager.instance.isLogin()) {
            onLoginSuccess()
        }
    }

    @OnClick(R.id.btn_login)
    fun login() {
        var username = etUsername.text.toString()
        var password = etPassword.text.toString()
        presenter.login(username, password)
    }

    override fun onLoginSuccess() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showLoading() {
        dialog = ProgressDialog(this)
        dialog.setMessage("Loading...")
        dialog.show()
    }

    override fun hideLoading() {
        dialog.hide()
    }

}
