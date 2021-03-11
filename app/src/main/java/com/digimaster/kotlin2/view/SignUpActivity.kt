package com.digimaster.kotlin2.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.digimaster.kotlin2.R
import com.digimaster.kotlin2.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val username = et_username.text.toString()
        val email = et_email.text.toString()
        val address = et_address.text.toString()
        val dob = et_dob.text.toString()
        val password = et_password.text.toString()

        tv_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_signup.setOnClickListener {
            loginViewModel.register(username, email, address, dob, password)
        }
        setObserver()
    }

    private fun setObserver() {
        loginViewModel.getLoginResponseModel().observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "Berhasil Registrasi, User id: " + it.userModel.userId, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        })

        loginViewModel.getErrorListener().observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Something is wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // private boolean login(String email, string password)
    private fun register(username: String, email: String, address: String, dob: String, password: String): Boolean{
        return false
    }
}