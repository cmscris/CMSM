package com.cris.cmsm;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cris.cmsm.util.Constants;
import com.cris.cmsm.util.FontFamily;

public class WebsitesLink extends BaseActivity {

    private ProgressBar progressBar;
    private WebView webView;
    private Typeface fontFamily;
    private ImageView iv_title_icon,iv_left, iv_middle, iv_right;
    private TextView action_bar_title;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.websites_link);
        Intent webIntent = getIntent();
        fontFamily = new FontFamily(WebsitesLink.this).getBold();
        action_bar_title = findViewById(R.id.action_bar_title);
        iv_title_icon = findViewById(R.id.iv_title_icon);
        progressBar = findViewById(R.id.progressBar1);
        iv_right = findViewById(R.id.iv_right);
        iv_right.setVisibility(View.VISIBLE);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new SSLTolerentWebViewClient());
        String pageTitle = webIntent.getStringExtra(Constants.PAGE_TITLE);
        if (pageTitle == null || pageTitle.isEmpty()) {
            action_bar_title.setText("Website");
        } else {
            action_bar_title.setText(pageTitle);
        }

        Log.d("Constants ----  ", Constants.PAGE_URL);
        Log.d("URL ----  ", webIntent.getStringExtra(Constants.PAGE_URL));

        webView.loadUrl(webIntent.getStringExtra(Constants.PAGE_URL));
        action_bar_title.setTypeface(fontFamily);
        iv_title_icon.setVisibility(View.VISIBLE);
        iv_title_icon.setImageResource(R.drawable.iv_back);
        iv_title_icon.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                onBackPressed();
            }
        });

        iv_right.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                webView = null;
                onBackPressed();
            }
        });

    }

    private class SSLTolerentWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedSslError(WebView view,
                                       final SslErrorHandler handler, SslError error) {
            try {

                String message = getResources().getString(
                        R.string.certificate_error);
                switch (error.getPrimaryError()) {
                    case SslError.SSL_UNTRUSTED:
                        message = getResources().getString(
                                R.string.certificate_untrusted);
                        break;
                    case SslError.SSL_EXPIRED:
                        message = getResources().getString(
                                R.string.certificate_expired);
                        break;
                    case SslError.SSL_IDMISMATCH:
                        message = getResources().getString(
                                R.string.certificate_mistmatch);
                        break;
                    case SslError.SSL_INVALID:
                        message = getResources().getString(
                                R.string.certificate_not_valid);
                        break;
                }
                message += getResources().getString(
                        R.string.do_you_want_continue);

                FontFamily font = new FontFamily(WebsitesLink.this);
                final Dialog dialog = new Dialog(WebsitesLink.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(
                        new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCanceledOnTouchOutside(false);
                dialog.setContentView(R.layout.alert_dialog);
                TextView tv_otp_title = dialog
                        .findViewById(R.id.tv_otp_title);
                tv_otp_title.setText(message);
                tv_otp_title.setTypeface(font.getRegular());
                TextView action_bar_title = dialog
                        .findViewById(R.id.action_bar_title);
                action_bar_title.setText(getResources().getString(
                        R.string.app_name));
                action_bar_title.setTypeface(font.getRegular());
                Button btn_cancel = dialog
                        .findViewById(R.id.btn_cancel);
                Button btn_ok = dialog.findViewById(R.id.btn_ok);
                btn_cancel.setTypeface(font.getRegular());
                btn_cancel.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        handler.cancel();
                        dialog.dismiss();
                        finish();
                    }
                });
                btn_ok.setTypeface(font.getRegular());
                btn_ok.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        dialog.dismiss();
                        handler.proceed();

                    }
                });

                //dialog.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (webView != null && webView.canGoBack()) {
            Log.d("BAck --- ", " Can Go ");
            webView.goBack();
        } else {
            Log.d("BAck --- ", " No Go ");
            webView = null;
            super.onBackPressed();
        }
    }
}
