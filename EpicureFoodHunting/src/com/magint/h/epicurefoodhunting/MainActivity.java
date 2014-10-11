package com.magint.h.epicurefoodhunting;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        


        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0)
            	return Fragment_issues.newInstance(position+1);
            else if (position == 1)
            	return Fragment_gallery.newInstance(position+1);
            else if (position == 2)
            	return Fragment_hunt.newInstance(position+1);
            else if (position == 3)
            	return Fragment_recipes.newInstance(position+1);            
            else
                return Fragment_more.newInstance(position+1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
                case 4:
                    return getString(R.string.title_section5).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     *  issues fragment.
     */
    public static class Fragment_issues extends Fragment {
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
            
            
            Button issues_btn;
            issues_btn = (Button) rootView.findViewById(R.id.button1);
            
            Bitmap issues_image;
            issues_image = generateBitmap("http://www.epicureasia.com/img/covers/1014.jpg");
            
			BitmapDrawable bitmapDrawable = new BitmapDrawable(null,issues_image);//(BitmapDrawable)issues_image;
            Drawable issues_image_drawable = (Drawable)bitmapDrawable;
            
            issues_btn.setBackgroundDrawable(issues_image_drawable);
            
            
            
            
            return rootView;
        }
    }
        
        
    

    
    

        /**
         *  gallery fragment.
         */
    public static class Fragment_gallery extends Fragment {
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
         
      
         /**
          *  hunt fragment.
          */
     public static class Fragment_hunt extends Fragment {
             /**
              * The fragment argument representing the section number for this
              * fragment.
              */
         private static final String ARG_SECTION_NUMBER = "section_number";

             /**
              * Returns a new instance of this fragment for the given section
              * number.
              */
          public static Fragment_hunt newInstance(int sectionNumber) {
                 Fragment_hunt fragment = new Fragment_hunt();
                 Bundle args = new Bundle();
                 args.putInt(ARG_SECTION_NUMBER, sectionNumber);
                 fragment.setArguments(args);
                 return fragment;
             }

          public Fragment_hunt() {
             }       
        
          
          @Override
          public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
              View rootView = inflater.inflate(R.layout.fragment_main, container, false);
              return rootView;
          }
      }
        
  
     /**
      *  recipes fragment.
      */
 public static class Fragment_recipes extends Fragment {
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
          View rootView = inflater.inflate(R.layout.fragment_main, container, false);
          return rootView;
      }
  }
     
  
 
 /**
  *  recipes more.
  */
public static class Fragment_more extends Fragment {
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
      View rootView = inflater.inflate(R.layout.fragment_main, container, false);
      return rootView;
  }
}
        
    

}
