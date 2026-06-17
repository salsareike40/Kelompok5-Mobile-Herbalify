package com.example.herbalify

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnDaftar = findViewById<TextView>(R.id.btnDaftar)

        btnDaftar.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        btnLogin.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            RetrofitClient.instance.getUsers()
                .enqueue(object : Callback<List<User>> {

                    override fun onResponse(
                        call: Call<List<User>>,
                        response: Response<List<User>>
                    ) {

                        val users = response.body()

                        val user = users?.find {
                            it.email == email &&
                                    it.password == password
                        }

                        if (user != null) {

                            Toast.makeText(
                                this@LoginActivity,
                                "Login berhasil",
                                Toast.LENGTH_SHORT
                            ).show()

                            startActivity(
                                Intent(
                                    this@LoginActivity,
                                    MainActivity::class.java
                                )
                            )

                            finish()

                        } else {

                            Toast.makeText(
                                this@LoginActivity,
                                "Email atau password salah",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(
                        call: Call<List<User>>,
                        t: Throwable
                    ) {
                        Toast.makeText(
                            this@LoginActivity,
                            t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }
}