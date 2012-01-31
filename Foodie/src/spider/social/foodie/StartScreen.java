package spider.social.foodie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class StartScreen extends Activity{
	@Override
	public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	
		setContentView(R.layout.startscreen);
		
		ImageView login = (ImageView) findViewById(R.id.fblogin);
		
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View V) {
				Intent i = new Intent(StartScreen.this, loginActivity.class);
                startActivity(i);
                finish();
			}
		});
	}

}
