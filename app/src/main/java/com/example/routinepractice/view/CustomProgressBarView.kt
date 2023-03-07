package com.example.routinepractice.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomProgressBarView: View {
    // 생성자
    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)

    // 호위 각도 관리 변수
    private var numProgress = 0.0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()

        // 회색 원(배경)
        paint.color = Color.GRAY
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 40f
        canvas?.drawArc(200f, 200f, 700f, 700f, 0f, 360f, false, paint)

        // 파란 윈(프로그레스)
        paint.color = Color.GREEN
        // sweepAngle 매개변수 위치에 위에서 선언한 numProgress 변수 넣기
        canvas?.drawArc(200f, 200f, 700f, 700f, 90f, numProgress, false, paint)
    }

    // 프로그래스바 각도값 변경
    fun setNumProgress(num: Float) {
        // numProgress 값 변경
        numProgress = num

        // 뷰 갱신: 변경된 numProgress 값으로 다시 그린다.
        invalidate()
    }
}