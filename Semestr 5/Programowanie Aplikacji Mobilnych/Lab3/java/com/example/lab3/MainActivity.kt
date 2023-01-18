package com.example.lab3

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}


class OlympicLogoView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        style = Paint.Style.STROKE

    }

    private val paint2 = Paint().apply {
        style = Paint.Style.FILL

    }
    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.setStrokeWidth(15F);
            val random = Random()
            paint.color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
        val width = width.toFloat()
        val height = height.toFloat()

        // Draw the blue circle
        paint.color = Color.BLUE
        canvas?.drawCircle(300F, 200F , width / 8, paint)

        // Draw the black circle
        paint.color = Color.BLACK
        canvas?.drawCircle(550F, 200F , width / 8, paint)


        // Draw the red circle
        paint.color = Color.RED
        canvas?.drawCircle(770F, 200F , width / 8, paint)

        // Draw the yellow circle
        paint.color = Color.YELLOW
        canvas?.drawCircle(430F, 430F , width / 8, paint)

        // Draw the green circle
        paint.color = Color.GREEN
        canvas?.drawCircle(630F, 430F , width / 8, paint)

        paint2.color = Color.BLUE
        paint2.textSize = 85F
        canvas?.drawText("Rio de Janeiro 2016", 150F, 630F, paint2)

    }
}






