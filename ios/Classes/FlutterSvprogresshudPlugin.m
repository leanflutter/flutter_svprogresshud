#import "FlutterSvprogresshudPlugin.h"

#define FLUTTERCOLOR(c) [UIColor colorWithRed:((c>>16)&0xFF)/255.0  green:((c>>8)&0xFF)/255.0 blue: ((c)&0xFF)/255.0 alpha:((c>>24)&0xFF)/255.0]

@implementation FlutterSvprogresshudPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"flutter_svprogresshud"
                                     binaryMessenger:[registrar messenger]];
    FlutterSvprogresshudPlugin* instance = [[FlutterSvprogresshudPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

- (instancetype)init
{
    self = [super init];
    if (self) {
    }
    return self;
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if ([@"show" isEqualToString:call.method]) {
        [self show:call result:result];
    } else if ([@"showProgress" isEqualToString:call.method]) {
        [self showProgress:call result:result];
    } else if ([@"dismiss" isEqualToString:call.method]) {
        [self dismiss:call result:result];
    } else if ([@"showInfo" isEqualToString:call.method]) {
        [self showInfo:call result:result];
    } else if ([@"showSuccess" isEqualToString:call.method]) {
        [self showSuccess:call result:result];
    } else if ([@"showError" isEqualToString:call.method]) {
        [self showError:call result:result];
    } else if ([@"setDefaultStyle" isEqualToString:call.method]) {
        [self setDefaultStyle:call result:result];
    } else if ([@"setDefaultMaskType" isEqualToString:call.method]) {
        [self setDefaultMaskType:call result:result];
    } else if ([@"setDefaultAnimationType" isEqualToString:call.method]) {
        [self setDefaultAnimationType:call result:result];
    } else if ([@"setMinimumSize" isEqualToString:call.method]) {
        [self setMinimumSize:call result:result];
    } else if ([@"setRingThickness" isEqualToString:call.method]) {
        [self setRingThickness:call result:result];
    } else if ([@"setRingRadius" isEqualToString:call.method]) {
        [self setRingRadius:call result:result];
    } else if ([@"setRingNoTextRadius" isEqualToString:call.method]) {
        [self setRingNoTextRadius:call result:result];
    } else if ([@"setCornerRadius" isEqualToString:call.method]) {
        [self setCornerRadius:call result:result];
    } else if ([@"setBorderColor" isEqualToString:call.method]) {
        [self setBorderColor:call result:result];
    } else if ([@"setBorderWidth" isEqualToString:call.method]) {
        [self setBorderWidth:call result:result];
    } else if ([@"setForegroundColor" isEqualToString:call.method]) {
        [self setForegroundColor:call result:result];
    } else if ([@"setForegroundImageColor" isEqualToString:call.method]) {
        [self setForegroundImageColor:call result:result];
    } else if ([@"setBackgroundColor" isEqualToString:call.method]) {
        [self setBackgroundColor:call result:result];
    } else if ([@"setBackgroundLayerColor" isEqualToString:call.method]) {
        [self setBackgroundLayerColor:call result:result];
    } else if ([@"setImageViewSize" isEqualToString:call.method]) {
        [self setImageViewSize:call result:result];
    } else if ([@"setMinimumDismissTimeInterval" isEqualToString:call.method]) {
        [self setMinimumDismissTimeInterval:call result:result];
    } else if ([@"setMaximumDismissTimeInterval" isEqualToString:call.method]) {
        [self setMaximumDismissTimeInterval:call result:result];
    } else if ([@"setFadeInAnimationDuration" isEqualToString:call.method]) {
        [self setFadeInAnimationDuration:call result:result];
    } else if ([@"setFadeOutAnimationDuration" isEqualToString:call.method]) {
        [self setFadeOutAnimationDuration:call result:result];
    } else if ([@"setHapticsEnabled" isEqualToString:call.method]) {
        [self setHapticsEnabled:call result:result];
    } else  {
        result(FlutterMethodNotImplemented);
        return;
    }
}

- (void)show:(FlutterMethodCall*)call
      result:(FlutterResult)result
{
    NSString *status = call.arguments[@"status"];
    
    if (status != (id)[NSNull null] && [status length] > 0) {
        [SVProgressHUD showWithStatus: status];
    } else {
        [SVProgressHUD show];
    }
}

- (void)showProgress:(FlutterMethodCall*)call
              result:(FlutterResult)result
{
    NSNumber *progress = call.arguments[@"progress"];
    NSString *status = call.arguments[@"status"];
    
    if (status != (id)[NSNull null] && [status length] >0) {
        [SVProgressHUD showProgress: [progress floatValue] status: status];
    } else {
        [SVProgressHUD showProgress: [progress floatValue]];
    }
}

- (void)dismiss:(FlutterMethodCall*)call
         result:(FlutterResult)result
{
    NSNumber *delay = call.arguments[@"delay"];
    
    SVProgressHUDDismissCompletion completion =^{
        result([NSNumber numberWithBool:YES]);
    };
    
    if (delay != (id)[NSNull null]) {
        NSTimeInterval delaySecond = [delay intValue] / 1000;
        [SVProgressHUD dismissWithDelay: delaySecond];
        [SVProgressHUD dismissWithDelay:delaySecond completion:completion];
    } else {
        [SVProgressHUD dismissWithCompletion:completion];
    }
}

- (void)showInfo:(FlutterMethodCall*)call
          result:(FlutterResult)result
{
    NSString *status = call.arguments[@"status"];
    
    [SVProgressHUD showInfoWithStatus:status];
}

- (void)showSuccess:(FlutterMethodCall*)call
             result:(FlutterResult)result
{
    NSString *status = call.arguments[@"status"];
    
    [SVProgressHUD showSuccessWithStatus: status];
}

- (void)showError:(FlutterMethodCall*)call
           result:(FlutterResult)result
{
    NSString *status = call.arguments[@"status"];
    
    [SVProgressHUD showErrorWithStatus: status];
}

- (void)setDefaultStyle:(FlutterMethodCall*)call
                 result:(FlutterResult)result
{
    NSDictionary<NSString*, NSNumber*> *styles = @{
        @"light": [NSNumber numberWithInt:SVProgressHUDStyleLight],
        @"dark": [NSNumber numberWithInt:SVProgressHUDStyleDark],
        @"custom": [NSNumber numberWithInt:SVProgressHUDStyleCustom],
    };
    
    NSString *style = call.arguments[@"style"];
    [SVProgressHUD setDefaultStyle: [[styles valueForKey:style] intValue]];
}
- (void)setDefaultMaskType:(FlutterMethodCall*)call
                    result:(FlutterResult)result
{
    NSDictionary<NSString*, NSNumber*> *maskTypes = @{
        @"none": [NSNumber numberWithInt:SVProgressHUDMaskTypeNone],
        @"clear": [NSNumber numberWithInt:SVProgressHUDMaskTypeClear],
        @"black": [NSNumber numberWithInt:SVProgressHUDMaskTypeBlack],
        @"gradient": [NSNumber numberWithInt:SVProgressHUDMaskTypeGradient],
        @"custom": [NSNumber numberWithInt:SVProgressHUDMaskTypeCustom],
    };
    
    NSString *maskType = call.arguments[@"maskType"];
    [SVProgressHUD setDefaultMaskType: [[maskTypes valueForKey:maskType] intValue]];
}

- (void)setDefaultAnimationType:(FlutterMethodCall*)call
                         result:(FlutterResult)result
{
    NSDictionary<NSString*, NSNumber*> *types = @{
        @"flat": [NSNumber numberWithInt:SVProgressHUDAnimationTypeFlat],
        @"native": [NSNumber numberWithInt:SVProgressHUDAnimationTypeNative],
    };
    
    NSString *type = call.arguments[@"type"];
    [SVProgressHUD setDefaultAnimationType:[[types valueForKey:type] intValue]];
}

- (void)setMinimumSize:(FlutterMethodCall*)call
                result:(FlutterResult)result
{
    NSNumber *width = call.arguments[@"width"];
    NSNumber *height = call.arguments[@"height"];
    
    CGSize minimumSize = CGSizeMake([width floatValue], [height floatValue]);
    [SVProgressHUD setMinimumSize: minimumSize];
}

- (void)setRingThickness:(FlutterMethodCall*)call
                  result:(FlutterResult)result
{
    NSNumber *ringThickness = call.arguments[@"ringThickness"];
    
    [SVProgressHUD setRingThickness: [ringThickness floatValue]];
}
- (void)setRingRadius:(FlutterMethodCall*)call
               result:(FlutterResult)result
{
    NSNumber *radius = call.arguments[@"radius"];
    
    [SVProgressHUD setRingRadius: [radius floatValue]];
}
- (void)setRingNoTextRadius:(FlutterMethodCall*)call
                     result:(FlutterResult)result
{
    NSNumber *radius = call.arguments[@"radius"];
    
    [SVProgressHUD setRingNoTextRadius: [radius floatValue]];
}

- (void)setCornerRadius:(FlutterMethodCall*)call
                 result:(FlutterResult)result
{
    NSNumber *cornerRadius = call.arguments[@"cornerRadius"];
    [SVProgressHUD setCornerRadius:[cornerRadius floatValue]];
}

- (void)setBorderColor:(FlutterMethodCall*)call
                result:(FlutterResult)result
{
    NSNumber *color = call.arguments[@"color"];
    [SVProgressHUD setBorderColor:FLUTTERCOLOR(color.intValue)];
}

- (void)setBorderWidth:(FlutterMethodCall*)call
                result:(FlutterResult)result
{
    NSNumber *width = call.arguments[@"width"];
    [SVProgressHUD setBorderWidth:[width floatValue]];
}

- (void)setForegroundColor:(FlutterMethodCall*)call
                    result:(FlutterResult)result
{
    NSNumber *color = call.arguments[@"color"];
    [SVProgressHUD setForegroundColor:FLUTTERCOLOR(color.intValue)];
}

- (void)setForegroundImageColor:(FlutterMethodCall*)call
                         result:(FlutterResult)result
{
    NSNumber *color = call.arguments[@"color"];
    [SVProgressHUD setForegroundImageColor:FLUTTERCOLOR(color.intValue)];
}

- (void)setBackgroundColor:(FlutterMethodCall*)call
                    result:(FlutterResult)result
{
    NSNumber *color = call.arguments[@"color"];
    [SVProgressHUD setBackgroundColor:FLUTTERCOLOR(color.intValue)];
}

- (void)setBackgroundLayerColor:(FlutterMethodCall*)call
                         result:(FlutterResult)result
{
    NSNumber *color = call.arguments[@"color"];
    [SVProgressHUD setBackgroundLayerColor:FLUTTERCOLOR(color.intValue)];
}

- (void)setImageViewSize:(FlutterMethodCall*)call
                  result:(FlutterResult)result
{
    NSNumber *width = call.arguments[@"width"];
    NSNumber *height = call.arguments[@"height"];
    
    CGSize imageViewSize = CGSizeMake([width floatValue], [height floatValue]);
    [SVProgressHUD setImageViewSize:imageViewSize];
}

- (void)setMinimumDismissTimeInterval:(FlutterMethodCall*)call
                   result:(FlutterResult)result
{
    NSNumber *interval = call.arguments[@"interval"];
    [SVProgressHUD setMinimumDismissTimeInterval:[interval floatValue]];
}

- (void)setMaximumDismissTimeInterval:(FlutterMethodCall*)call
                   result:(FlutterResult)result
{
    NSNumber *interval = call.arguments[@"interval"];
    [SVProgressHUD setMaximumDismissTimeInterval:[interval floatValue]];
}

- (void)setFadeInAnimationDuration:(FlutterMethodCall*)call
                   result:(FlutterResult)result
{
    NSNumber *duration = call.arguments[@"duration"];
    [SVProgressHUD setFadeInAnimationDuration:[duration floatValue]];
}

- (void)setFadeOutAnimationDuration:(FlutterMethodCall*)call
                   result:(FlutterResult)result
{
    NSNumber *duration = call.arguments[@"duration"];
    [SVProgressHUD setFadeOutAnimationDuration:[duration floatValue]];
}

- (void)setHapticsEnabled:(FlutterMethodCall*)call
                   result:(FlutterResult)result
{
    NSNumber *hapticsEnabled = call.arguments[@"hapticsEnabled"];
    [SVProgressHUD setHapticsEnabled:[hapticsEnabled boolValue]];
}

@end
