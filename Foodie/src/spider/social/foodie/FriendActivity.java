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
    
	 	loginActivity.mAsyncRunner.request("me/friends", new RequestListener(){

			public void onComplete(String response,Object state) {
				System.out.println(response);
					 String []friends=new String[500];
					int count=0;
					 int nof=0;
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
							if(o.has("name"))
							{
								friends[i]=o.getString("id");
								System.out.println(friends[i]);
								nof++;
								//System.out.println(o.getString("name"));
							}
						}
					
				}
				catch(JSONException e)
				{
					Log.e("log_tag","error parsing"+e.toString());
					
				}
				try {
					String resp=loginActivity.facebook.request("FoodieBase/feed",new Bundle(),"GET");
					//System.out.println(resp);
					JSONObject json=new JSONObject(response);
					JSONArray jsonarr=json.getJSONArray("data");
					String []friend_feeds=new String[20];
					int length=(jsonarr==null)? 0:jsonarr.length();
					int k=0;
					for(int i=0;i<length && k<20;i++)
					{
						JSONObject o = jsonarr.getJSONObject(i);
						if(o.has("message"))
						{
							String tmp=o.getJSONObject("from").getString("id");
								int flag=0;
							for(int j=0;j<nof;j++)
							{
								if(tmp==friends[j])
								{flag=1;break;}	
							}
							if(flag==1){friend_feeds[k++]=o.getJSONObject("from").getString("id")+o.getString("message");}
							System.out.println(friend_feeds[k]);
						}
					}
					System.out.println(resp);
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
				
				
				
				/*nof=count;
		        loginActivity.mAsyncRunner.request("FoodieBase/feed",new Bundle(),new RequestListener(){

					public void onComplete(String response, Object state) {
						System.out.println(response);
							String [] friend_feeds=new String[20];
						try{
								/*json= new JSONObject(response);
								JSONArray jsonarr=json.getJSONArray("story");
								int length=(jsonarr==null)? 0:jsonarr.length();
								for(int i=0;i<length;i++)
								{
									JSONObject o = jsonarr.getJSONObject(i);
									System.out.println(o.getString("story"));
								}*
								JSONObject json=new JSONObject(response);
								JSONArray jsonarr=json.getJSONArray("data");
								
								int length=(jsonarr==null)? 0:jsonarr.length();
								int k=0;
								for(int i=0;i<length && k<20;i++)
								{
									JSONObject o = jsonarr.getJSONObject(i);
									if(o.has("message"))
									{
										String tmp=o.getJSONObject("from").getString("id");
											int flag=0;
										for(int j=0;j<nof;j++)
										{
											if(tmp==friends[j])
											{flag=1;break;}	
										}
										if(flag==1){friend_feeds[k++]=o.getJSONObject("from").getString("id")+o.getString("message");}
										System.out.println(friend_feeds[k]);
									}
								}
						}
						catch(JSONException e)
						{
							Log.e("log_tag","error parsing"+e.toString());
							
						}
						
						
						//TextView textview = new TextView(this);
				        //textview.setText("Friends Timeline");
						//System.out.println(response);
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
		        	
		        	
		        });*/

				//TextView textview = new TextView(this);
		        //textview.setText("Friends Timeline");
				//System.out.println(response);
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
