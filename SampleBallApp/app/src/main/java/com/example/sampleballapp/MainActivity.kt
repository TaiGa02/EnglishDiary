package com.example.sampleballapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val ballView = BallView(this)
        setContentView(ballView)
    }

    class BallView(context: Context?) : View(context) {
        private var paint:Paint = Paint()
        private var circleX:Float = 200F
        private var circleY:Float = 200F

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            canvas?.drawColor(Color.RED)
            paint.color = Color.YELLOW
            canvas?.drawCircle(circleX,circleY,50F,paint)
        }

        override fun onTouchEvent(event: MotionEvent?): Boolean {

            circleX = event!!.x
            circleY = event.y
            invalidate()
            return true
        }
    }
}