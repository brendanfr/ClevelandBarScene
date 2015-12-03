package com.clevelandbarscene.barapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class JavascriptInterface {
	Context mContext;

    /** Instantiate the interface and set the context */
    JavascriptInterface(Context c) {
        mContext = c;
    }

    public void openMap(String uri) {
    	//String uri = "geo:"+ latitude + "," + longitude + "?q=my+street+address";
    	//Toast.makeText(mContext, "OPENING..."+uri, Toast.LENGTH_LONG).show();
    	//uri = "http://maps.google.com/maps?hl=en&gs_rfai=&um=1&ie=UTF-8&cid=0,0,6473761167792601389&fb=1&hq=barley+house&hnear=cleveland&gl=us&daddr=1265+W+6th+St,+Cleveland,+Ohio+44113&geocode=12940520600100156913,41.500596,-81.698994&ei=0B-hS43xDKDENNDn9fIM&sa=X&oi=local_result&ct=directions-to&resnum=1&ved=0CAkQngIwAA";
        
    	Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
    	//intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
    	mContext.startActivity( intent);
    	
    }
    
    public void openVenue(String venueName) {
    	Intent intent = new Intent(mContext, Venue.class);
    	intent.putExtra("venueName", venueName);
    	//intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
    	mContext.startActivity( intent);
    	
    }
    
    public void loadMsg(){
    	// TODO: improve loading messag
    	Toast.makeText(mContext, "Loading...", Toast.LENGTH_LONG).show();
    }
    public int getId(){
    	return id;
    }
    public void setId(int id2){
    	if(id2<0)
    		id=8663;
    	else if(id2>8663)
    		id=0;
    	else
    		id=id2;
    }
    
    public void incId(){
    	setId(id+1);
    }
    public void decId(){
    	setId(id-1);
    }
    
	
	private int id; // Current problem id
	//private String info;

}
