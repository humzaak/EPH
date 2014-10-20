package com.magint.h.epicurefoodhunting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;


public class Fragment_gallery_details extends Activity  {

	   String[] gallerylistT = new String[] {
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1",
               "gallery entry 1"
       };
       
       int[] galleryListI = new int[]{
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher,
       		R.drawable.ic_launcher
       };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_gallery_details);

        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);    
        
        ListView listView = (ListView) findViewById(R.id.gallerylist1);


        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();        
        
        for(int i=0;i<10;i++){
        	HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("rt",  gallerylistT[i]);
            hm.put("ri", Integer.toString(galleryListI[i]) );            
            aList.add(hm);        
        }
        
        String[] from = { "ri","rt" };
        
        int[] to = { R.id.gallerylistdetailsImageView1,R.id.gallerylistdetailstextView2};   
        
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.fragment_gallery_details_list, from, to);

        listView.setAdapter(adapter); 
        
        
    }

   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            this.finish();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   

}