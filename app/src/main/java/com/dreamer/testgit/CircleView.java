package com.dreamer.testgit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

/**
 * Created by dreamer on 2015/7/21.
 */
public class CircleView extends View {

    private Context context;
    private int maxValue;
    private int progress;
    private Paint backgroundPaint, frontPaint, textPaint;
    private RectF oval;
    private int x, y, r;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initDefaultParams();
        initBackgroundPaint();
        initFrontPaint();
        initTextPaint();
        initOval();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x = getMeasuredWidth() / 2;
        y = getMeasuredHeight() / 2;
        r = x - 1;
        //在中心的地方画个半径为20的圆，宽度为setStrokeWidth：1，也就是灰色的底边
        canvas.drawCircle(x, y, r, backgroundPaint);
        //规定参考圆的范围
        oval.set(1, 1, getMeasuredWidth() - 1, getMeasuredHeight() - 1);
        //画圆弧，第二个参数为：起始角度，第三个为跨的角度，第四个为true的时候是实心，false的时候为空心
        canvas.drawArc(oval, -90, ((float) progress / maxValue) * 360, false, frontPaint);
        if (progress == maxValue) {
            Log.e("test", x + "------------" + y);
            canvas.drawText("1", x, getTextVerticalPoint(), textPaint);
        }
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        invalidate();
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public void beginAnimator(int duration, int delay) {
        ValueAnimator colorAnim = ObjectAnimator.ofInt(this, "progress", 0, getMaxValue());
        colorAnim.setDuration(duration);
        colorAnim.setStartDelay(delay);
        colorAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        colorAnim.start();
    }

    private void initBackgroundPaint() {
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);// 设置是否抗锯齿
        backgroundPaint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿
        backgroundPaint.setColor(Color.GRAY);// 设置画笔灰色
        backgroundPaint.setStrokeWidth(1);// 设置画笔宽度
        backgroundPaint.setStyle(Paint.Style.STROKE);// 设置中空的样式
    }

    private void initFrontPaint() {
        frontPaint = new Paint();
        frontPaint.setAntiAlias(true);// 设置是否抗锯齿
        frontPaint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿
        frontPaint.setColor(getResources().getColor(R.color.yellow));// 设置画笔灰色
        frontPaint.setStrokeWidth(1);// 设置画笔宽度
        frontPaint.setStyle(Paint.Style.STROKE);// 设置中空的样式
    }

    private void initTextPaint() {
        textPaint = new Paint();
        textPaint.setAntiAlias(true);//设置是否抗锯齿
        textPaint.setColor(getResources().getColor(R.color.yellow));// 设置画笔灰色
        textPaint.setTextSize(sp2px(14));
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    private float getTextVerticalPoint() {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        float textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom;
        return textBaseY;
    }

    private void initDefaultParams() {
        maxValue = 360;
        progress = 0;
    }

    private void initOval() {
        oval = new RectF();
    }

    private int dp2px(float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int sp2px(float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
