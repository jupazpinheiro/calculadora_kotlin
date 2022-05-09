package com.example.infnetimccalculadora2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pesoText = findViewById<EditText>(R.id.etPeso)
        val cinturaText = findViewById<EditText>(R.id.etCintura)
        val calculaBtn = findViewById<Button>(R.id.btnCalcula)

        calculaBtn.setOnClickListener{
            val peso = pesoText.text.toString()
            val cintura = cinturaText.text.toString()
            if(validacao(peso,cintura)){
                val imc = peso.toFloat()/((cintura.toFloat()/100)*(cintura.toFloat()/100))
                val imcfinal = String.format("%.2f",imc).toFloat()
                resultadoFinal(imcfinal)
            }
        }
    }
    private fun validacao(peso:String?,cintura:String?):Boolean{
        return when{
            peso.isNullOrEmpty() ->{
                Toast.makeText(this, "Digite um peso válido", Toast.LENGTH_LONG).show()
                return false
            }
            cintura.isNullOrEmpty() ->{
                Toast.makeText(this, "Digite uma cintura válida", Toast.LENGTH_LONG).show()
                return false
            }
            else ->
                return true
        }
    }

    private fun resultadoFinal (imc:Float){
        val resultadoIndex = findViewById<TextView>(R.id.tvIndex)
        val resultadoDescricao = findViewById<TextView>(R.id.tvResultado)
        val resultadoInfo = findViewById<TextView>(R.id.tvInfo)

        resultadoIndex.text = imc.toString()
        resultadoInfo.text = "Normal: 18,5-24,9 kg/m"

        var resultadoFinal = ""
        var cor = 0

        when {
            imc<18.50 ->{
                resultadoFinal = "Baixo Peso"
                cor = R.color.baixoPeso
            }
            imc in 18.50..24.99 ->{
                resultadoFinal = "Saudável"
                cor = R.color.normal
            }
            imc in 25.00..29.99 ->{
                resultadoFinal = "Acima do Peso"
                cor = R.color.acimaPeso
            }
            imc>29.99 ->{
                resultadoFinal = "Obeso"
                cor = R.color.obeso
            }
        }
        resultadoDescricao.setTextColor(ContextCompat.getColor(this,cor))
        resultadoDescricao.text = resultadoFinal
    }
}