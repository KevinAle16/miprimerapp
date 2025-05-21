package com.example.kaftapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    private lateinit var crvHola : CardView
    private lateinit var crvImc : CardView
    private lateinit var crvConversion : CardView
    private lateinit var crvMoneda : CardView
    private lateinit var crvCotizacion : CardView
    private lateinit var crvSpinner : CardView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_maincard)

        //llamar funciones
        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
     fun iniciarComponentes(){
        crvHola = findViewById(R.id.crvHola) as CardView
        crvImc = findViewById(R.id.crvImc) as CardView
        crvCotizacion = findViewById(R.id.crvCotizacion) as CardView
        crvConversion = findViewById(R.id.crvConversion) as CardView
        crvSpinner = findViewById(R.id.crvSpinner) as CardView
        crvMoneda = findViewById(R.id.crvMoneda) as CardView
    }
    fun eventosClic(){
        crvHola.setOnClickListener(View.OnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        })
        crvImc.setOnClickListener(View.OnClickListener{
            val intent = Intent(this,MainActivityIMC::class.java)
            startActivity(intent)
        })
        crvConversion.setOnClickListener(View.OnClickListener{
            val intent = Intent(this,MainActivityTemperature::class.java)
            startActivity(intent)
        })
        crvMoneda.setOnClickListener(View.OnClickListener{
            val intent = Intent(this,MainActivityMoneda::class.java)
            startActivity(intent)
        })

    }
}