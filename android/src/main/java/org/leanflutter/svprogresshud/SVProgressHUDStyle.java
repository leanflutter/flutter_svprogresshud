package org.leanflutter.svprogresshud;

public enum SVProgressHUDStyle {
    Light("light"),  // default style, white HUD with black text, HUD background will be blurred
    Dark("dark"),   // black HUD and white text, HUD background will be blurred
    Custom("custom"); // uses the fore- and background color properties

    private String name;

    SVProgressHUDStyle(String name) {
        this.name = name;
    }

    public static SVProgressHUDStyle fromString(String text) {
        for (SVProgressHUDStyle b : SVProgressHUDStyle.values()) {
            if (b.name.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
