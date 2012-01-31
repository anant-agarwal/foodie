package spider.social.foodie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.FacebookError;

public class FriendActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("Friends Timeline");
        setContentView(textview);
      
       /* mAsyncRunner.logout(this, new RequestListener(){

        	public void onComplete(String response, Object state) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void onIOException(IOException e, Object state) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void onFileNotFoundException(FileNotFoundException e, Object state) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void onMalformedURLException(MalformedURLException e, Object state) {
        		// TODO Auto-generated method stub
        		
        	}

        	public void onFacebookError(FacebookError e, Object state) {
        		// TODO Auto-generated method stub
        		
        	}
        	
        });*/
        loginActivity.mAsyncRunner.request("anantinvent/posts", new RequestListener(){

			public void onComplete(String response, Object state) {
				//System.out.println(response);
				JSONObject json=null;
				try{
						json= new JSONObject(response);
						JSONArray jsonarr=json.getJSONArray("story");
						int length=(jsonarr==null)? 0:jsonarr.length();
						for(int i=0;i<length;i++)
						{
							JSONObject o = jsonarr.getJSONObject(i);
							System.out.println(o.getString("story"));
						}
				}
				catch(JSONException e)
				{
					Log.e("log_tag","error parsing"+e.toString());
					
				}
				
				
				//TextView textview = new TextView(this);
		        //textview.setText("Friends Timeline");
				System.out.println(response);
			}

			public void onIOException(IOException e, Object state) {
				// TODO Auto-generated method stub
				
			}

			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
				// TODO Auto-generated method stub
				
			}

			public void onMalformedURLException(MalformedURLException e,
					Object state) {
				// TODO Auto-generated method stub
				
			}

			public void onFacebookError(FacebookError e, Object state) {
				// TODO Auto-generated method stub
				
			}
        	
        	
        });
    }

}
