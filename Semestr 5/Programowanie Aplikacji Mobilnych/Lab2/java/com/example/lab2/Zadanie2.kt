package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class Zadanie2 : AppCompatActivity() {
    private var attempts = 0;
    private var word = ""
    private var fWord = ""
    private var maxAttempts = 8;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zadanie2)

        start();
    }

    fun start(){
        val proby = findViewById<TextView>(R.id.proby)
        val button = findViewById<Button>(R.id.button2)
        val slowo = findViewById<TextView>(R.id.slowo)

        proby.text = String.format("Prób: %d", attempts)
        word = getRandomWord()
        fWord = ""
        attempts = 0
        maxAttempts = 8

        button.setOnClickListener{_ -> checkUserInput()}
        button.text = "Enter"

        asd()
        slowo.text = fWord;

    }

    fun renderWord(char: Char, lista: List<Int>){
        val slowo = findViewById<TextView>(R.id.slowo)

       val x = getFormatedWord(char , lista)
        fWord = x
        slowo.text = x

    }

    fun checkUserInput(){
        val proby = findViewById<TextView>(R.id.proby)
        val zycia = findViewById<TextView>(R.id.proby2)
        val input = findViewById<EditText>(R.id.userInputBox2).text.toString()
        val lista = mutableListOf<Int>()

        if (maxAttempts <= 0){
            end(false);
            return;
        }


        if (!word.contains(input)){
            attempts++
            maxAttempts--
        }

        proby.text = String.format("Prób: %d", attempts)
        zycia.text = String.format("Pozostałych Prób: %d", maxAttempts)

        var index = word.indexOf(input)
        while (index != -1) {
            lista.add(index * 2)
            index = word.indexOf(input, index + 1)
        }

        if (lista.size > 0){
            renderWord(input.first(), lista)
        }

        if (!fWord.contains('_')){
            end(true)
        }

    }

    private fun end(win: Boolean) {
        val proby = findViewById<TextView>(R.id.proby)
        val button = findViewById<Button>(R.id.button2)

        if (win) {
            proby.text = String.format("Gratulacje wygrałeś! Próby: %d", attempts)
        }else{
            proby.text = String.format("Przegrałeś :(")
        }

        button.text = "Resetuj"
        button.setOnClickListener { _ -> start() }

    }

    fun getRandomWord(): String {
        val words = getWords()
        val word = words.get(Random.nextInt(0,words.size))

        return word;
    }

    fun getWords(): List<String> {
        val words = listOf<String>(
            "Kalafior",
            "Pomidor",
            "Arbuz",
            "Gruszka",
            "Sałata",
            "Winogrona",
            "Pomarańcza"
        )

        return words;
    }

    fun asd(){
        var array = word.toCharArray()

        for (x in array){
            fWord += "_ "
        }
    }

    fun getFormatedWord(letter: Char, lista: List<Int>): String {

        var array = fWord.toCharArray()

        for (index in lista){
            array[index] = letter
        }

        println(array)

        var formatedWord = ""
        for (char in array){
            formatedWord += char
        }

        println(formatedWord)

        return formatedWord
    }
}