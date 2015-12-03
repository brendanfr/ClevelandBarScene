package com.clevelandbarscene.barapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.app.ActivityGroup;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class Venue extends ActivityGroup {
	
	// TODO:
	/*
	 * Professional loading/error messages (for web pages, web cams)
	 * Events page
	 * Icons or venues, Events
	 * Menu - "Refresh" button, "About" (talk abuot CBS features, etc.)
	 * 
	 */

	Map<String, String> webcams = new HashMap<String, String>();
	 ImageView webcam;
    String imageUrl="http://208.109.252.90:8080/cam_4.jpg?uniq=0.05643876804970205";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		  // Purpose of "unique" thing?  Generate random "unique" ID? TODO
    	  webcams.put("Barley House Cleveland", "http://208.109.252.90:8080/cam_1.jpg?uniq=0.9406545360106975");
    	  webcams.put("Wonder Bar Cleveland", "http://208.109.252.90:8080/cam_5.jpg");
    	  webcams.put("K.K. Trick's", "http://208.109.252.90:8080/cam_6.jpg");
    	  webcams.put("Tequila Ranch","http://208.109.252.90:8080/cam_3.jpg");
    	  webcams.put("West 6th Street","http://208.109.252.90:8080/cam_4.jpg?uniq=0.901550667556419");

    	  
		  super.onCreate(savedInstanceState);
	      setContentView(R.layout.webcam);
          webcam = (ImageView)findViewById(R.id.webcamView);
	      Bundle extras = getIntent().getExtras();
	      //extras.getString("venueName")

	     new DownloadImageTask().execute(extras.getString("venueName"));

	}
	
    class DownloadImageTask extends AsyncTask<String, Bitmap, Void> {
	    @Override 
		protected Void doInBackground(String... venueNames) {
	    	while(true){
	    		publishProgress(fetchWebcam(webcams.get(venueNames[0])));
	    	}
	     }
	    
	    protected void onProgressUpdate(Bitmap... bitmaps ) {
	    	webcam.setImageBitmap(bitmaps[0]);
	    }
	   
	     protected void onPostExecute(Bitmap result) {
	    	 webcam.setImageBitmap(result);
	     }
	 }
	
   // Bitmap webcambmp;
    Bitmap fetchWebcam(String fileUrl){
        URL myFileUrl =null;          
        try {
             myFileUrl= new URL(fileUrl);
        } catch (MalformedURLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        }
        try {
             HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
             conn.setDoInput(true);
             conn.connect();
             int length = conn.getContentLength();
             InputStream is = conn.getInputStream();
             
             return BitmapFactory.decodeStream(is);
             //webcambmp = BitmapFactory.decodeStream(is);
             //webcam.setImageBitmap(webcambmp);
        } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
        }
        return null;
   }
	
}
