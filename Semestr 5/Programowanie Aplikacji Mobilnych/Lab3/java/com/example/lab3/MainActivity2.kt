package com.example.lab3

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import java.util.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}

class TriangleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        style = Paint.Style.FILL
    }
    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in 1..100) {
            val random = Random()
            val x1 = random.nextInt(width).toFloat()
            val y1 = random.nextInt(height).toFloat()
            val x2 = random.nextInt(width).toFloat()
            val y2 = random.nextInt(height).toFloat()
            val x3 = random.nextInt(width).toFloat()
            val y3 = random.nextInt(height).toFloat()

            paint.color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
            path.moveTo(x1, y1)
            path.lineTo(x2, y2)
            path.lineTo(x3, y3)
            path.close()

            canvas?.drawPath(path, paint)
            path.reset()
        }
    }
}
