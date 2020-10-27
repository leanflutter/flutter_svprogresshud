enum SVProgressHUDStyle {
  light, // default style, white HUD with black text, HUD background will be blurred
  dark, // black HUD and white text, HUD background will be blurred
  custom // uses the fore- and background color properties
}

extension SVProgressHUDStyleGetName on SVProgressHUDStyle {
  String get name {
    return this.toString().split('.').last;
  }
}
