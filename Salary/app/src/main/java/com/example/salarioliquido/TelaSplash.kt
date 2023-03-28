package com.example.salarioliquido

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class TelaSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_splash)

        val tempoTelaSplash: Long = 4000;

        Handler().postDelayed({
            val telaPrincipal = Intent(this@TelaSplash, MainActivity::class.java); // Activity Atual, Proxima Activity
            startActivity(telaPrincipal) // Iniciar proxima Activity
            finish() // Fechar telaSplash
        }, tempoTelaSplash)
    } // Fim onCreate
} // Fim TelaSplash