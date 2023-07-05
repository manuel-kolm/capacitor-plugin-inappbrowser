package com.capacitor.plugin.inappbrowser;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;


public class InAppBrowser {

    private final Context context;
    private final AppCompatActivity activity;

    private WebView webView;

    public InAppBrowser(Context context, AppCompatActivity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void open(Uri uri) {
        activity.runOnUiThread(() -> {
            webView = new WebView(context);
            webView.loadUrl(uri.toString());
            activity.addContentView(webView, new WindowManager.LayoutParams(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT));
        });
    }

    public void close() {
        activity.runOnUiThread(() -> {
            ((ViewGroup) activity.findViewById(android.R.id.content)).removeView(webView);
            webView.setVisibility(View.GONE);
            webView.destroy();
        });
    }
}
