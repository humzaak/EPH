package com.magint.h.epicurefoodhunting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//import com.magint.h.epicurefoodhunting.MainActivity.Fragment_recipes;


/**
 *  recipes fragment.
 */
public class Fragment_recipes extends Fragment {
    
	
	// Array of strings storing country names
    String[] countries = new String[] {
            "India",
            "Pakistan",
            "Sri Lanka",
            "China",
            "Bangladesh",
            "Nepal",
            "Afghanistan",
            "North Korea",
            "South Korea",
            "Japan"
    };
    
    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flags = new int[]{
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
	
    // Array of strings to store currencies
    String[] currency = new String[]{
    	"Indian Rupee",
    	"Pakistani Rupee",
    	"Sri Lankan Rupee",
    	"Renminbi",
    	"Bangladeshi Taka",
    	"Nepalese Rupee",
    	"Afghani",
    	"North Korean Won",
    	"South Korean Won",
    	"Japanese Yen"
    };
    
	
	
	
	
	/**
     * The fragment argument representing the section number for this
     * fragment.
     */
private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
 public static Fragment_recipes newInstance(int sectionNumber) {
        Fragment_recipes fragment = new Fragment_recipes();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        
        
        return fragment;
    }

 public Fragment_recipes() {
    }       

 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
     View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);
     
     ListView listView = (ListView) rootView.findViewById(R.id.recipelist);

     /*
     String[] values = new String[] { "Android List View", 
             "Adapter implementation",
             "Simple List View In Android",
             "Create List View Android", 
             "Android Example", 
             "List View Source Code", 
             "List View Array Adapter", 
             "Android Example List View" 
            };
     */
     

     // Each row in the list stores country name, currency and flag
     List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();        
     
     for(int i=0;i<10;i++){
     	HashMap<String, String> hm = new HashMap<String,String>();
         hm.put("rt", "Country : " + countries[i]);
         hm.put("rd","Currency : " + currency[i]);
         hm.put("ri", Integer.toString(flags[i]) );            
         aList.add(hm);        
     }
     
     // Keys used in Hashmap
     String[] from = { "ri","rt","rd" };
     
     // Ids of views in listview_layout
     int[] to = { R.id.recipeimage,R.id.recipetitle,R.id.recipedesc};   
     
     SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.fragment_recipes_list, from, to);
     
     
  //   ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
      //       android.R.layout.activity_list_item, android.R.id.text1, values);
   
     listView.setOnItemClickListener(new OnItemClickListener()
     {
         @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
         { 
             Toast.makeText(getActivity().getApplicationContext(), "Stop Clicking me", Toast.LENGTH_SHORT).show();
             
         }
     });
   
           // Assign adapter to ListView
     listView.setAdapter(adapter); 
     
     
     return rootView;
 }
 

 
}

