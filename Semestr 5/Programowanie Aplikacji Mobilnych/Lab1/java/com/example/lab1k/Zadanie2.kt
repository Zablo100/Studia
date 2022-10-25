package com.example.lab1k

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class Zadanie2 : AppCompatActivity() {
    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zadanie2)

        val next = findViewById<Button>(R.id.next)
        val prev = findViewById<Button>(R.id.prev)

        prev.setOnClickListener {_ -> prevAssignment()}
        next.setOnClickListener { _ -> nextAssignment() }

        calc()
    }

    fun prevAssignment(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun nextAssignment(){
        val intent = Intent(this, Zadanie3::class.java)
        startActivity(intent)
    }

    fun randomArray(): List<Int> {
        val randomNumbers = List(10) { Random.nextInt(0, 100) }
        return randomNumbers
    }

    fun calc(){
        val numbers = randomArray();
        val listView = findViewById<TextView>(R.id.ListView)
        val max = findViewById<TextView>(R.id.Max)
        val min = findViewById<TextView>(R.id.Min)
        val sum = findViewById<TextView>(R.id.Sum)
        val avg = findViewById<TextView>(R.id.Avg)

        listView.text = numbers.toString()
        max.text = numbers.max().toString()
        min.text = numbers.min().toString()
        sum.text = numbers.sum().toString()
        avg.text = numbers.average().toString()

    }

}