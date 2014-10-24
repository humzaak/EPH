package com.magint.h.epicurefoodhunting;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import android.R.color;
import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class Fragment_recipes_details extends Activity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe_details);
        
        
		WebView myWebView = (WebView) findViewById(R.id.webview);
		//myWebView.setBackgroundColor(0);
		//myWebView.loadData(readTextFromResource(R.raw.new3), "text/html", "utf-8");
		
        final ActionBar actionBar = getActionBar();
        actionBar.setHomeButtonEnabled(true);       
        
        Bundle extras = getIntent().getExtras();
        int _recipeID;
        if (extras != null) {
        	_recipeID = extras.getInt("recipeID");
        	//new getRecipieDetailsAsync().execute(params)
        }
        
        
        
    }
    
    public class getRecipieDetailsAsync extends AsyncTask<String, String, String>
    {

   	@Override
   	protected String doInBackground(String... params) {
   		// TODO Auto-generated method stub
   		return null;
   	}
   	 
    }
    
    private String readTextFromResource(int resourceID)
	{
		InputStream raw = getResources().openRawResource(resourceID);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		int i;
		try
		{
			i = raw.read();
			while (i != -1)
			{
				stream.write(i);
				i = raw.read();
			}
			raw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return stream.toString();
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
