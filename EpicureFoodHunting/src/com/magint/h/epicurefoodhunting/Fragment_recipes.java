package com.magint.h.epicurefoodhunting;


import java.util.ArrayList;
import java.util.List;


import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.koushikdutta.urlimageviewhelper.UrlImageViewCallback;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.magint.h.epicurefoodhunting.soap.SoapFactory;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

//import com.magint.h.epicurefoodhunting.MainActivity.Fragment_recipes;

class RecipieListAdaptor extends BaseAdapter
{
	
	private ArrayList<RecipeListItem> navDrawerItems;
	Context mContext;
	 public RecipieListAdaptor(ArrayList<RecipeListItem> navDrawerItems, Context context) {
	        this.navDrawerItems=navDrawerItems;
	        this.mContext=context;
	    }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return navDrawerItems.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return navDrawerItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		 if (convertView == null) {
	            LayoutInflater mInflater = (LayoutInflater)
	            		mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	            convertView = mInflater.inflate(R.layout.fragment_recipes_list, null);
	        }
	        
		 ImageView imgIcon = (ImageView) convertView.findViewById(R.id.recipeimage);
	        TextView txtTitle = (TextView) convertView.findViewById(R.id.recipetitle);
	        TextView txtCount = (TextView) convertView.findViewById(R.id.recipedesc);
	   
				 
	        txtTitle.setText(navDrawerItems.get(position).getTitle());
	        txtCount.setText(navDrawerItems.get(position).getIntroText());
	        imgIcon.setAnimation(null);
	        UrlImageViewHelper.setUrlDrawable(imgIcon, navDrawerItems.get(position).getImgURL(), R.drawable.loading, new UrlImageViewCallback() {
	            @Override
	            public void onLoaded(ImageView imageView, Bitmap loadedBitmap, String url, boolean loadedFromCache) {
	                if (!loadedFromCache) {
	                    ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
	                    scale.setDuration(300);
	                    scale.setInterpolator(new OvershootInterpolator());
	                    imageView.startAnimation(scale);
	                }
	            }
	        });
	        return convertView;
	}
	
}



/**
 *  recipes fragment.
 */
public class Fragment_recipes extends Fragment {
    
	
	
	/**
     * The fragment argument representing the section number for this
     * fragment.
     */
private static final String ARG_SECTION_NUMBER = "section_number";
List<String> RecipeIDList= new ArrayList<String>();

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

 private ArrayList<RecipeListItem> RecipeListItems;
 ListView listView;
 RecipieListAdaptor mRecipieListAdaptor;
 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
     View rootView = inflater.inflate(R.layout.fragment_recipes, container, false);
     
     listView = (ListView) rootView.findViewById(R.id.recipelist);
     RecipeListItems = new ArrayList<RecipeListItem>();
     
     listView.setOnItemClickListener(new OnItemClickListener()
     {
         @Override public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
         { 
             Intent intent = new Intent(getActivity(), Fragment_recipes_details.class);
             intent.putExtra("recipeID", RecipeIDList.get(position));
             startActivity(intent); 

             
         }
     });
     new SoapAsync().execute("");
    
	 mRecipieListAdaptor = new RecipieListAdaptor(RecipeListItems,getActivity().getApplicationContext());
     // Assign adapter to ListView

     
     return rootView;
 }
 

 
 public class SoapAsync extends AsyncTask<String, String, String>
 {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		
	     Document doc= SoapFactory.getRecipeListDocuemnt(getActivity().getApplicationContext());	       
	       Elements _elementsRecipieThumbs=doc.getElementsByTag("thumb");
	        Elements _elementsRecipieTitle=doc.getElementsByTag("title");
	        Elements _elementsRecipieIntroText=doc.getElementsByTag("intro_text");
	        Elements _elementsRecipieID=doc.getElementsByTag("id");
	        for(int i=0; i< Math.min(Math.min(_elementsRecipieThumbs.size(),_elementsRecipieTitle.size()),_elementsRecipieIntroText.size()); i++)
	        {
	        	RecipeListItems.add(new RecipeListItem(_elementsRecipieTitle.get(i).text(), _elementsRecipieIntroText.get(i).text(),"http://epicureasia.com/app/webroot/img/recipes/"+ _elementsRecipieThumbs.get(i).text()));
	        	RecipeIDList.add(_elementsRecipieID.get(i).text());
	        }
			return null;
		}
		
		@Override
		 protected void onPostExecute(String result) {
				 listView.setAdapter(mRecipieListAdaptor); 
		 }
 }
 

 
}

