package org.leanflutter.svprogresshud;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class SVIndefiniteAnimatedView extends View {
    private static final int ROTATION_DURATION = 1200;
    private static final int ROTATION_LENGTH = 360;

    private RectF oval;
    private Paint paint;

    private SweepGradient gradient;
    private int[] gradientFromToColors;
    private float[] gradientFromToPositions;

    private int startAngle = 0;
    private int sweepAngle = 0;

    private ObjectAnimator animator;
    private float toDegrees = 0;

    // Props
    private float radius;
    private int strokeColor = Color.BLACK;
    private float strokeThickness = 2;

    public SVIndefiniteAnimatedView(Context context) {
        super(context);
        init();
    }

    public SVIndefiniteAnimatedView(Context context,
                                    AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SVIndefiniteAnimatedView(Context context,
                                    AttributeSet attrs,
                                    int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!animator.isStarted()) {
            animator.start();
        }
        canvas.rotate(toDegrees, getWidth() / 2, getHeight() / 2);

        float left = Utils.dp2px(getContext(), strokeThickness);
        float top = Utils.dp2px(getContext(), strokeThickness);
        float right = getWidth() - Utils.dp2px(getContext(), strokeThickness);
        float bottom = getHeight() - Utils.dp2px(getContext(), strokeThickness);
        oval.set(left, top, right, bottom);

        canvas.drawArc(oval, startAngle, sweepAngle, false, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int dimension = Utils.dp2px(getContext(), (this.radius + this.strokeThickness / 2 + 5) * 2);
        setMeasuredDimension(dimension, dimension);
    }

    @Override
    protected void onSizeChanged(int newWidth, int newHeight, int oldw, int oldh) {
        super.onSizeChanged(newWidth, newHeight, oldw, oldh);

        updateSeepAngle();
        updateSweepGradientParams();

        if (getParent() == null) {
            animator.cancel();
        }
    }

    private static int computeOffset(float capRadius, float arcRadius) {
        float sinus = capRadius / arcRadius;
        double degreeRad = Math.asin(sinus);
        double degree = Math.toDegrees(degreeRad);
        return (int) Math.ceil(degree);
    }

    private void init() {
        // disable hardware acceleration
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        oval = new RectF();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(strokeColor);
        paint.setStrokeWidth(Utils.dp2px(getContext(), strokeThickness));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);

        updateSeepAngle();
        updateSweepGradientParams();

        animator = createRotateAnimator();
    }

    private ObjectAnimator createRotateAnimator() {
        ObjectAnimator rotateAnimator = ObjectAnimator.ofFloat(this, "rotation", 0, ROTATION_LENGTH);
        rotateAnimator.setDuration(ROTATION_DURATION);
        rotateAnimator.setInterpolator(new LinearInterpolator());
        rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        return rotateAnimator;
    }

    private void updateSeepAngle() {
        float cupRadius = Utils.dp2px(getContext(), strokeThickness);
        float arcRadius = getWidth() / 2f - Utils.dp2px(getContext(), strokeThickness);
        startAngle = computeOffset(cupRadius, arcRadius);
        sweepAngle = 360 - Utils.dp2px(getContext(), strokeThickness * 2);
    }

    private void updateSweepGradientParams() {
        gradientFromToColors = new int[]{Color.TRANSPARENT, strokeColor};
        gradientFromToPositions = new float[]{0, sweepAngle / 360f};

        float centerX = getWidth() / 2f;
        float centerY = getHeight() / 2f;
        gradient = new SweepGradient(centerX, centerY, gradientFromToColors, gradientFromToPositions);
        paint.setShader(gradient);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        paint.setColor(strokeColor);

        updateSeepAngle();
        updateSweepGradientParams();

        invalidate();
    }

    public void setStrokeThickness(float strokeThickness) {
        this.strokeThickness = strokeThickness;
        paint.setStrokeWidth(Utils.dp2px(getContext(), strokeThickness));

        updateSeepAngle();
        updateSweepGradientParams();

        invalidate();
    }
}
