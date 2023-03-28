package com.example.salarioliquido

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Componentes
        val wSpDeps =findViewById<Spinner>(R.id.wSpDeps)
        val wEdtBruto = findViewById<EditText>(R.id.wEdtBruto)
        val wBtnCalcula = findViewById<Button>(R.id.wBtnCalcula)
        val wBtnLimpar = findViewById<ImageButton>(R.id.wBtnLimpar)
        val wTvResultInss = findViewById<TextView>(R.id.wTvResultInss)
        val wTvResultIrrf = findViewById<TextView>(R.id.wTvResultIrrf)
        val wTvResultDesc = findViewById<TextView>(R.id.wTvResultDesc)
        val wTvResultLiquido = findViewById<TextView>(R.id.wTvResultLiquido)

        // Acessar lista de dependentes
        val deps = resources.getStringArray(R.array.deps_array)

        val adapterDados = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, deps)
        wSpDeps.adapter = adapterDados

        wBtnLimpar.setOnClickListener {
            wTvResultInss.text = ""
            wTvResultIrrf.text = ""
            wTvResultDesc.text = ""
            wTvResultLiquido.text = ""
            wEdtBruto.text.clear()
            wSpDeps.setSelection(0)
        } // Fim Botao Limpar

        wBtnCalcula.setOnClickListener {
            if (wEdtBruto.text.isEmpty()) {
                val msgErro: String = "Informe o salário bruto para o cálculo."
                Toast.makeText(applicationContext, msgErro, Toast.LENGTH_LONG).show()
            } else {
                val salarioBruto = wEdtBruto.text.toString().toDouble()

                if (salarioBruto < 1302 || salarioBruto > 500000) {
                    val msgErro: String = "Valor inválido! Salário Bruto deve estar entre R$1302 e R$500000."
                    Toast.makeText(applicationContext, msgErro, Toast.LENGTH_LONG).show()
                } else {
                    // Cálculo INSS
                    var descInss: Double = 97.65

                    if (salarioBruto >= 1302.01 && salarioBruto <= 2571.29) {
                        descInss += (salarioBruto - 1302) * 0.09
                    } else if (salarioBruto >= 2571.30 && salarioBruto <= 3856.94) {
                        descInss += 114.23 + ((salarioBruto - 2571.29) * 0.12)
                    } else if (salarioBruto >= 3856.95 && salarioBruto <= 7507.49) {
                        descInss += 114.23 + 154.27 + ((salarioBruto - 3856.94) * 0.14)
                    } else {
                        if (salarioBruto != 1302.00) {
                            descInss = 877.22
                        }
                    }

                    wTvResultInss.text = "R$" + "%.2f".format(descInss)

                    // Cálculo Dependentes
                    var descDeps: Double = wSpDeps.selectedItem.toString().toDouble() * 189.59
                    // Cálculo Salário Base
                    var salarioBase = salarioBruto - descInss - descDeps

                    // Cálculo IRRF
                    var descIrrf: Double = 0.00

                    if (salarioBase >= 1903.99 && salarioBase <= 2826.65) {
                        descIrrf = (salarioBase * 0.075) - 142.80
                    } else if (salarioBase > 2826.65 && salarioBase <= 3751.05) {
                        descIrrf = (salarioBase * 0.15) - 354.80
                    } else if (salarioBase > 3751.05 && salarioBase <= 4664.68) {
                        descIrrf = (salarioBase * 0.225) - 636.13
                    } else if (salarioBase > 4664.68) {
                        descIrrf = (salarioBase * 0.275) - 869.36
                    }

                    wTvResultIrrf.text = "R$" + "%.2f".format(descIrrf)

                    // Cálculo Total de Descontos
                    val totalDesc: Double = descInss + descIrrf
                    wTvResultDesc.text = "R$" + "%.2f".format(totalDesc)

                    // Cálculo Salário Líquido
                    val salarioLiquido: Double = salarioBruto - totalDesc
                    wTvResultLiquido.text = "R$" + "%.2f".format(salarioLiquido)

                }
            }
        } // Fim Botao Calcula
    }
}