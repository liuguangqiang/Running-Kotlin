package com.liuguangqiang.irunning.act.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.liuguangqiang.irunning.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    @Inject lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        DaggerLoginComponent.builder().loginModule(LoginModule(this)).build().inject(this)
    }

    @OnClick(R.id.btn_login)
    fun login() {
        var username = etUsername.text.toString()
        var password = etPassword.text.toString()
        presenter.login(username, password)
    }

    override fun onLoginSuccess() {
        toast("login success")
    }

}
