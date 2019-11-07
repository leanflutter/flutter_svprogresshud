import FlutterMacOS
import Foundation

public class FlutterSvprogresshudPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(
      name: "flutter_svprogresshud",
      binaryMessenger: registrar.messenger)
    let instance = FlutterSvprogresshudPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
    
    ProgressHUD.setDefaultPosition(.center)
    ProgressHUD.setDefaultStyle(.dark)
    ProgressHUD.setDefaultMaskType(.clear)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    let arguments: [String: Any] = call.arguments as! [String: Any]

    let progress: Float = arguments["progress"] as? Float ?? 0.0;
    let status: String = arguments["status"] as? String ?? "";
    let delay: Int = arguments["delay"] as? Int ?? 0;
    
    switch call.method {
        case "setDefaultStyle":
            let style: String = arguments["style"] as! String;
            setDefaultStyle(style: style)
            break;
        case "setDefaultMaskType":
            let maskType: String = arguments["maskType"] as! String;
            setDefaultMaskType(maskType: maskType)
            break;
        case "show":
            show(status: status)
            break;
        case "showInfo":
            showInfo(status: status)
            break;
        case "showSuccess":
            showSuccess(status: status)
            break;
        case "showError":
            showError(status: status)
            break;
        case "showProgress":
            showProgress(progress: progress, status: status)
            break;
        case "dismiss":
            dismiss()
            break;
        case "dismissWithDelay":
            dismissWithDelay(delay: delay)
            break;
        default:
          result(FlutterMethodNotImplemented)
    }
    
    result(true)
  }

    private func setDefaultStyle(style: String) {
        if (style == "dark") {
            ProgressHUD.setDefaultStyle(.dark)
        } else {
            ProgressHUD.setDefaultStyle(.light)
        }
    }

    private func setDefaultMaskType(maskType: String) {
        if ("none" == maskType) {
            ProgressHUD.setDefaultMaskType(.none)
        } else if ("clear" == maskType) {
            ProgressHUD.setDefaultMaskType(.clear)
        } else if ("black" == maskType) {
            ProgressHUD.setDefaultMaskType(.black);
        }
    }

    private func show(status: String) {
        if (status.isEmpty) {
            ProgressHUD.show()
        } else {
            ProgressHUD.show(withStatus: status)
        }
    }

    private func showInfo(status: String) {
        ProgressHUD.showInfoWithStatus(status)
    }

    private func showSuccess(status: String) {
        ProgressHUD.showSuccessWithStatus(status)
    }

    private func showError(status: String) {
        ProgressHUD.showErrorWithStatus(status)
    }

    private func showProgress(progress: Float, status: String) {
        ProgressHUD.show(progress: Double(progress / 100), status: status)
    }

    private func dismiss() {
        ProgressHUD.dismiss(delay: TimeInterval(10))
    }

    private func dismissWithDelay(delay: Int) {
        let delaySecond: TimeInterval  = TimeInterval(delay / 1000);
        ProgressHUD.dismiss(delay: delaySecond, completion: nil)
    }
}
