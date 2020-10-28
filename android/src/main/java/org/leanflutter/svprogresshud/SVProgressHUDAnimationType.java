package org.leanflutter.svprogresshud;

public enum SVProgressHUDAnimationType {
    Flat("flat"),    // default animation type, custom flat animation (indefinite animated ring)
    Native("native");  // iOS native UIActivityIndicatorView

    private String name;

    SVProgressHUDAnimationType(String name) {
        this.name = name;
    }

    public static SVProgressHUDAnimationType fromString(String text) {
        for (SVProgressHUDAnimationType b : SVProgressHUDAnimationType.values()) {
            if (b.name.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
