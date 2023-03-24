package com.example.calculadoralinear

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Vinculacao de componentes
        val btn0 = findViewById<Button>(R.id.btn0)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn2 = findViewById<Button>(R.id.btn2)
        val btn3 = findViewById<Button>(R.id.btn3)
        val btn4 = findViewById<Button>(R.id.btn4)
        val btn5 = findViewById<Button>(R.id.btn5)
        val btn6 = findViewById<Button>(R.id.btn6)
        val btn7 = findViewById<Button>(R.id.btn7)
        val btn8 = findViewById<Button>(R.id.btn8)
        val btn9 = findViewById<Button>(R.id.btn9)
        val btnSomar = findViewById<Button>(R.id.btnSomar)
        val btnSubtrair = findViewById<Button>(R.id.btnSubtrair)
        val btnDividir = findViewById<Button>(R.id.btnDividir)
        val btnMultiplicar = findViewById<Button>(R.id.btnMultiplicar)
        val btnIgual = findViewById<Button>(R.id.btnIgual)
        val btnLimpar = findViewById<Button>(R.id.btnLimpar)
        val btnPonto = findViewById<Button>(R.id.btnPonto)
        val tvCalculo = findViewById<TextView>(R.id.tvCalculo)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btnApagar = findViewById<ImageButton>(R.id.btnApagar)
        var operacao:String = ""
        var operando1:Double = 0.00
        var operando2:Double = 0.00
        var resultado:Double = 0.00

        fun populaCampo(valor: String) {
            val result = tvResultado.text.toString()
            val calc = tvCalculo.text.toString()

            if (calc != "" && result == "" && operacao == ""){
                tvResultado.setText(calc + valor)
                tvCalculo.setText(calc + valor)
            } else {
                tvResultado.setText(result + valor)
                tvCalculo.setText(calc + valor)
            }

        }

        fun setaOperacao(opera: String) {
            if (operacao == "") {
                if (tvResultado.text.isNotEmpty()) {
                    operacao = opera

                    val calc = tvCalculo.text.toString()
                    tvCalculo.setText(calc + opera)

                    operando1 = tvResultado.text.toString().toDouble()

                    tvResultado.setText("")
                    btnIgual.isClickable = true

                    if (btnLimpar.isEnabled == false) {
                        btnLimpar.isEnabled = true;
                    }
                } else if (tvCalculo.text.isNotEmpty()) {
                    operacao = opera

                    operando1 = tvCalculo.text.toString().toDouble()

                    val calc = tvCalculo.text.toString()
                    tvCalculo.setText(calc + opera)

                    btnIgual.isClickable = true

                    if (btnLimpar.isEnabled == false) {
                        btnLimpar.isEnabled = true;
                    }
                } else {
                    val msgErro: String = "Informe o primeiro operando da operação."
                    Toast.makeText(applicationContext,msgErro, Toast.LENGTH_LONG).show()
                }
            } else {
                val msgErro: String = "A operação já foi informada."
                Toast.makeText(applicationContext,msgErro, Toast.LENGTH_LONG).show()
            }
        }

        btn0.setOnClickListener(){
            populaCampo("0");
        }

        btn1.setOnClickListener(){
            populaCampo("1");
        }

        btn2.setOnClickListener(){
            populaCampo("2");
        }

        btn3.setOnClickListener(){
            populaCampo("3");
        }

        btn4.setOnClickListener(){
            populaCampo("4");
        }

        btn5.setOnClickListener(){
            populaCampo("5");
        }

        btn6.setOnClickListener(){
            populaCampo("6");
        }

        btn7.setOnClickListener(){
            populaCampo("7");
        }

        btn8.setOnClickListener(){
            populaCampo("8");
        }

        btn9.setOnClickListener(){
            populaCampo("9");
        }

        btnLimpar.setOnClickListener() {
            tvResultado.setText("")
            tvCalculo.setText("")

            btnLimpar.isEnabled  = false
            btnIgual.isClickable = false

            operando1 = 0.00
            operando2 = 0.00
            resultado = 0.00
            operacao  = ""
        }

        btnPonto.setOnClickListener(){
            val numero = tvResultado.text.toString()
            val ponto = "."
            val exists = ponto in numero
            if (exists == false && numero != ""){
                populaCampo(".")
            }
        }

        btnSomar.setOnClickListener() {
            setaOperacao("+")
        }

        btnSubtrair.setOnClickListener() {
            setaOperacao("-")
        }

        btnMultiplicar.setOnClickListener() {
            setaOperacao("x")
        }

        btnDividir.setOnClickListener() {
            setaOperacao("/")
        }

        btnIgual.setOnClickListener() {
            if (operacao != "") {
                if (tvResultado.text.isNotEmpty()) {
                    operando2 = tvResultado.text.toString().toDouble()

                    val calc = tvCalculo.text.toString()
                    tvCalculo.setText(calc + " = ")

                    if (operacao == "+") {
                        resultado = operando1 + operando2
                    } else if (operacao == "-") {
                        resultado = operando1 - operando2
                    } else if (operacao == "x") {
                        resultado = operando1 * operando2
                    } else if (operacao == "/") {
                        resultado = operando1 / operando2
                    }

                    tvResultado.setText("%.3f".format(resultado))

                    btnIgual.isClickable = false
                } else {
                    val msgErro: String = "Informe os dois operandos para o cálculo."
                    Toast.makeText(applicationContext,msgErro, Toast.LENGTH_LONG).show()
                }
            } else {
                val msgErro: String = "Informe o cálculo a ser realizado."
                Toast.makeText(applicationContext,msgErro, Toast.LENGTH_LONG).show()
            }
        }

        btnApagar.setOnClickListener() {
            if (operando2 == 0.00){
                val numero = tvResultado.text.toString()
                val calculo = tvCalculo.text.toString()

                if (numero.length > 0){

                    tvResultado.setText(numero.substring(0, numero.length - 1))
                    tvCalculo.setText(calculo.substring(0, calculo.length - 1))

                } else {

                    if (calculo.length > 0){

                        tvCalculo.setText(calculo.substring(0, calculo.length - 1))
                        operacao = ""

                        btnIgual.isClickable = false

                        if (btnLimpar.isEnabled == true) {
                            btnLimpar.isEnabled = false;
                        }
                    }

                }

            }
        }

    } // Fim MainActivity
} // Fim onCreate