package com.example.kaftapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

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
public fun iniciarComponentes() {
    txtCliente = findViewById(R.id.txtCliente)
    btnIngresar = findViewById(R.id.btnEntrar)
    btnRegresar = findViewById(R.id.btnRegresar)
}

public fun eventosClic() {
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
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.login_app))
        dialog.setMessage(getString(R.string.confirmar_cierre))
        dialog.setPositiveButton(getString(R.string.aceptar)) { _, _ ->
            finish()
        }
        dialog.setNegativeButton(getString(R.string.cancelar)) { _, _ ->
            Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}}
