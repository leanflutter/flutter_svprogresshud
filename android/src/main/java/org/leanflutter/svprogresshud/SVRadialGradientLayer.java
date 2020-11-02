package org.leanflutter.svprogresshud;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SVRadialGradientLayer extends ImageView {
    public SVRadialGradientLayer(Context context) {
        this(context, null);
    }

    public SVRadialGradientLayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float w, h;
        w = getWidth();
        h = getHeight();

        if (w == 0 || h == 0) return;

        Shader shader;

        float centerX = w / 2;
        float centerY = h / 2;
        float radius = Math.min(w, h);
        int centerColor = Color.argb(0x00, 0x00, 0x00, 0x00);
        int edgeColor = Color.argb(0xbf, 0x00, 0x00, 0x00);

        shader = new RadialGradient(
                centerX,
                centerY,
                radius,
                centerColor,
                edgeColor,
                Shader.TileMode.CLAMP
        );

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);
        canvas.drawCircle(centerX, centerY, h, paint);
    }
}
