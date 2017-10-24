package com.liuguangqiang.irunning.act.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import butterknife.OnClick
import com.liuguangqiang.irunning.R
import com.liuguangqiang.irunning.act.main.MainActivity
import com.liuguangqiang.irunning.data.entity.User
import com.liuguangqiang.irunning.extension.toast
import com.liuguangqiang.irunning.utils.LoginManager
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private var dialog: ProgressDialog? = null

    @Inject lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)
        DaggerLoginComponent.builder().loginModule(LoginModule(this)).build().inject(this)

        if (LoginManager.instance.isLogin()) {
            onLoginSuccess(LoginManager.instance.user!!)
        }
    }

    @OnClick(R.id.btn_login)
    fun login() {
        var username = etUsername.text.toString()
        var password = etPassword.text.toString()
        presenter.login(username, password)
    }

    override fun onLoginSuccess(user: User) {
        hideLoading()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onLoginFailed(t: Throwable) {
        hideLoading()
        toast(R.string.error_login)
    }

    override fun showLoading() {
        dialog = ProgressDialog(this)
        dialog?.setMessage(getString(R.string.loading))
        dialog?.show()
    }

    override fun hideLoading() {
        dialog?.hide()
    }

}
