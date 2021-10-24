package com.e_commerce_challenge.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.e_commerce_challenge.databinding.ActivityMainBinding
import com.e_commerce_challenge.ui.product.ProductActivity
import com.e_commerce_challenge.ui.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        listeners()
        observers()
    }

    override fun onClick(v: View) {
        when (v) {
            mBinding.buttonLogin -> {
                if (loginValidate()) {
                    mViewModel.login(
                        mBinding.editUsername.text.toString(),
                        mBinding.editPassword.text.toString()
                    )
                }
            }
        }
    }

    private fun listeners() {
        mBinding.buttonLogin.setOnClickListener(this)
    }

    private fun observers() {
        mViewModel.login.observe(this, {
            if (it) {
                startActivity(Intent(this, ProductActivity::class.java))
                finish()
            } else {
                showToast("Usuário ou senha inválidos.")
            }
        })
    }

    private fun loginValidate(): Boolean {
        var valid = true

        if (mBinding.editUsername.text.toString().isBlank()) {
            valid = false
            mBinding.inputUsername.error = "Obrigatório"
        }

        if (mBinding.editPassword.text.toString().isBlank()) {
            valid = false
            mBinding.inputPassword.error = "Obrigatório"
        }

        return valid
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}