package com.capacitor.plugin.inappbrowser;

import android.net.Uri;

import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "InAppBrowser")
public class InAppBrowserPlugin extends Plugin {

    private InAppBrowser implementation;

    @Override
    public void load() {
        implementation = new InAppBrowser(getContext(), getActivity(), this::onInAppBrowserEvent);
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
        String id = call.getString("id");
        if (id == null || id.isEmpty()) {
            call.reject("Id must be non null nor empty.");
            return;
        }

        implementation.close(id);
        call.resolve();
    }

    void onInAppBrowserEvent(int event) {
        switch (event) {
            case InAppBrowser.BROWSER_OPEN_EVENT:
                notifyListeners("open", null);
                break;
            case InAppBrowser.BROWSER_CLOSE_EVENT:
                notifyListeners("close", null);
                break;
        }
    }
}
