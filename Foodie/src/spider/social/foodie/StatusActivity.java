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
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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
		  String status = statusText.getText().toString();   
		  statusText.setText(Place);
		  loginActivity.mAsyncRunner.request("anantinvent/posts", new RequestListener(){

				public void onComplete(String response, Object state) {
					//System.out.println(response);
					
					try{
							/*json= new JSONObject(response);
							JSONArray jsonarr=json.getJSONArray("story");
							int length=(jsonarr==null)? 0:jsonarr.length();
							for(int i=0;i<length;i++)
							{
								JSONObject o = jsonarr.getJSONObject(i);
								System.out.println(o.getString("story"));
							}*/
							JSONObject json=new JSONObject(response);
							JSONArray jsonarr=json.getJSONArray("data");
							
							int length=(jsonarr==null)? 0:jsonarr.length();
							for(int i=0;i<length;i++)
							{
								JSONObject o = jsonarr.getJSONObject(i);
								if(o.has("story"))
								{
									System.out.println(o.getString("story"));
								}
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

		  final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.CheckBoxResponse);
		  boolean bRequiresResponse = responseCheckbox.isChecked();
      }
}
