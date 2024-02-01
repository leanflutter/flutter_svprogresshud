enum SVProgressHUDAnimationType {
  flat, // default animation type, custom flat animation (indefinite animated ring)
  native // iOS native UIActivityIndicatorView
}

extension SVProgressHUDAnimationTypeGetName on SVProgressHUDAnimationType {
  String get name {
    return toString().split('.').last;
  }
}
