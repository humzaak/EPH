package com.magint.h.epicurefoodhunting;

//import com.example.android.animationsdemo.MainActivity;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.magint.h.epicurefoodhunting.MainActivity.Fragment_more;



/**
 *  fragment more.
 */
public  class Fragment_more extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
 public static Fragment_more newInstance(int sectionNumber) {
        Fragment_more fragment = new Fragment_more();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

 public Fragment_more() {
    }       


 
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
     View rootView = inflater.inflate(R.layout.fragment_more, container, false);
     

     final Button button = (Button) rootView.findViewById(R.id.tutorialbutton);
     button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
               startActivity(new Intent(getActivity(), TutorialActivity.class));
               
         }
     });
     
     final Button button1 = (Button) rootView.findViewById(R.id.aboutbutton);
     button1.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
        	 String url = getString(R.string.epicureurl);
        	 Intent i = new Intent(Intent.ACTION_VIEW);
        	 i.setData(Uri.parse(url));
        	 startActivity(i);               
         }
     });
     
     
     return rootView;
 }
}