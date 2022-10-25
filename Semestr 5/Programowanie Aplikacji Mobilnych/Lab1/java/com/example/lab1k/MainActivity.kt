package com.example.lab1k

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener { _ -> calc() }

        val next = findViewById<Button>(R.id.next)

        next.setOnClickListener { _ -> nextAssignment()}
    }

    fun calc() {
        val delta = findViewById<TextView>(R.id.textView4)
        val wynik = findViewById<TextView>(R.id.textView7)
        val a = findViewById<EditText>(R.id.editTextNumber).text.toString().toInt()
        val b = findViewById<EditText>(R.id.editTextNumber2).text.toString().toInt()
        val c = findViewById<EditText>(R.id.editTextNumber3).text.toString().toInt()

        val d = b * b -4 * a * c
        if(d > 0){
            val r1 = (-b + Math.pow(d.toDouble(), 0.5))/(2*a)
            val r2 = (-b - Math.pow(d.toDouble(), 0.5))/(2*a)

            delta.text = String.format("%d", d)
            wynik.text = String.format("X1 = %.1f X2 = %.1f", r1, r2)


        }else if(d == 0){
            val r1 = -b / (2 * a)

            delta.text = String.format("%d", d)
            wynik.text = String.format("X1 = %.1f", r1)
        }else {
            wynik.text = String.format("%s", "Brak rozwiązanie")
        }

    }
    fun nextAssignment(){
        val intent = Intent(this, Zadanie2::class.java)
        startActivity(intent)
    }
}