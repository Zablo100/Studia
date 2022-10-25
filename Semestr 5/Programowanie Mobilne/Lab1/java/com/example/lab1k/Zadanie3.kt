package com.example.lab1k

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class Zadanie3 : AppCompatActivity() {

    private var randomNumber = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zadanie3)

        val prev = findViewById<Button>(R.id.prev)
        val btn = findViewById<Button>(R.id.button2)

        startGame()

        prev.setOnClickListener { prevAssignment()}
        btn.setOnClickListener { getUserInput() }

    }

    private fun prevAssignment(){
        val intent = Intent(this, Zadanie2::class.java)
        startActivity(intent)
    }

    private fun getRandomNumber() {
        this.randomNumber = Random.nextInt(1,100)
    }

    private fun startGame(){
        getRandomNumber()
        this.attempts = 0
    }

    private fun checkInput(userInput: Int) {
        val info = findViewById<TextView>(R.id.Info)

        if (userInput > this.randomNumber){
            "Podana liczba jest zbyt duża!".also { info.text = it }
            this.attempts++
        }else if(userInput < this.randomNumber){
            "Podana liczba jest zbyt mała!".also { info.text = it }
            this.attempts++
        }else{
            "Gratulacje zgadłeś za ${this.attempts} razem!".also { info.text = it }
            startGame()
        }

    }

    private fun getUserInput(){
        val userInputBox = findViewById<EditText>(R.id.userInput)
        var userGuess = 0
        val userInput = userInputBox.text.toString().toIntOrNull()

        if (userInput != null){
            userGuess = userInput
        }
        userInputBox.text.clear()
        checkInput(userGuess)
    }


}