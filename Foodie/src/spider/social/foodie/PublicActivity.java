package spider.social.foodie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.FacebookError;
import com.facebook.android.AsyncFacebookRunner.RequestListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PublicActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("Public Timeline");
        setContentView(textview);
        loginActivity.mAsyncRunner.request("FoodieBase/feed",new RequestListener(){

			public void onComplete(String response, Object state) {
				//System.out.println(response);
				String [][]feeds=new String[20][2];
				try{
						JSONObject json=new JSONObject(response);
						JSONArray jsonarr=json.getJSONArray("data");
						
						int length=(jsonarr==null)? 0:jsonarr.length();
						for(int i=0;i<length && i<20;i++)
						{
							JSONObject o = jsonarr.getJSONObject(i);
							if(o.has("message"))
							{
								feeds[i][1]=o.getJSONObject("from").getString("name");
								feeds[i][0]=o.getString("message");
								//System.out.println(feeds[i][0]+feeds[i][1]);
							}
						}
				}
				catch(JSONException e)
				{
					Log.e("log_tag","error parsing"+e.toString());
					
				}
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
