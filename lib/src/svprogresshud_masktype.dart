enum SVProgressHUDMaskType {
  none, // default mask type, allow user interactions while HUD is displayed
  clear, // don't allow user interactions with background objects
  black, // don't allow user interactions with background objects and dim the UI in the back of the HUD (as seen in iOS 7 and above)
  gradient, // don't allow user interactions with background objects and dim the UI with a a-la UIAlertView background gradient (as seen in iOS 6)
  custom // don't allow user interactions with background objects and dim the UI in the back of the HUD with a custom color
}

extension SVProgressHUDMaskTypeGetName on SVProgressHUDMaskType {
  String get name {
    return this.toString().split('.').last;
  }
}
