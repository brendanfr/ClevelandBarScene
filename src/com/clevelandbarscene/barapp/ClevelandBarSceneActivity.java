package com.clevelandbarscene.barapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class ClevelandBarSceneActivity extends TabActivity {
	public TabHost tabHost; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);  
         setContentView(R.layout.main);  
   
             // Get the tabHost  
         this.tabHost = getTabHost();  
   
         TabHost.TabSpec spec;  // Resusable TabSpec for each tab  
         Intent intent;  // Reusable Intent for each tab  
   
         // Create an Intent to launch the first Activity for the tab (to be reused)  
         intent = new Intent().setClass(this, Venues.class);  
   
         // Initialize a TabSpec for the first tab and add it to the TabHost  
         spec = tabHost.newTabSpec("Venues").setIndicator("Venues",  
                 getResources().getDrawable  
                         (R.drawable.icon)) // Replace null with R.drawable.your_icon to set tab icon  
                         .setContent(intent);  
         tabHost.addTab(spec);  
   
             // Create an Intent to launch an Activity for the tab (to be reused)  
         intent = new Intent().setClass(this, Events.class);  
   
         // Initialize a TabSpec for the second tab and add it to the TabHost  
         spec = tabHost.newTabSpec("Events").setIndicator("Events",  
                 getResources().getDrawable  
                         (R.drawable.icon)) // Replace null with R.drawable.your_icon to set tab icon  
                         .setContent(intent);  
         tabHost.addTab(spec);  
   
         tabHost.setCurrentTab(0);  
    }
}