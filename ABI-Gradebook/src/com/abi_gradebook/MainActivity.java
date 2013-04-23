package com.abi_gradebook;

import com.abi_gradebook.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.VideoView;
import android.graphics.Bitmap;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
            	Log.d("mytag","Page Loading Finished!");
            	//hide loading image
                findViewById(R.id.imageView1).setVisibility(View.GONE);
                findViewById(R.id.progressBar1).setVisibility(View.GONE);
                //show webview
                findViewById(R.id.webview).setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if(findViewById(R.id.imageView1).getVisibility()==View.GONE){
                	Log.d("mytag","image view is hidden!");
                	findViewById(R.id.progressBar1).setVisibility(View.VISIBLE);
                }
                Log.d("mytag","Page Loading Started "+url);
                //myURLProgressDialog= ProgressDialog.show(WebviewExampleActivity.this, "Page Loading", "Wait for a moment...");
            }
        });
        myWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            	if(findViewById(R.id.progressBar1).getVisibility()==View.VISIBLE){
            		return true; // block click action if progress bar is visible            		
            	}
                return false; // else do default action
            }
        });
        myWebView.loadUrl("http://aeries.shivamthapar.com");
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	WebView myWebView = (WebView) findViewById(R.id.webview);
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
}
