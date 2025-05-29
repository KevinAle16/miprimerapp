package com.example.kaftapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import androidx.appcompat.app.AlertDialog
import kotlin.math.abs

class CotizacionActivity : AppCompatActivity() {

    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPorcentaje: EditText
    private lateinit var txtPrecio: EditText

    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton

    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cotizacion)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

        @SuppressLint("SetTextI18n")
        fun iniciarComponentes() {
            txtCliente = findViewById(R.id.txtCliente) as TextView
            txtFolio = findViewById(R.id.txtFolio) as TextView
            txtDescripcion = findViewById(R.id.txtDescripcion) as EditText
            txtPorcentaje = findViewById(R.id.txtPorcentaje)  as EditText
            txtPrecio = findViewById(R.id.txtPrecio) as EditText

            rdb12 = findViewById(R.id.rdb12) as RadioButton
            rdb24 = findViewById(R.id.rdb24) as RadioButton
            rdb36 = findViewById(R.id.rdb36) as RadioButton
            rdb48 = findViewById(R.id.rdb48) as RadioButton

            txtPagoInicial = findViewById(R.id.txtPagoInicial)as TextView
            txtTotalFin = findViewById(R.id.txtTotalFin)as TextView
            txtPagoMensual = findViewById(R.id.txtPagoMensual)as TextView

            btnCalcular = findViewById(R.id.btnCalcular)as Button
            btnLimpiar = findViewById(R.id.btnLimpiar)as Button
            btnCerrar = findViewById(R.id.btnCerrar)as Button

            var strCliente: String = intent.getStringExtra("cliente").toString()
            txtCliente.text = strCliente.toString()

            var folio = abs(Cotizacion().generaFolio())
            txtFolio.text = "Folio: $folio"
        }

        @SuppressLint("SetTextI18n")
        fun eventosClic() {
            btnCalcular.setOnClickListener (View.OnClickListener {
                val cotizacion = Cotizacion()

                if (txtDescripcion.text.toString().contentEquals("") || txtPrecio.text.toString().contentEquals("") || txtPorcentaje.text.toString().contentEquals("")) {
                    Toast.makeText(this, "Falta capturar algún dato", Toast.LENGTH_SHORT).show()
                } else {
                    txtFolio.text = cotizacion.generaFolio().toString()
                    cotizacion.descripcion = txtDescripcion.text.toString()
                    cotizacion.precio = txtPrecio.text.toString().toFloat()
                    cotizacion.porPagInicial = txtPorcentaje.text.toString().toFloat()

                    if(rdb12.isChecked) cotizacion.plazos = 12
                    if(rdb24.isChecked) cotizacion.plazos = 24
                    if(rdb36.isChecked) cotizacion.plazos = 36
                    if(rdb48.isChecked) cotizacion.plazos= 48

                    }

                    txtPagoInicial.text = getString(R.string.pinicial) + ": " + cotizacion.calcularPagoInicial().toString() + ":"
                    txtTotalFin.text = getString(R.string.tfin)+": " + cotizacion.calcularTotalFin().toString()
                    txtPagoMensual.text = getString(R.string.pmensual)+ ": " + cotizacion.calcularPagoMensual().toString()
                })


            btnLimpiar.setOnClickListener {
                txtPagoInicial.text = getString(R.string.pinicial)
                txtTotalFin.text = getString(R.string.tfin)
                txtPagoMensual.text = getString(R.string.pmensual)
                txtDescripcion.setText("")
                txtPrecio.setText("")
                txtPorcentaje.setText("")
                rdb12.isChecked = true

                val folio = abs(Cotizacion().generaFolio())
                txtFolio.text = "Folio: $folio"
            }

            btnCerrar.setOnClickListener {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle(getString(R.string.Cliente_app))
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