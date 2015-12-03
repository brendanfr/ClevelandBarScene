package com.clevelandbarscene.android;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class VenuesActivity extends ListActivity {
	
	/* ******************************* TODO 
	 * This almost works, except only bottom row is working? Try with 3+ rows?  Little by ilttle turn "Test" into this?
	 */
	
	HashMap<String,VenueInfo> venues_info = new HashMap<String,VenueInfo>();
	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
	Context mContext;
	
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	final ListView listView = getListView();

    	//findViewById(R.id.list).setOnClickHandler(new VenueListEntryClicker);
       listView.setItemsCanFocus(true);
    	
        VenueListAdapter adapter = new VenueListAdapter(
        		this,
        		list,
        		R.layout.venues_list_entry,
        		new String[] { "venue","address"},
        		new int[] { R.id.venue_name, R.id.address} );
        // in LIstView android:descendantFocusability="blocksDescendants"
        //list.add(new HashMap<String,String>());
        
        HashMap<String,String> venue1 = new HashMap<String,String>();
        venue1.put("venue","Barley House Cleveland");
        venue1.put("address","1261 West 6th Street\nCleveland, OH 44113\n(216) 623-4979");
        list.add(venue1);
        venues_info.put("Barley House Cleveland",
        		new VenueInfo("http://208.109.252.90:8080/cam_1.jpg?uniq=0.9406545360106975",
        				"http://maps.google.com/maps?hl=en&gs_rfai=&um=1&ie=UTF-8&cid=0,0,6473761167792601389&fb=1&hq=barley+house&hnear=cleveland&gl=us&daddr=1265+W+6th+St,+Cleveland,+Ohio+44113&geocode=12940520600100156913,41.500596,-81.698994&ei=0B-hS43xDKDENNDn9fIM&sa=X&oi=local_result&ct=directions-to&resnum=1&ved=0CAkQngIwAA"));
        
        HashMap<String,String> venue2 = new HashMap<String,String>();
        venue2.put("venue","Wonder Bar Cleveland");
        venue2.put("address","2044 East 4th  Street\nCleveland, OH 44115\n(216) 298-4050");
        venues_info.put("Wonder Bar Cleveland",
        		new VenueInfo("http://208.109.252.90:8080/cam_5.jpg",
        				"http://maps.google.com/maps?hl=en&um=1&ie=UTF-8&cid=0,0,9629063103875721267&fb=1&hq=wonder+bar&hnear=Cleveland,+OH&gl=us&daddr=2044+East+4th+Street,+Cleveland,+OH+44115&geocode=5813574900884472900,41.499114,-81.690181&ei=mEh8TLmzIIrZnAfI16GdCw&sa=X&oi=local_result&ct=directions-to&resnum=2&ved=0CBsQngIwAQ"));
        
        list.add(venue2);
        /*
        HashMap<String,String> venue3 = new HashMap<String,String>();
        venue3.put("venue","K.K. Trick's");
        venue3.put("address","620 Frankfort Avenue\nCleveland, OH 44113\n(216) 361-9280");
        venues_info.put("K.K. Trick's",
        		new VenueInfo("http://208.109.252.90:8080/cam_6.jpg",
        				"http://maps.google.com/maps?hl=en&gs_rfai=&um=1&ie=UTF-8&cid=0,0,6473761167792601389&fb=1&hq=barley+house&hnear=cleveland&gl=us&daddr=1265+W+6th+St,+Cleveland,+Ohio+44113&geocode=12940520600100156913,41.500596,-81.698994&ei=0B-hS43xDKDENNDn9fIM&sa=X&oi=local_result&ct=directions-to&resnum=1&ved=0CAkQngIwAA"));
        
        list.add(venue3);
        */
        HashMap<String,String> venue4 = new HashMap<String,String>();
        venue4.put("venue","Tequila Ranch");
        venue4.put("address","1229 West 6th Street\nCleveland, OH 44113\n(216) 566-8226");
        venues_info.put("Tequila Ranch",
        		new VenueInfo("http://208.109.252.90:8080/cam_3.jpg",
        				"http://maps.google.com/maps?hl=en&source=hp&gs_rfai=&um=1&ie=UTF-8&cid=0,0,2137836149563074205&fb=1&hq=tequila+ranch&hnear=cleveland&gl=us&daddr=1229+West+6th+Street,+Cleveland,+OH+44113-1339&geocode=292332997108811585,41.501135,-81.699237&ei=sCChS93PJJPMNZCH2coM&sa=X&oi=local_result&ct=directions-to&resnum=1&ved=0CAwQngIwAA"));
        list.add(venue4);
        
        HashMap<String,String> venue5 = new HashMap<String,String>();
        venue5.put("venue","The 620 Club");
        venue5.put("address","620 Frankfort Avenue\nCleveland, OH 44113\n(216) 361-9280");
        venues_info.put("The 620 Club",
        		new VenueInfo("http://208.109.252.90:8080/cam_6.jpg",
        				"http://maps.google.com/maps?hl=en&q=620+Frankfort+Avenue+Cleve&um=1&ie=UTF-8&hq=&hnear=620+Frankfort+Ave,+Cleveland,+OH+44113&gl=us&daddr=620%20Frankfort%20Ave,%20Cleveland,%20OH%2044113&ei=m5TlTPLWPMuhnAedlKi7DQ&sa=X&oi=geocode_result&ct=directions-to&resnum=1&sqi=2&ved=0CBUQwwUwAA"));
        list.add(venue5);
        
        HashMap<String,String> venue6 = new HashMap<String,String>();
        venue6.put("venue","Stir Cleveland");
        venue6.put("address","16700 Lorain Ave\nCleveland, OH\n(216) 671-1111");
        venues_info.put("Stir Cleveland",
        		new VenueInfo(" http://208.109.252.90:8080/cam_2.jpg",
        				"http://maps.google.com/maps?hl=en&q=16700+lorain+ave+cleveland+oh&bav=on.2,or.r_gc.r_pw.&biw=1143&bih=524&um=1&ie=UTF-8&hq=&hnear=0x8830ed985fba5d4f:0xd2fa661641dfb814,16700+Lorain+Ave,+Cleveland,+OH+44111&gl=us&daddr=16700%20Lorain%20Ave,%20Cleveland,%20OH%2044111&ei=evA6TpTCFdPpgQedqsnPBg&sa=X&oi=geocode_result&ct=directions-to&resnum=1&ved=0CBcQwwUwAA"));
        //venue6.put("venue","Willoughby Brewing Co.");
        //venue6.put("address","4057 Erie Street\nWilloughby, OH 44094");
        list.add(venue6);
        
        HashMap<String,String> venue7 = new HashMap<String,String>();
        venue7.put("venue","Willoughby Brewing Co.");
        venue7.put("address","4057 Erie Street\nWilloughby, OH 44094");
        venues_info.put("Willoughby Brewing Co.",
        		new VenueInfo("http://208.109.252.90:8080/cam_7.jpg",
        				"http://maps.google.com/maps?q=willoughby+brewing+company&daddr=4057+Erie+Street,+Willoughby,+OH+44094+%28Willoughby+Brewing+Company%29&hl=en&sll=41.641443,-81.405596&sspn=0.016677,0.032015&gl=us&view=map&geocode=CfQvChWuDEMfFc1lewIdX9kl-yHKExMNIJYORg&t=m&z=16&vpsrc=0"));   
        //venue7.put("venue","Stir Cleveland");
        //venue7.put("address","16700 Lorain Ave\nCleveland, OH\n(216) 671-1111");
        list.add(venue7);
        
        HashMap<String,String> venue8 = new HashMap<String,String>();
        venue8.put("venue","West 6th Street");
        venue8.put("address","West 6th Street, OH 44113");
        venues_info.put("West 6th Street",
        		new VenueInfo("http://208.109.252.90:8080/cam_4.jpg",
        				"http://maps.google.com/maps?hl=en&expIds=17259,23756,24692,24878,24879,26637&sugexp=ldymls&xhr=t&q=west+6th+street+cleveland&cp=10&um=1&gl=us&resnum=1&ie=UTF-8&hq=&hnear=W+6th+St,+Cleveland,+OH+44113&gl=us&ei=FQO-TKGXFtCsngfTzZSJDg&sa=X&oi=geocode_result&ct=title&resnum=1&ved=0CBUQ8gEwAA"));   
        list.add(venue8);
        
        setListAdapter(adapter);
        
        //((ListView)findViewById(R.layout.venues_list)).setItemsCanFocus(true);

        
        setContentView(R.layout.venues_list);

        
        // Worry about map view later
    }
    
	public void buttonClick(View v){
		HashMap<String,String> row = (HashMap<String,String>) getListAdapter().getItem((Integer)(v.getTag()));
		Log.i("CBARSCENE","Current Venue" + row.get("venue"));
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(venues_info.get(row.get("venue")).getMap()));
    startActivity( intent);
		//Toast.makeText(this, "BUTTON" + (Integer)(v.getTag()), Toast.LENGTH_SHORT).show();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		HashMap<String,String> row = (HashMap<String,String>) getListAdapter().getItem(position);
		//Toast.makeText(this, row.get("address") + " selected. position: " + position + " id: "+id, Toast.LENGTH_SHORT).show();
		Log.i("CBARSCENE",row.get("address") + " selected. position: " + position + " id: "+id);
		
		Intent intent = new Intent(this, VenueActivity.class);
    	intent.putExtra("webcam", venues_info.get(row.get("venue")).getWebcam());
        startActivity( intent);
	}
    /*
    public void myClickHandler(View v)
    {
    	Toast.makeText(v.getContext(), "My click handler", Toast.LENGTH_SHORT);
    }
    */
}
