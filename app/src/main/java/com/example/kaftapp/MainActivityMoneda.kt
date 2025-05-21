package com.example.kaftapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.annotation.SuppressLint
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivityMoneda : AppCompatActivity() {

    private lateinit var txtValor: EditText
    private lateinit var spinnerMonedaOrigen: Spinner
    private lateinit var spinnerMonedaDestino: Spinner
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var lblResultado: TextView

    private val monedas = listOf(
        "USD", "EUR", "JPY", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD",
        "MXN", "INR", "BRL", "ZAR", "RUB", "KRW", "TRY", "SAR", "NGN", "IDR"
    )

    private val tasas = mapOf(
        "USD" to 1.0,
        "EUR" to 0.91,
        "JPY" to 134.20,
        "GBP" to 0.77,
        "AUD" to 1.48,
        "CAD" to 1.34,
        "CHF" to 0.91,
        "CNY" to 7.21,
        "SEK" to 10.40,
        "NZD" to 1.61,
        "MXN" to 19.29,
        "INR" to 82.50,
        "BRL" to 4.96,
        "ZAR" to 18.00,
        "RUB" to 77.50,
        "KRW" to 1333.00,
        "TRY" to 23.68,
        "SAR" to 3.75,
        "NGN" to 780.00,
        "IDR" to 15300.00
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_moneda)

        iniciarComponentes()
        configurarSpinners()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        }
        fun iniciarComponentes() {
            txtValor = findViewById(R.id.txtValor)
            spinnerMonedaOrigen = findViewById(R.id.spinnerMonedaOrigen)
            spinnerMonedaDestino = findViewById(R.id.spinnerMonedaDestino)
            btnCalcular = findViewById(R.id.btnCalcular)
            btnLimpiar = findViewById(R.id.btnLimpiar)
            btnCerrar = findViewById(R.id.btnCerrar)
            lblResultado = findViewById(R.id.lblResultado)
        }


        fun configurarSpinners() {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerMonedaOrigen.adapter = adapter
            spinnerMonedaDestino.adapter = adapter

            spinnerMonedaOrigen.setSelection(monedas.indexOf("USD"))
            spinnerMonedaDestino.setSelection(monedas.indexOf("EUR"))
        }

        fun convertirMoneda(valor: Double, origen: String, destino: String): Double {
            val tasaOrigen = tasas[origen] ?: 1.0
            val tasaDestino = tasas[destino] ?: 1.0
            val valorEnUSD = valor / tasaOrigen
            return valorEnUSD * tasaDestino
        }

        @SuppressLint("DefaultLocale", "SetTextI18n")
        fun eventosBotones() {
            btnCalcular.setOnClickListener {
                val valorStr = txtValor.text.toString()
                val origen = spinnerMonedaOrigen.selectedItem.toString()
                val destino = spinnerMonedaDestino.selectedItem.toString()

                val valor = valorStr.toDoubleOrNull()
                if (valor == null) {
                    Toast.makeText(this, "Por favor ingrese un valor válido", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }

                if (origen == destino) {
                    lblResultado.text = "La moneda origen y destino no pueden ser iguales."
                    return@setOnClickListener
                }

                val resultado = convertirMoneda(valor, origen, destino)
                lblResultado.text = String.format("Resultado: %.2f %s", resultado, destino)
            }

            btnLimpiar.setOnClickListener {
                txtValor.setText("")
                spinnerMonedaOrigen.setSelection(monedas.indexOf("USD"))
                spinnerMonedaDestino.setSelection(monedas.indexOf("EUR"))
                lblResultado.text = "El resultado aparecerá aquí"
            }
            btnCerrar.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("App IMC")
                builder.setMessage("¿Desea Cerrar la aplicacion?")

                builder.setPositiveButton("Aceptar") { _, _ ->
                    finish()
                }
                builder.setNeutralButton("cancelar") { _, _ ->
                    Toast.makeText(applicationContext, "cancelado", Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Quiza") { _, _ ->
                    Toast.makeText(applicationContext, "Quiza", Toast.LENGTH_SHORT).show()
                }
                builder.show()
            }



    }
}