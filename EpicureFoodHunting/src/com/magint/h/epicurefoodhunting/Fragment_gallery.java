package com.magint.h.epicurefoodhunting;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.magint.h.epicurefoodhunting.MainActivity.Fragment_gallery;


        /**
         *  gallery fragment.
         */
    public class Fragment_gallery extends Fragment {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
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
             View rootView = inflater.inflate(R.layout.fragment_main, container, false);
             return rootView;
         }
     }
         