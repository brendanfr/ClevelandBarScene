package com.clevelandbarscene.android;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class VenueListEntryClicker implements OnClickListener {
    int position;
    public VenueListEntryClicker(int positon){
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        Log.i("CBARSCENE", "position: "+position);
        Toast.makeText(v.getContext(), "position"+position, Toast.LENGTH_SHORT);
    }

}
