package com.digimaster.kotlin2.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.digimaster.kotlin2.R
import com.digimaster.kotlin2.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = et_email.text.toString()
        val password = et_password.text.toString()

        tv_skip.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        tv_signup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_login.setOnClickListener{
            loginViewModel.login(email, password)
        }
        setObserver()
    }

    // Bocah yg nerima bola
    private fun setObserver() {
        loginViewModel.getLoginResponseModel().observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "User Id " + it.userModel.userId, Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
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
    private fun login(email: String, password: String): Boolean{
        return false
    }
}