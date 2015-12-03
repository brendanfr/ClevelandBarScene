package com.clevelandbarscene.barapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Venues extends Activity {

        // Keep this in a static variable to make it accessible for all the nesten activities, lets them manipulate the view
	public static Venues group;
	
    // Need to keep track of the history if you want the back-button to work properly, don't use this if your activities requires a lot of memory.
	private ArrayList<View> history;

	JavascriptInterface jsi;
	WebView venuesView;
	String URL = "http://www.clevelandbarscene.com/android_app/venues.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      group = this;

	      setContentView(R.layout.venues);
	      venuesView = (WebView)findViewById(R.id.venuesView);
	      venuesView.setWebViewClient(new PageViewer());
	        venuesView.getSettings().setJavaScriptEnabled(true);
	        jsi = new JavascriptInterface(this);
	        venuesView.getSettings().setSupportZoom(false);
	        venuesView.addJavascriptInterface(jsi, "Android");
	       Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
	        venuesView.loadUrl(URL);
	        
	
	    	/*
	    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
	    	//intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
	    	startActivity( intent);*/
	   }

    private class PageViewer extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
