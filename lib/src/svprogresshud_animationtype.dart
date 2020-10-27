enum SVProgressHUDAnimationType {
  flat, // default animation type, custom flat animation (indefinite animated ring)
  native // iOS native UIActivityIndicatorView
}

extension SVProgressHUDAnimationTypeGetName on SVProgressHUDAnimationType {
  String get name {
    return this.toString().split('.').last;
  }
}
