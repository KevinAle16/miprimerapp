package com.example.kaftapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityIMC : AppCompatActivity() {

    private lateinit var txtPeso: EditText
    private lateinit var txtAltura: EditText
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var lblResultado: TextView
    private lateinit var txtEncabezado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_imc)

    fun iniciarComponentess() {
        txtPeso = findViewById(R.id.txtPeso) as EditText
        txtAltura = findViewById(R.id.txtAltura) as EditText
        txtEncabezado = findViewById(R.id.txtEncabezado) as TextView
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnCerrar = findViewById(R.id.btnCerrar)
        lblResultado = findViewById(R.id.lblResultado) as TextView
    }
    @SuppressLint("DefaultLocale")
    fun eventosBotoness() {
        btnCalcular.setOnClickListener(View.OnClickListener {
            val peso = txtPeso.text.toString().toFloatOrNull()
            val altura = txtAltura.text.toString().toFloatOrNull()

            if (peso == null || altura == null || altura == 0f) {
                Toast.makeText(this, "Falta ingresar valores", Toast.LENGTH_SHORT).show()
            } else {
                val imc = peso / (altura * altura)
                val resultado = String.format("Tu IMC es: %.2f", imc)
                lblResultado.text = resultado
                }
        })

            btnLimpiar.setOnClickListener(View.OnClickListener{
                txtPeso.setText("")
                txtAltura.setText("")
                lblResultado.text = (getString(R.string.mensaje_resultado))
            })

            btnCerrar.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("App IMC")
                builder.setMessage("¿Desea Cerrar la aplicacion?")

                builder.setPositiveButton("Aceptar"){_, _ ->
                    finish()
                }
                builder.setNeutralButton("cancelar"){ _, _ ->
                    Toast.makeText(applicationContext, "cancelado", Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Quiza"){ _, _ ->
                    Toast.makeText(applicationContext, "Quiza", Toast.LENGTH_SHORT).show()
                }
                builder.show()
            })
        }

        iniciarComponentess()
        eventosBotoness()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    }
