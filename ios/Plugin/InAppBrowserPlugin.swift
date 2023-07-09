import Foundation
import Capacitor

/**
 * Please read the Capacitor iOS Plugin Development Guide
 * here: https://capacitorjs.com/docs/plugins/ios
 */
@objc(InAppBrowserPlugin)
public class InAppBrowserPlugin: CAPPlugin {
    private let implementation = InAppBrowser()
    
    @objc func open(_ call: CAPPluginCall) {
        guard let urlString = call.getString("url"), let url = URL(string: urlString) else {
            call.reject("Must provide a valid URL to open")
            return
        }
        
        implementation.open(url)
        call.resolve()
    }
    
    @objc func close(_ call: CAPPluginCall) {
        guard let id = call.options["id"] as? String else {
          call.reject("Must provide an id")
          return
        }
        
        implementation.close(id)
        call.resolve()
    }
}
