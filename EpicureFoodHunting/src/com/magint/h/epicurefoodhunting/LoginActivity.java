package com.magint.h.epicurefoodhunting;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.*;
import com.facebook.model.*;
import com.facebook.widget.LoginButton;

public class LoginActivity extends Activity {

	
	//hello
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.login_signup_dialog);

	        final ActionBar actionBar = getActionBar();
	        actionBar.setHomeButtonEnabled(true); 
        
	       LoginButton authButton = (LoginButton) findViewById(R.id.facebook_login_button);
	        authButton.setReadPermissions(Arrays.asList("public_profile","email"));
	        
	        authButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
	            @Override
	            public void onUserInfoFetched(GraphUser user) {
	                // It's possible that we were waiting for this.user to be populated in order to post a
	                // status update.
	            	updateUI(user);
	            	if(user!=null)
	            	{

	            	}
	            	else{
	            		
	            		Log.d("username1","you are not logged in");
	            	}
	            
	            }
	            
	           
	        });
	        
	      
	        
	        
	        
	     /*   

	      Session.openActiveSession(this, true, new Session.StatusCallback() {

	          // callback when session changes state
	         // @Override
	          public void call(Session session, SessionState state, Exception exception) {
	            
	        	  if (session.isOpened()) {
  
	              // make request to the /me API
	              Request.newMeRequest(session, new Request.GraphUserCallback() {
	            	  
	                // callback after Graph API response with user object
	                @Override
	                public void onCompleted(GraphUser user, Response response) {
	                  if (user != null) {
	                    //TextView welcome = (TextView) findViewById(R.id.welcome);
	                    //welcome.setText("Hello " + user.getName() + "!");
	                	 // Log.d("username",user.getFirstName());
	                	 // Log.d("username",(String) user.asMap().get("email"));
	                	 // Intent returnIntent = new Intent();
	                	  //returnIntent.putExtra("result",user.getFirstName());
	                	  //setResult(RESULT_OK,returnIntent);
	                	 // finish();
	                  }
	                  else{
		            		
		            		Log.d("username2","you are not logged in");
		            	}
	                  
	                }
	              }).executeAsync();
	            }
	          }
	        });
	        */
	        
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
	    
	    @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	    }

	    private void updateUI(GraphUser user){
	    	 Session session = Session.getActiveSession();
	    	 if(session != null && session.isOpened() && user != null){
	    		 
            	  Intent returnIntent = new Intent();
           	      returnIntent.putExtra("result",user.getFirstName());
           	      setResult(RESULT_OK,returnIntent);
           	      finish();
	    	 }
	    	}
	
}
