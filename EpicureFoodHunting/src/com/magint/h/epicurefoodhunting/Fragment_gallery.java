package com.magint.h.epicurefoodhunting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

//import com.magint.h.epicurefoodhunting.MainActivity.Fragment_gallery;


        /**
         *  gallery fragment.
         */
    public class Fragment_gallery extends Fragment {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
    	
    	
    	
        String[] galleryT = new String[] {
                "Month 1",
                "Month 2",
                "Month 3",
                "Month 4",
                "Month 5",
                "Month 6",
                "Month 7",
                "Month 8",
                "Month 9",
                "Month 10"
        };
        
        int[] galleryI = new int[]{
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
    	
        
    	
    	
    	
    	
        private static final String ARG_SECTION_NUMBER = "section_number";

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
         public static Fragment_gallery newInstance(int sectionNumber) {
                Fragment_gallery fragment = new Fragment_gallery();
                Bundle args = new Bundle();
                args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                fragment.setArguments(args);
               
                return fragment;
            }

         public Fragment_gallery() {
            }
         
         
         
         @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
             View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
             
             Calendar c = Calendar.getInstance();
             int year = c.get(Calendar.YEAR);
             int month = c.get(Calendar.MONTH);
             
             int splityear = year%100;
             
             Button issues_btn2;
             issues_btn2 = (Button) rootView.findViewById(R.id.gallerybutton1);
             
             issues_btn2.setText(String.format(Locale.US,"%tB",c) +" " +String.valueOf(year) + " Edition out now" );
             
             
             final Button button2 = (Button) rootView.findViewById(R.id.gallerybutton1);
             button2.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
              	   String url = getString(R.string.epicureurl);
              	   Intent i = new Intent(Intent.ACTION_VIEW);
              	   i.setData(Uri.parse(url));
              	   startActivity(i);
                 }
             });
             
             final Button button3 = (Button) rootView.findViewById(R.id.gallerybutton2);
             button3.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
              	   String url = getString(R.string.epicureurl);
              	   Intent i = new Intent(Intent.ACTION_VIEW);
              	   i.setData(Uri.parse(url));
              	   startActivity(i);
                 }
             });
             
             
             
             
             ListView listView = (ListView) rootView.findViewById(R.id.gallerylist);

             

             // Each row in the list stores country name, currency and flag
             List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();        
             
             for(int i=0;i<10;i++){
             	HashMap<String, String> hm = new HashMap<String,String>();
                 hm.put("rt",  galleryT[i]);
                 hm.put("ri", Integer.toString(galleryI[i]) );            
                 aList.add(hm);        
             }
             
             // Keys used in Hashmap
             String[] from = { "ri","rt" };
             
             // Ids of views in listview_layout
             int[] to = { R.id.gallerylistimageView1,R.id.gallerylisttextView1};   
             
             SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.fragment_gallery_list, from, to);
             
             
          //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
              //       android.R.layout.activity_list_item, android.R.id.text1, values);
           
             listView.setOnItemClickListener(new OnItemClickListener()
             {
                 @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
                 { 
                    //Toast.makeText(getActivity().getApplicationContext(), "Stop Clicking me", Toast.LENGTH_SHORT).show();
                     
                     Intent intent = new Intent(getActivity(), Fragment_gallery_details.class);
                     startActivity(intent); 

                     
                 }
             });
           
                   // Assign adapter to ListView
             listView.setAdapter(adapter); 
             
             
             
             
             return rootView;
         }
     }
         