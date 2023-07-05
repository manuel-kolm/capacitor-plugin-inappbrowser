package com.capacitor.plugin.inappbrowser;

import android.net.Uri;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "InAppBrowser")
public class InAppBrowserPlugin extends Plugin {

    private InAppBrowser implementation;

    @Override
    public void load() {
        implementation = new InAppBrowser(getContext(), getActivity());
    }

    @PluginMethod
    public void open(PluginCall call) {
        String url = call.getString("url");
        if (url == null || url.isEmpty()) {
            call.reject("URL must be non null nor empty.");
            return;
        }

        implementation.open(Uri.parse(url));
        call.resolve();
    }

    @PluginMethod
    public void close(PluginCall call) {
        implementation.close();
        call.resolve();
    }
}
