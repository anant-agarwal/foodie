package spider.social.foodie;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;


public class loginActivity extends Activity{
	 static Facebook facebook = new Facebook("281912308536270");
	 
	 static AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);
	
	 String FILENAME = "AndroidSSO_data";
	 private SharedPreferences mPrefs;
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("please Login");
        setContentView(textview);
        mPrefs = getPreferences(MODE_PRIVATE);
        String access_token = mPrefs.getString("access_token", null);
        long expires = mPrefs.getLong("access_expires", 0);
        if(access_token != null) {
            facebook.setAccessToken(access_token);
        }
        if(expires != 0) {
            facebook.setAccessExpires(expires);
        }
        
        facebook.authorize(this, new String[]{"publish_stream"},new DialogListener() {
       
        	public void onComplete(Bundle values) {
        		 SharedPreferences.Editor editor = mPrefs.edit();
                 editor.putString("access_token", facebook.getAccessToken());
                 editor.putLong("access_expires", facebook.getAccessExpires());
                 editor.commit();
        		 Intent intent = new Intent();
        		 intent.setClassName("spider.social.foodie", "spider.social.foodie.Using_tabsActivity"); 
        		 startActivity(intent);
        	}

       
            public void onFacebookError(FacebookError error) {}

            public void onError(DialogError e) {            
            	
            }

     
            public void onCancel() {finish();}
        });
    }
	 @Override
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);

	        facebook.authorizeCallback(requestCode, resultCode, data);
	    }
	 
	 /*protected void onResume()
	 {
		 super.onResume();
		 if(facebook.isSessionValid())
		 {	
			Intent intent = new Intent();
		 	intent.setClassName("tabby.Using_tabs", "tabby.Using_tabs.Using_tabsActivity"); 
		 	startActivity(intent);
		 }
	 }*/
	 protected void onRestart()
	 {
		 super.onRestart();
		 finish();
	 }

}
