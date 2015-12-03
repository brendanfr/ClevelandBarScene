package com.clevelandbarscene.android;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class VenueActivity extends Activity {
	Map<String, String> webcams = new HashMap<String, String>();
	 ImageView webcam;
 	TextView chatView;
 	ScrollView scrollView;
 	 TextView username;
 	 EditText message;
 	 Thread timer,timer2;
 	 boolean runThreads = true;
 	 boolean portrait = true;
	 
	    HttpClient httpclient = new DefaultHttpClient();
	   // HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");

	    String user_id = null;
	    
	    Bundle extras;
	 
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			  // Purpose of "unique" thing?  Generate random "unique" ID? TODO
			/*
	    	  webcams.put("Barley House Cleveland", "http://208.109.252.90:8080/cam_1.jpg?uniq=0.9406545360106975");
	    	  webcams.put("Wonder Bar Cleveland", "http://208.109.252.90:8080/cam_5.jpg");
	    	  webcams.put("K.K. Trick's", "http://208.109.252.90:8080/cam_6.jpg");
	    	  webcams.put("Tequila Ranch","http://208.109.252.90:8080/cam_3.jpg");
webcams.put("West 6th Street","http://208.109.252.90:8080/cam_4.jpg?uniq=0.901550667556419");
	    	  
	 
	    	  */
			
			// TODO kill webcam+chat stuff when activity not active? kil activity itself? close when press back?
			/*
	    	  Intent registrationIntent = new Intent("com.google.android.c2dm.intent.REGISTER");

	    	  registrationIntent.putExtra("app", PendingIntent.getBroadcast(this, 0, new Intent(), 0));

	    	  registrationIntent.putExtra("sender", "bfrsoccer@gmail.com");

	    	  startService(registrationIntent);
			  */

		      //extras.getString("venueName")

		    //  Log.i("CBARCAM","VENUE:"+extras.getString("venue"));
		      
			  //   Toast.makeText(getApplicationContext(), "VENUE:"+extras.getString("venue"), Toast.LENGTH_SHORT);
			     
		   //  nsew DownloadImageTask().execute(extras.getString("venue"));
		     // new DownloadImageTask().execute("Barley House Cleveland");
			 setContentView(R.layout.venue);
			 
			 
			 
			WindowManager winMan = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	        if (winMan != null)
	        {
	            portrait = winMan.getDefaultDisplay().getOrientation() == 0;
	        }
	        
			setContentBasedOnLayout();
		     extras = getIntent().getExtras();
		      timer = new Thread() {
		    	    public void run () {
		    	        while(runThreads) {
		    	        	//new DownloadImageTask().execute(extras.getString("webcam"));
		    	        	if(runThreads)
		    	        		uiCallback.sendEmptyMessage(0);
		    	            try {
								Thread.sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}    // sleep for 3 seconds
		    	        }
		    	    }
		    	};
		    	timer2 = new Thread() {
		    	    public void run () {
		    	        while(true){
		    	        	//new DownloadImageTask().execute(extras.getString("webcam"));
		    	            if(runThreads)
		    	            	uiCallback2.sendEmptyMessage(0);
		    	            try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}    // sleep for 3 seconds
		    	        }
		    	    }
		    	};
		    	timer.start();	
		    	if(portrait)
		    		timer2.start();	
		}
		
		@Override
		protected void onStop() {
			super.onStop();
	            runThreads = false;
		}
		
		@Override
		protected void onPause() {
			super.onPause();
	            runThreads = false;
		}
		
		@Override
		protected void onResume() {
			super.onResume();
			//WindowManager winMan = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
	        //if (winMan != null)
	        /*
	            int orientation = winMan.getDefaultDisplay().getOrientation();
	            if(!timer.isAlive())
	            	timer.start();
	            if(orientation == 1 && !timer2.isAlive())
	            	timer2.start();
	        */
			  runThreads = true;
		}
		
	 /*
	    class DownloadImageTask extends AsyncTask<String, Bitmap, Void> {
		    @Override 
			protected Void doInBackground(String... venueNames) {
		    	while(true){
		    		publishProgress(fetchWebcam("http://clevelandbarscene.com/images/PNG.png"));
		    	}
		     }
		    
		    protected void onProgressUpdate(Bitmap... bitmaps ) {
		    
		    }
		   
		     protected void onPostExecute(Bitmap result) {
		    	 webcam.setImageBitmap(result);
		     }
		 }
		*/
		private Handler uiCallback = new Handler () {
		    public void handleMessage (Message msg) {
		    	Bitmap webcamBitmap = fetchWebcam(extras.getString("webcam"));
		    	if(webcamBitmap == null)
		    		webcam.setImageResource(R.drawable.loading); // TODO make error message image
		    	else
		    		webcam.setImageBitmap(webcamBitmap);

		    }
		};
		
		private Handler uiCallback2 = new Handler () {
		    public void handleMessage (Message msg) {
		    	  HttpClient httpclient = new DefaultHttpClient();
		          HttpResponse response;
		          String responseString = "Connection error";
		          
		              try {
		                  response = httpclient.execute(new HttpGet("http://www.clevelandbarscene.com/androidappv2/read.php"));
		                  StatusLine statusLine = response.getStatusLine();
		                  Log.v("CHAT", "READING DATA");
		                  if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		                      ByteArrayOutputStream out = new ByteArrayOutputStream();
		                      response.getEntity().writeTo(out);
		                      out.close();
		                      responseString = out.toString();
		                  } else{
		                      //Closes the connection.
		                      response.getEntity().getContent().close();
		                      throw new IOException(statusLine.getReasonPhrase());
		                  }
		              } catch (ClientProtocolException e) {
		                  //TODO Handle problems..
		              	return;
		              } catch (IOException e) {
		                  //TODO Handle problems..
		              	return;
		              }
		          
		          Log.v("CHAT", "RESPONSE:"+responseString);
		         // chatView.setText("test");
		          chatView.setText(responseString.replaceAll("<br>", "\n"));
		          
		      	scrollView.post(new Runnable() { 
	                public void run() { 
	                    scrollView.smoothScrollTo(0, chatView.getBottom());
	                } 
	            }); 
		    }
		};
		
		
      

		
		// TODO do as thread
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
	    
	    public void changeUsername(View v)
	    {
	    	AlertDialog.Builder alert = new AlertDialog.Builder(this);

    		alert.setTitle("Username");
    		alert.setMessage("Enter a username for Cleveland Bar Scene chat (length < 17 characters)");

    		// Set an EditText view to get user input 
    		final EditText input = new EditText(this);
    		InputFilter[] FilterArray = new InputFilter[1];
    		FilterArray[0] = new InputFilter.LengthFilter(16);
    		input.setFilters(FilterArray);
    		alert.setView(input);

    		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int whichButton) {
    		   user_id = input.getText().toString();
    		   if(user_id != null)
    			   username.setText("Username: " + user_id);
    		  }
    		});

    		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    		  public void onClick(DialogInterface dialog, int whichButton) {
    		    // Canceled.
    		  }
    		});
    		
    		alert.show();
	    }
	    
	    public void requireUsername(View v)
	    {

	    	if(user_id == null)
	    	{
	    		changeUsername(v);
	    	}
	    }
	    /*
		 class ReadTask extends AsyncTask<String, String, String>{

	        @Override
	        protected String doInBackground(String... uri) {
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpResponse response;
	            String responseString = null;
	            TextView chatView = (TextView)findViewById(R.id.chat_view);
	            while(true) {
		            try {
		                response = httpclient.execute(new HttpGet(uri[0]));
		                StatusLine statusLine = response.getStatusLine();
		                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		                    ByteArrayOutputStream out = new ByteArrayOutputStream();
		                    response.getEntity().writeTo(out);
		                    out.close();
		                    responseString = out.toString();
		                } else{
		                    //Closes the connection.
		                    response.getEntity().getContent().close();
		                    throw new IOException(statusLine.getReasonPhrase());
		                }
		            } catch (ClientProtocolException e) {
		                //TODO Handle problems..
		            } catch (IOException e) {
		                //TODO Handle problems..
		            }
	            
	            
	            chatView.setText(responseString.replaceAll("<br>", "\n"));
	            try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            }
	        }

	        @Override
	        protected void onPostExecute(String result) {
	            super.onPostExecute(result);
	            //TextView chatView = (TextView)findViewById(R.id.chat_view);
	            //chatView.setText(result.replaceAll("<br>", "\n"));
	            //this.execute("http://www.clevelandbarscene.com/androidappv2/read.php");
	            //Do anything with response..
	        }
	    }
		 */
		 class PostTask extends AsyncTask<String, String, String>{

		        @Override
		        protected String doInBackground(String... uri) {
		    	    // Create a new HttpClient and Post Header
		    	    HttpClient httpclient = new DefaultHttpClient();
		    	    HttpPost httppost = new HttpPost(uri[0]);

		    	    try {
		    	        // Add your data
		    	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		    	        nameValuePairs.add(new BasicNameValuePair("user_id", uri[1]));
		    	        nameValuePairs.add(new BasicNameValuePair("message",uri[2]));
		    	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		    	        // Execute HTTP Post Request
		    	        HttpResponse response = httpclient.execute(httppost);
		    	        
		    	    } catch (ClientProtocolException e) {
		    	        // TODO Auto-generated catch block
		    	    } catch (IOException e) {
		    	        // TODO Auto-generated catch block
		    	    }
					return null;
		        }

		        @Override
		        protected void onPostExecute(String result) {
		            super.onPostExecute(result);
		            //Do anything with response..
		        }
		    }
		
		
	    public void submitMessage(View v)
	    {
	    	if(user_id == null)
	    		changeUsername(v);
	    	EditText messageView = (EditText)findViewById(R.id.message);
	    	String message = messageView.getText().toString();
	    	Toast.makeText(this, message, Toast.LENGTH_SHORT);
	    	Log.i("EDITTEXT","MESSSSSSSSSSSSSAGE: "+message);
	    	chatView.append( "\n" + user_id + ": "+ message);
	    	scrollView.post(new Runnable() { 
                public void run() { 
                    scrollView.smoothScrollTo(0, chatView.getBottom());
                } 
            }); 
	    	new PostTask().execute("http://www.clevelandbarscene.com/androidappv2/post.php",user_id,message);

	    	} 
	    
	    private void setContentBasedOnLayout()

	    {

	            if (portrait) {
	                // Portrait
	                setContentView(R.layout.venue);
	                
	 		       message = (EditText)findViewById(R.id.message);
	 				
	 			    chatView = (TextView)findViewById(R.id.chat_view); 
	 			    chatView.setMovementMethod(new ScrollingMovementMethod());
	 			      
	 		    	scrollView = (ScrollView)findViewById(R.id.scroller);
	 	 
	 		    
	 		        

		         message.setOnClickListener(new OnClickListener(){
		        	 @Override
		        	 public void onClick(View v){
		        		 requireUsername(v);
		        	 }
		         });
		         
		         username = (TextView)findViewById(R.id.username);
		         username.setOnClickListener(new OnClickListener(){
		        	 @Override
		        	 public void onClick(View v){
		        		 changeUsername(v);
		        	 }
		         });
	            }
	            else {
	                // Landscape
	                setContentView(R.layout.venue_landscape);
		 		       message = (EditText)findViewById(R.id.message);
		 				
		 			    chatView = (TextView)findViewById(R.id.chat_view); 
		 			    chatView.setMovementMethod(new ScrollingMovementMethod());
		 			      
		 		    	scrollView = (ScrollView)findViewById(R.id.scroller);
	            }     
	            webcam = (ImageView)findViewById(R.id.webcamView);
	        }
}
