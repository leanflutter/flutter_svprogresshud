package org.leanflutter.svprogresshud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class SVProgressAnimatedView extends View {
    private RectF oval;

    private float radius = 24f;
    private float strokeThickness = 2.0f;

    private int thumbColor = Color.BLACK;
    private Paint thumbPaint;

    private int activeColor = Color.WHITE;
    private Paint activePaint;

    private float progress = 0;

    public SVProgressAnimatedView(Context context) {
        super(context);
        init(context);
    }

    public SVProgressAnimatedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SVProgressAnimatedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        thumbPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        thumbPaint.setStyle(Paint.Style.STROKE);
        thumbPaint.setStrokeWidth(Utils.dp2px(getContext(), strokeThickness));
        thumbPaint.setColor(thumbColor);

        activePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        activePaint.setStyle(Paint.Style.STROKE);
        activePaint.setStrokeWidth(Utils.dp2px(getContext(), strokeThickness));
        activePaint.setStrokeCap(Paint.Cap.ROUND);
        activePaint.setColor(activeColor);

        oval = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int padding = Utils.dp2px(getContext(), strokeThickness);
        oval.set(padding, padding, w - padding, h - padding);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dimension = Utils.dp2px(getContext(), radius * 2);
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float angle = this.progress * 360f / 1f;
        canvas.drawArc(oval, 270f + angle, 360f - angle, false, thumbPaint);
        canvas.drawArc(oval, 270f, angle, false, activePaint);
    }

    public void setStrokeThickness(float strokeThickness) {
        this.strokeThickness = strokeThickness;

        thumbPaint.setStrokeWidth(Utils.dp2px(getContext(), strokeThickness));
        activePaint.setStrokeWidth(Utils.dp2px(getContext(), strokeThickness));
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setThumbColor(int thumbColor) {
        this.thumbColor = thumbColor;
        this.thumbPaint.setColor(thumbColor);
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
        this.activePaint.setColor(activeColor);
    }

    public void setProgress(float progress) {
        this.progress = progress;

        invalidate();
    }
}
