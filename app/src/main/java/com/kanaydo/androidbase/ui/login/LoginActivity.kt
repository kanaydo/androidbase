package com.kanaydo.androidbase.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kanaydo.androidbase.R
import com.kanaydo.androidbase.utils.LoadingDialog
import com.kanaydo.androidbase.utils.snackBar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var owner: LifecycleOwner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init view
        setContentView(R.layout.activity_login)

        // init dep
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val loadingDialog = LoadingDialog(this)
        owner = this


        // view actions
        btnLogin.setOnClickListener {
            val username = etUserName.text.toString()
            val password = etPassword.text.toString()
            if (username == "" || password == "") {
                showMessage(getString(R.string.alert_empty_input))
            } else {
                viewModel.validateLogin(username, password)
            }

        }

        // view model observer
        with(viewModel) {
            message().observe(owner, Observer { message ->
                showMessage(message)
            })

            loading().observe(owner, Observer { isLoading ->
                loadingDialog.show(isLoading)
            })
        }
    }

    /* start ui method */
    private fun showMessage(message: String) = root_layout.snackBar(message)
    /* end ui method */

}