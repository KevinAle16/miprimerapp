package com.example.kaftapp

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

class MainActivity : AppCompatActivity() {
    //declaracin de objetos
    private lateinit var lblSaludo : TextView
    private lateinit var txtNombre : EditText
    private lateinit var btnSaludar : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnCerrar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fun IniciarComponentes() {
            //relacionar los componentes del layout con los objetos de kotlin
            lblSaludo = findViewById(R.id.lblSaludar) as TextView
            txtNombre = findViewById(R.id.txtNombre) as EditText
            btnCerrar = findViewById(R.id.btnCerrar)
            btnLimpiar = findViewById(R.id.btnLimpiar)
            btnSaludar = findViewById(R.id.btnSaludo)
        }

        IniciarComponentes()


        fun eventosBotones() {
            btnSaludar.setOnClickListener(View.OnClickListener {
                //declarar variables
                var strNombre: String = "";
                if (txtNombre.text.toString().contentEquals("")) {
                    Toast.makeText(
                        applicationContext,
                        "Falto capturar el nombre",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    strNombre = "Hola" + txtNombre.text.toString() + "Como estas?"
                    //asignar nombre al editText
                    lblSaludo.text = strNombre
                }
            })
            btnLimpiar.setOnClickListener(View.OnClickListener {
                txtNombre.setText("")
                lblSaludo.setText("")
            })
            btnCerrar.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("App Hola")
                builder.setMessage("¿Desea Cerrar la aplicacion?")

                builder.setPositiveButton("Aceptar"){dialog, which ->
                    finish()
                }
                builder.setNeutralButton("cancelar"){ dialog, which ->
                Toast.makeText(applicationContext, "cancelar", Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Quiza"){ dialog, which ->
                    Toast.makeText(applicationContext, "Quiza", Toast.LENGTH_SHORT).show()
                }
                builder.show()
            })
        }

    eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}