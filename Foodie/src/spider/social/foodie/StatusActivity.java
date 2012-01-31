package spider.social.foodie;

import android.app.Activity;
import android.os.Bundle;
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
		  
		  final CheckBox responseCheckbox = (CheckBox) findViewById(R.id.CheckBoxResponse);
		  boolean bRequiresResponse = responseCheckbox.isChecked();
      }
}
