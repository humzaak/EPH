package com.magint.h.epicurefoodhunting;

//import com.example.android.animationsdemo.MainActivity;

import com.facebook.Session;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
	private static final int RESULT_OK = -1;
	private static final int RESULT_CANCELED = 1;
	private TextView userName;
	private TextView welCome;

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
     
     Session session = Session.getActiveSession();
     if (session != null) {

         if (!session.isClosed()) {
             session.closeAndClearTokenInformation();
             Log.d("se","seesion is opened");
             //clear your preferences if saved
         }
         else{
        	 
             Log.d("se","seesion is closed");

         }
     }
     else{
    	 
         Log.d("se","seesion is null");

    	 
     }
     
     
     

     final Button button = (Button) rootView.findViewById(R.id.tutorialbutton);
     button.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
               startActivity(new Intent(getActivity(), TutorialActivity.class));
               
         }
     });
     
     final Button button2 = (Button) rootView.findViewById(R.id.loginbutton);
     button2.setOnClickListener(new View.OnClickListener() {
         public void onClick(View v) {
               startActivityForResult(new Intent(getActivity(), LoginActivity.class),1);

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
 
 public void onActivityResult(int requestCode, int resultCode, Intent data) {

	    if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	            String result=data.getStringExtra("result");
	            Log.d("lk",result);
	    		userName = (TextView) getActivity().findViewById(R.id.usernametextView);
				userName.setText(result);
				
				welCome = (TextView) getActivity().findViewById(R.id.welcometextView);
				welCome.setText("Welcome");
				
			    final Button button_login = (Button) getView().findViewById(R.id.loginbutton);
			    button_login.setText("Logout");

	        }
	        if (resultCode == RESULT_CANCELED) {
	            //Write your code if there's no result
	        }
	    }
	}
 
 
}