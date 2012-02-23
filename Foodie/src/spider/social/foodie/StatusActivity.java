package spider.social.foodie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StatusActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("Like Something! Share It!");
        setContentView(R.layout.test);
      
	}
	  public void statusUpdate(View button) {
		  final Spinner feedbackSpinner = (Spinner) findViewById(R.id.SpinnerPlaces);
		  String Place = feedbackSpinner.getSelectedItem().toString();
		  
		  final EditText statusText = (EditText) findViewById(R.id.EditTextCommentBody);
		 final String status = statusText.getText().toString()+"@"+Place;   
		 
		  //statusText.setText(Place);
		 Bundle params=new Bundle();
		//parameters.putString("description",status);
		params.putString("description","Powered By:Foodie");
	   //params.putString("name", "appname");
	    params.putString("captions", "Via Foodie!!!");
	    //params.putString("link", "http://www.google.com");
	    params.putString("message", status);
	    try {
			String response=loginActivity.facebook.request("FoodieBase/feed", params, "POST");
			System.out.println(response);
			JSONObject json= new JSONObject(response);
			if(json.has("id"))
			{
				Toast.makeText(getBaseContext(),"Successfullly Shared :)",1000).show();
				
			}
			else
				Toast.makeText(getBaseContext(),"Unable to share,pls try again later",1000).show();
	    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    	
	    /*loginActivity.facebook.dialog(this,"feed",params,new DialogListener(){
	    	
			@Override
			public void onComplete(Bundle values) {
				// TODO Auto-generated method stub
					/*final String postid=values.getString("post_id");
					if(postid != null)
						Toast.makeText(getBaseContext(),"Successfullly posted",1000).show();
					else
						Toast.makeText(getBaseContext(),"Unable to post,pls try again later",1000).show();
					*
			}

			@Override
			public void onFacebookError(FacebookError e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(DialogError e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub
				
			}});*/
		  final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.CheckBoxResponse);
		  boolean bRequiresResponse = responseCheckbox.isChecked();
		  if(bRequiresResponse)
		  {
			  	Bundle para_me=new Bundle();
				//parameters.putString("description",status);
				para_me.putString("description","Powered By:Foodie");
			   //params.putString("name", "appname");
			    para_me.putString("captions", "Via Foodie!!!");
			    //params.putString("link", "http://www.google.com");
			    para_me.putString("message", status);
			    try {
					String response=loginActivity.facebook.request("me/feed", para_me, "POST");
					System.out.println(response);
					JSONObject json= new JSONObject(response);
					if(json.has("id"))
					{
						Toast.makeText(getBaseContext(),"Successfullly posted :)",1000).show();
						
					}
					else
						Toast.makeText(getBaseContext(),"Unable to post,pls try again later",1000).show();
			    } catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
		  }
      }
}
