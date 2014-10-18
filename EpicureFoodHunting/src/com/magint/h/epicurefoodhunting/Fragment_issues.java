package com.magint.h.epicurefoodhunting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.magint.h.epicurefoodhunting.MainActivity.Fragment_issues;

/**
 *  issues fragment.
 */
public class Fragment_issues extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static Fragment_issues newInstance(int sectionNumber) {
        Fragment_issues fragment = new Fragment_issues();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        
     
        
        return fragment;
    }

    public Fragment_issues() {
    }
    
    
    public Bitmap generateBitmap(String url){
        Bitmap bitmap_picture = null;
       

        int intentos = 0;
        boolean exception = true;
        while((exception) && (intentos < 3)){
            try {
                URL imageURL = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) imageURL.openConnection();
                conn.connect();
                InputStream bitIs = conn.getInputStream();
                if(bitIs != null){
                    bitmap_picture = BitmapFactory.decodeStream(bitIs);
                    exception = false;
                }else{
                    Log.e("InputStream", "null");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                exception = true;
            } catch (IOException e) {
                e.printStackTrace();
                exception = true;
            }
            intentos++;
        }

        return bitmap_picture;
    }
    
    
    @SuppressWarnings("deprecation")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        
        int splityear = year%100;
        
        Button issues_btn;
        issues_btn = (Button) rootView.findViewById(R.id.button1);
        
        Bitmap issues_image;
        

        
        
        DownloadThumbnailTask task_thumb = new DownloadThumbnailTask();
        task_thumb.execute(new String[] { "http://www.epicureasia.com/img/covers/"+String.valueOf(month+1)+String.valueOf(splityear)+".jpg" });     
         
       try {
    	   issues_image =  task_thumb.get();
   		BitmapDrawable bitmapDrawable = new BitmapDrawable(null,issues_image);//(BitmapDrawable)issues_image;
        Drawable issues_image_drawable = (Drawable)bitmapDrawable;
        issues_btn.setBackgroundDrawable(issues_image_drawable);
       	} catch (InterruptedException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
       		} catch (ExecutionException e) {
       			// TODO Auto-generated catch block
       			e.printStackTrace();
       		}

        Button issues_btn2;
        issues_btn2 = (Button) rootView.findViewById(R.id.button2);
        
        issues_btn2.setText(String.format(Locale.US,"%tB",c) +" " +String.valueOf(year) + " Edition out now" );
        issues_btn2.setTextColor(-1 );
        issues_btn2.setBackgroundColor(-16777216);
        
        Button issues_btn3;
        issues_btn3 = (Button) rootView.findViewById(R.id.button3);
        issues_btn3.setTextColor(-1 );
        issues_btn3.setBackgroundColor(-16777216);
        
        return rootView;
    }
    
    
    private class DownloadThumbnailTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
	        Bitmap issues_image;
			issues_image = generateBitmap(params[0]);
			return issues_image;
		}
   
    }
    
    
}
    