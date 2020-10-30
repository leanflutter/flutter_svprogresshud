package org.leanflutter.svprogresshud;

import android.content.Context;

public class Utils {
    private static float scale = 0;

    public static int dp2px(Context context, float dp) {
        if (scale == 0) {
            scale = context.getResources().getDisplayMetrics().density;
        }
        return (int) (dp * scale);
    }
}
