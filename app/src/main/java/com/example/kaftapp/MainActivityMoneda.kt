package com.example.kaftapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.annotation.SuppressLint
import android.view.View
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

    private lateinit var monedas: Array<String>
    private var posOrigen = 0
    private var posDestino = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_moneda)

        iniciarComponentes()
        eventoClick()

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

            monedas = resources.getStringArray(R.array.monedas)

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, monedas)
            spinnerMonedaOrigen.adapter = adapter
            spinnerMonedaDestino.adapter = adapter
        }


    @SuppressLint("DiscouragedApi")
    fun eventoClick() {
        spinnerMonedaOrigen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                posOrigen = position
                Toast.makeText(applicationContext, "Origen: ${monedas[position]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinnerMonedaDestino.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                posDestino = position
                Toast.makeText(applicationContext, "Destino: ${monedas[position]}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnCalcular.setOnClickListener {
            val cantidadStr = txtValor.text.toString()
            if (cantidadStr.isEmpty()) {
                Toast.makeText(this, getString(R.string.error_falta_cantidad), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cantidad = cantidadStr.toFloat()
            val origen = monedas[posOrigen].lowercase()
            val destino = monedas[posDestino].lowercase()

            val tasaOrigen = resources.getString(
                resources.getIdentifier(origen, "string", packageName)
            ).toFloat()

            val tasaDestino = resources.getString(
                resources.getIdentifier(destino, "string", packageName)
            ).toFloat()

            val resultado = (cantidad / tasaOrigen) * tasaDestino
            lblResultado.text = getString(R.string.resultado_conversion, resultado, monedas[posDestino])
        }


        btnLimpiar.setOnClickListener {
                txtValor.setText("")
                spinnerMonedaOrigen.setSelection(monedas.indexOf("USD"))
                spinnerMonedaDestino.setSelection(monedas.indexOf("EUR"))
                lblResultado.text = "El resultado aparecerá aquí"
            }
        btnCerrar.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle(getString(R.string.app_monedas))
            dialog.setMessage(getString(R.string.confirmar_cierre))
            dialog.setPositiveButton(getString(R.string.aceptar)) { _, _ ->
                finish()
            }
            dialog.setNegativeButton(getString(R.string.cancelar)) { _, _ ->
                Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show()
            }
            dialog.show()
        }

    }
}