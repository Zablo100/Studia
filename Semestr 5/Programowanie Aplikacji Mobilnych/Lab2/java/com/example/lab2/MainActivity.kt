package com.example.lab2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Word(val word: String, val translation: String ){}

class MainActivity : AppCompatActivity() {
    private var index = 0;
    private var lista = emptyList<Word>()
    private var points = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start()

        val next = findViewById<Button>(R.id.next)
        next.setOnClickListener{_ -> next()}
    }
    fun next(){
        val intent = Intent(this, Zadanie2::class.java)
        startActivity(intent)
    }

    fun end(){
        val word = findViewById<TextView>(R.id.word)
        val enterButton = findViewById<Button>(R.id.button)
        val userInput = findViewById<EditText>(R.id.userInputBox)

        word.text = String.format("%s", "Koniec, zdobyłeś $points punktów")
        enterButton.text = String.format("%s", "Reset")
        enterButton.setOnClickListener { _ -> start() }
        userInput.visibility = View.INVISIBLE

    }

    fun start(){
        val word = findViewById<TextView>(R.id.word)
        val enterButton = findViewById<Button>(R.id.button)
        val userInput = findViewById<EditText>(R.id.userInputBox)

        index = 0
        points = 0

        lista = getWords()
        word.text = lista[index].word
        userInput.text.clear()
        userInput.visibility = View.VISIBLE
        enterButton.text = String.format("%s", "Enter")

        enterButton.setOnClickListener { _ -> setWord()}
        userInput.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                enterButton.performClick()
                return@OnKeyListener true
            }
            false
        })
    }

    fun setWord(){
        val word = findViewById<TextView>(R.id.word)

        if (index < lista.size -1) {
            checkWord()
            index++
            word.text = lista[index].word
        }else{
            end()
        }

    }

    fun getWords(): List<Word>{
        val p1 = Word("Red", "czerwony")
        val p2 = Word("Blue", "niebieski")
        val p3 = Word("Black", "czarny")
        val p4 = Word("White", "biały")
        val p5 = Word("Green", "zielony")
        val lista = listOf<Word>(p1, p2, p3, p4, p5)

        return lista
    }

    fun checkWord(){
        val userInput = findViewById<EditText>(R.id.userInputBox)

        if (userInput.text.toString().lowercase() == lista[index].translation){
            points++
        }

        userInput.text.clear()
    }


}
