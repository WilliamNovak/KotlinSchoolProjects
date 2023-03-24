package com.example.placar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd1 = findViewById<Button>(R.id.btnAdd1)
        val btnAdd2 = findViewById<Button>(R.id.btnAdd2)
        val btnSub1 = findViewById<Button>(R.id.btnSub1)
        val btnSub2 = findViewById<Button>(R.id.btnSub2)
        val btnSet1 = findViewById<Button>(R.id.btnSet1)
        val btnSet3 = findViewById<Button>(R.id.btnSet3)
        val btnSet5 = findViewById<Button>(R.id.btnSet5)
        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val tvPoints1 = findViewById<TextView>(R.id.tvPoints1)
        val tvPoints2 = findViewById<TextView>(R.id.tvPoints2)
        val tvSetCasa = findViewById<TextView>(R.id.tvSetCasa)
        val tvSetFora = findViewById<TextView>(R.id.tvSetFora)
        val tvSetNumber = findViewById<TextView>(R.id.tvSetNumber)
        var sets = 3
        var editMode = false

        btnSet1.isClickable = false
        btnSet3.isClickable = false
        btnSet5.isClickable = false

        fun compareSetCasa() {
            if ((sets == 1 && tvSetCasa.text.toString().toInt() == sets) || (sets == 3 && tvSetCasa.text.toString().toInt() == 2) || (sets == 5 && tvSetCasa.text.toString().toInt() == 3)){
                tvPoints1.setText((0).toString())
                tvPoints2.setText((0).toString())
                tvSetCasa.setText((0).toString())
                tvSetFora.setText((0).toString())
                tvSetNumber.setText("1º")

                val msg: String = "Time da casa venceu a partida!"
                Toast.makeText(applicationContext,msg, LENGTH_SHORT).show()
            }
        }

        fun compareSetFora() {
            if ((sets == 1 && tvSetFora.text.toString().toInt() == sets) || (sets == 3 && tvSetFora.text.toString().toInt() == 2) || (sets == 5 && tvSetFora.text.toString().toInt() == 3)){
                tvPoints1.setText((0).toString())
                tvPoints2.setText((0).toString())
                tvSetCasa.setText((0).toString())
                tvSetFora.setText((0).toString())
                tvSetNumber.setText("1º")

                val msg: String = "Time visitante venceu a partida!"
                Toast.makeText(applicationContext,msg, LENGTH_SHORT).show()
            }
        }

        btnAdd1.setOnClickListener {
            val points1 = tvPoints1.text.toString().toInt()
            val points2 = tvPoints2.text.toString().toInt()

            if (points1 < 24) {
                tvPoints1.setText((points1 + 1).toString())
            } else {
                if ((points1 + 1) >= (points2 + 2)){
                    tvPoints1.setText((0).toString())
                    tvPoints2.setText((0).toString())
                    tvSetCasa.setText((tvSetCasa.text.toString().toInt() + 1).toString())

                    val msg: String = "Time da casa venceu o set!"
                    Toast.makeText(applicationContext,msg, LENGTH_SHORT).show()

                    val set = tvSetNumber.text.toString().subSequence(0,1)
                    tvSetNumber.setText((set.toString().toInt() + 1).toString() + "º")

                    compareSetCasa()
                } else {
                    tvPoints1.setText((points1 + 1).toString())
                }
            }
        }

        btnAdd2.setOnClickListener {
            val points1 = tvPoints1.text.toString().toInt()
            val points2 = tvPoints2.text.toString().toInt()

            if (points2 < 24) {
                tvPoints2.setText((points2 + 1).toString())
            } else {
                if ((points2 + 1) >= (points1 + 2)){
                    tvPoints1.setText((0).toString())
                    tvPoints2.setText((0).toString())
                    tvSetFora.setText((tvSetFora.text.toString().toInt() + 1).toString())

                    val msg: String = "Time visitante venceu o set!"
                    Toast.makeText(applicationContext,msg, LENGTH_SHORT).show()

                    val set = tvSetNumber.text.toString().subSequence(0,1)
                    tvSetNumber.setText((set.toString().toInt() + 1).toString() + "º")

                    compareSetFora()
                } else {
                    tvPoints2.setText((points2 + 1).toString())
                }
            }
        }

        btnEdit.setOnClickListener {
            val points1 = tvPoints1.text.toString().toInt()
            val points2 = tvPoints2.text.toString().toInt()

            if (editMode == false) {
                editMode = true

                btnSet1.isClickable = true
                btnSet3.isClickable = true
                btnSet5.isClickable = true
                btnSub1.isVisible   = true
                btnSub2.isVisible   = true
                btnEdit.setText("Concluído")
            } else {
                editMode = false

                btnSet1.isClickable = false
                btnSet3.isClickable = false
                btnSet5.isClickable = false
                btnSub1.isVisible   = false
                btnSub2.isVisible   = false
                btnEdit.setText("Editar")

                if (points1 > 24 && points1 >= (points2 + 2)) {
                    tvPoints1.setText((0).toString())
                    tvPoints2.setText((0).toString())
                    tvSetCasa.setText((tvSetCasa.text.toString().toInt() + 1).toString())

                    val msg: String = "Time da casa venceu o set!"
                    Toast.makeText(applicationContext,msg, LENGTH_SHORT).show()

                    val set = tvSetNumber.text.toString().subSequence(0,1)
                    tvSetNumber.setText((set.toString().toInt() + 1).toString() + "º")

                    compareSetCasa()
                } else if (points2 > 24 && points2 >= (points1 + 2)) {
                    tvPoints1.setText((0).toString())
                    tvPoints2.setText((0).toString())
                    tvSetFora.setText((tvSetFora.text.toString().toInt() + 1).toString())

                    val msg: String = "Time visitante venceu o set!"
                    Toast.makeText(applicationContext,msg, LENGTH_SHORT).show()

                    val set = tvSetNumber.text.toString().subSequence(0,1)
                    tvSetNumber.setText((set.toString().toInt() + 1).toString() + "º")

                    compareSetFora()
                }
            }
        }

        btnSub1.setOnClickListener {
            val points1 = tvPoints1.text.toString().toInt()

            if (points1 > 0) {
                tvPoints1.setText((points1 - 1).toString())
            }
        }

        btnSub2.setOnClickListener {
            val points2 = tvPoints2.text.toString().toInt()

            if (points2 > 0) {
                tvPoints2.setText((points2 - 1).toString())
            }
        }

        btnSet1.setOnClickListener {
            sets = 1
            btnSet1.setBackgroundColor(Color.parseColor("#60605C"))
            btnSet3.setBackgroundColor(Color.parseColor("#454542"))
            btnSet5.setBackgroundColor(Color.parseColor("#454542"))
            tvPoints1.setText((0).toString())
            tvPoints2.setText((0).toString())
            tvSetCasa.setText((0).toString())
            tvSetFora.setText((0).toString())
            tvSetNumber.setText("1º")
        }

        btnSet3.setOnClickListener {
            sets = 3
            btnSet1.setBackgroundColor(Color.parseColor("#454542"))
            btnSet3.setBackgroundColor(Color.parseColor("#60605C"))
            btnSet5.setBackgroundColor(Color.parseColor("#454542"))
            tvPoints1.setText((0).toString())
            tvPoints2.setText((0).toString())
            tvSetCasa.setText((0).toString())
            tvSetFora.setText((0).toString())
            tvSetNumber.setText("1º")
        }

        btnSet5.setOnClickListener {
            sets = 5
            btnSet1.setBackgroundColor(Color.parseColor("#454542"))
            btnSet3.setBackgroundColor(Color.parseColor("#454542"))
            btnSet5.setBackgroundColor(Color.parseColor("#60605C"))
            tvPoints1.setText((0).toString())
            tvPoints2.setText((0).toString())
            tvSetCasa.setText((0).toString())
            tvSetFora.setText((0).toString())
            tvSetNumber.setText("1º")
        }

    } // Fim onCreate
} // Fim MainActivity