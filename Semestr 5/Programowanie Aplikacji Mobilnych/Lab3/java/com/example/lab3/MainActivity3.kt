package com.example.lab3

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import java.util.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
    }
}

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val rect = Rect()
    private val path = Path()
    private val random = Random()

    override fun onDraw(canvas: Canvas) {
        // Rysowanie linii
        val startX = random.nextInt(width).toFloat()
        val startY = random.nextInt(height).toFloat()
        val stopX = random.nextInt(width).toFloat()
        val stopY = random.nextInt(height).toFloat()
        paint.color = Color.RED
        canvas.drawLine(startX, startY, stopX, stopY, paint)

        // Rysowanie prostokąta
        var left = random.nextInt(width)
        var top = random.nextInt(height)
        var right = left + random.nextInt(width - left)
        var bottom = top + random.nextInt(height - top)
        paint.color = Color.BLUE
        rect.set(left, top, right, bottom)
        canvas.drawRect(rect, paint)

        // Rysowanie trójkąta
        val x1 = random.nextInt(width).toFloat()
        val y1 = random.nextInt(height).toFloat()
        val x2 = random.nextInt(width).toFloat()
        val y2 = random.nextInt(height).toFloat()
        val x3 = random.nextInt(width).toFloat()
        val y3 = random.nextInt(height).toFloat()
        paint.color = Color.GREEN
        path.moveTo(x1, y1)
        path.lineTo(x2, y2)
        path.lineTo(x3, y3)
        path.close()
        canvas.drawPath(path, paint)

        // Rysowanie okręgu
        val cx = random.nextInt(width).toFloat()
        val cy = random.nextInt(height).toFloat()
        val radius = random.nextInt(Math.min(width, height) / 2).toFloat()
        paint.color = Color.YELLOW
        canvas.drawCircle(cx, cy, radius, paint)

        // Rysowanie wycinka okręgu
        left = random.nextInt(width)
        top = random.nextInt(height)
        right = left + random.nextInt(width - left)
        bottom = top + random.nextInt(height - top)
        val rectF = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
        val startAngle = random.nextInt(360).toFloat()
        val sweepAngle = random.nextInt(360).toFloat()
        paint.color = Color.MAGENTA
        canvas.drawArc(rectF, startAngle, sweepAngle, true, paint)
    }
}