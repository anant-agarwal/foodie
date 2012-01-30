package tabby.Using_tabs;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PublicActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("Public Timeline");
        setContentView(textview);
    }

}
