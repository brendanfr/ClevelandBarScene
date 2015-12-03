package com.clevelandbarscene.android;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class VenueListAdapter extends SimpleAdapter {
	   private Context mContext;
	   private LayoutInflater mInflater;
	   boolean[] drawn;
	   private final Map<Integer, Drawable> drawableMap;
	   int[] icons = {R.drawable.venue_barleyhouse,R.drawable.venue_wonderbar,R.drawable.venue_tequilaranch,R.drawable.venue_620,R.drawable.venue_stir,R.drawable.venue_willoughby,R.drawable.venue_west6th};
 		

	    public VenueListAdapter(Context context, List<? extends Map<String, ?>> list, int layout, String[] from, int[] to) {
	        super(context, list, layout, from, to);
	        mContext = context;
	        drawableMap = new HashMap<Integer, Drawable>();
	    }

	    public View getView(int position, View convertView, ViewGroup parent) {
	    	  View view = super.getView(position, convertView, parent);

	    	  /*
	  	        Button map = (Button)view.findViewById(R.id.map);
	  	        map.setOnClickListener(new VenueListEntryClicker(position));
	  	        //Button deny = (Button)view.findViewById(R.id.deny_request);
	  	        //deny.setOnClickListener(new VenueListEntryClicker(position));
	  	         * 
	  	         */
	    	  if(convertView==null){
	    		  LayoutInflater inflater=(LayoutInflater)mContext.getSystemService
	    			      (Context.LAYOUT_INFLATER_SERVICE);
	    		  convertView=inflater.inflate(R.layout.venues_list_entry, parent, false);
	    		 
	 
	    	  }
	    	  
	   		  ImageView icon = (ImageView)view.getTag();
	    	  if(icon == null){
	    		   icon = (ImageView) view.findViewById(R.id.icon);
			  		icon.setImageResource(icons[position]);
			  		view.setTag(icon);
	    	  }
	    	  else{
	    		  icon.setImageResource(icons[position]);
	    	  }
	  		  /*
    		  if(position == 0){
	    		     icon.setImageResource(R.drawable.venue_barleyhouse);
	    	  }
	    	  else if(position == 1){
	    		  icon.setImageResource(R.drawable.venue_kktricks);
	    	  }*/
	  	        //convertView.setClickable(true);
	  	        //convertView.setFocusable(true);
	  	        convertView.setBackgroundResource(android.R.drawable.menuitem_background);
	  	        /*
	  	        convertView.setOnClickListener(new OnClickListener() {
	  	        	  @Override
	  	        	    public void onClick(View v) {
	  	        		     Toast.makeText(v.getContext(), "Click", Toast.LENGTH_SHORT);
	  	        	    }
	  	        });
	  	        */
	  	        
	  	        /*
	  	        if(position == 0){
	  	        	view.setTag("http://maps.google.com/maps?hl=en&gs_rfai=&um=1&ie=UTF-8&cid=0,0,6473761167792601389&fb=1&hq=barley+house&hnear=cleveland&gl=us&daddr=1265+W+6th+St,+Cleveland,+Ohio+44113&geocode=12940520600100156913,41.500596,-81.698994&ei=0B-hS43xDKDENNDn9fIM&sa=X&oi=local_result&ct=directions-to&resnum=1&ved=0CAkQngIwAA");
	  	        	convertView.setTag("http://maps.google.com/maps?hl=en&gs_rfai=&um=1&ie=UTF-8&cid=0,0,6473761167792601389&fb=1&hq=barley+house&hnear=cleveland&gl=us&daddr=1265+W+6th+St,+Cleveland,+Ohio+44113&geocode=12940520600100156913,41.500596,-81.698994&ei=0B-hS43xDKDENNDn9fIM&sa=X&oi=local_result&ct=directions-to&resnum=1&ved=0CAkQngIwAA");
		  	        
	  	        
	  	        }
	  	        else if(position == 1) {
	  	        	view.setTag("http://maps.google.com/maps?hl=en&um=1&ie=UTF-8&cid=0,0,9629063103875721267&fb=1&hq=wonder+bar&hnear=Cleveland,+OH&gl=us&daddr=2044+East+4th+Street,+Cleveland,+OH+44115&geocode=5813574900884472900,41.499114,-81.690181&ei=mEh8TLmzIIrZnAfI16GdCw&sa=X&oi=local_result&ct=directions-to&resnum=2&ved=0CBsQngIwAQ");
	  	        	convertView.setTag("http://maps.google.com/maps?hl=en&gs_rfai=&um=1&ie=UTF-8&cid=0,0,6473761167792601389&fb=1&hq=barley+house&hnear=cleveland&gl=us&daddr=1265+W+6th+St,+Cleveland,+Ohio+44113&geocode=12940520600100156913,41.500596,-81.698994&ei=0B-hS43xDKDENNDn9fIM&sa=X&oi=local_result&ct=directions-to&resnum=1&ved=0CAkQngIwAA");
		  	        
	  	        }*/
	  	        view.findViewById(R.id.map).setTag(position);
	  	        convertView.findViewById(R.id.map).setTag(position);
	  	       // Log.v("CBARSCENE","POSITION"+position);
	  	       // Toast.makeText(view.getContext(), "In getview", Toast.LENGTH_SHORT);
	  	        //view.setOnClickListener(new VenueListEntryClicker(position));
	  	        return view;
	    }

}
