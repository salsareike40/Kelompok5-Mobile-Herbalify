package com.example.herbalify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val btnMulai = findViewById<Button>(R.id.btnMulai)
        val txtMasuk = findViewById<TextView>(R.id.txtDaftar)

        // tombol mulai -> login
        btnMulai.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // daftar akun -> register
        txtMasuk.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}