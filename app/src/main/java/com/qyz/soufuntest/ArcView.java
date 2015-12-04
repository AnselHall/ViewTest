package com.qyz.soufuntest;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class ArcView extends View {

    private static final String TAG = "tag";
    private Paint paint;
    private int height, width;
    private int angel;/*弧形的角度*/
    private RectF oval;/*绘制弧形使用*/
    private Paint txPaint;
    private int denominator = 30;/*分母*/
    private int numerator;/*分子*/
    private Paint linePaint;/*绘制分割线*/
    private Rect boundRect;/*计算分子的长宽使用*/
    private float textSize;

    /*暂时只使用代码的方式创建
    public ArcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ArcView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }*/

    public ArcView(Context context) {
        super(context);
        init();
    }

    private void init() {

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Style.STROKE);

        txPaint = new Paint();
        txPaint.setAntiAlias(true);
        txPaint.setColor(Color.WHITE);

        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setAntiAlias(true);

        boundRect = new Rect();
        oval = new RectF();
    }

    /*
        * w:当前控件的宽
         * h:当前控件的高
         *
         * oldw:控件之前的宽
         * oldh:控件之前的高
        * */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
//        Log.e("Paragrame", "w = " + w + "h = " + h + "oldw = " + oldw + "oldh = " + oldh);
        if (w >= h) {
            w = h;
        }

        Log.e(TAG, "onSizeChanged() called with: " + "w = [" + w + "], h = [" + h + "], oldw = [" + oldw + "], oldh = [" + oldh + "]");
        width = height = w;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
        calcAngle();
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
        calcAngle();
    }

    private void calcAngle() {
        if (denominator == 0) {
            return;
        }
        this.angel = (int) (numerator * 360 / denominator);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//		canvas.drawColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawColor(Color.TRANSPARENT);
//		canvas.drawColor(Color.WHITE);

        int padding = 20;
        oval.left = padding;
        oval.top = padding;
        oval.right = width - padding;
        oval.bottom = height - padding;

        textSize = height / 3;
        //设置弧性的宽度
        paint.setStrokeWidth(width / 20);
        canvas.drawArc(oval, 360, angel, false, paint);

        txPaint.setTextSize(textSize);

        if (numerator > 9) {
            txPaint.getTextBounds(numerator + "", 0, 2, boundRect);
        } else {
            txPaint.getTextBounds(numerator + "", 0, 1, boundRect);
        }

        int height = boundRect.height();
        int width = boundRect.width();
        Log.e(TAG, "onDraw: width ：" + width);
        Log.e(TAG, "onDraw: height ：" + height);

        //绘制分子
        float numeratorX = this.width / 2 - width / 2 - 20;
        float numeratorY = this.height / 2 + height / 2;
        canvas.drawText(numerator + "", numeratorX, numeratorY, txPaint);
        txPaint.getTextBounds(numerator + "", 0, 1, boundRect);
        //分割线
        canvas.drawLine(this.width / 2 + width / 2, numeratorY, this.width / 2 + width / 2 + height / 2, this.height / 2, linePaint);
        //绘制分母   分母的大小为分子的1/4
        txPaint.setTextSize(textSize / 4);
        canvas.drawText(denominator + "", this.width / 2 + width / 2 + height / 2, numeratorY, txPaint);
    }
}
