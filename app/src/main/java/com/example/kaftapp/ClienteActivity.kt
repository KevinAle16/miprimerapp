package com.example.kaftapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ClienteActivity : AppCompatActivity() {

    private lateinit var txtCliente: EditText
    private lateinit var btnIngresar: Button
    private lateinit var btnRegresar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cliente)
        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
private fun iniciarComponentes() {
    txtCliente = findViewById(R.id.txtCliente)
    btnIngresar = findViewById(R.id.btnIngresar)
    btnRegresar = findViewById(R.id.btnRegresar)
}

private fun eventosClic() {
    btnIngresar.setOnClickListener {
        if (txtCliente.text.toString().trim().isEmpty()) {
            Toast.makeText(this, "Falta capturar el nombre del cliente", Toast.LENGTH_SHORT).show()
            txtCliente.requestFocus()
        } else {
            val intent = Intent(this, CotizacionActivity::class.java)
            intent.putExtra("cliente", txtCliente.text.toString())
            startActivity(intent)
        }
    }

    btnRegresar.setOnClickListener {
        finish()
    }
}}
