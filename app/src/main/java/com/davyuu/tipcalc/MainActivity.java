package com.davyuu.tipcalc;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

	EditText billText;
	EditText tipText;
	TextView tipTextView;
	TextView totalTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Do Something", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});


		billText = (EditText) findViewById(R.id.billText);
		tipText = (EditText) findViewById(R.id.tipText);
		tipTextView = (TextView) findViewById(R.id.tipTotalTextView);
		totalTextView = (TextView) findViewById(R.id.totalTextView);

		final Context context = this.getApplicationContext();
		final DecimalFormat format = new DecimalFormat("0.00");
		final Vibrator vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
		final InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		/*billText.addTextChangedListener(new TextWatcher() {
			private String current = "";
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				if(!s.toString().equals(current)){
					billText.removeTextChangedListener(this);

					String formatted = format.format(Float.parseFloat(s.toString()));
					current = formatted;

					billText.setText(formatted);
					billText.addTextChangedListener(this);
				}
			}
		});*/

		Button calculateButton = (Button) findViewById(R.id.calculateButton);
		calculateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view){
				vibrator.vibrate(100);

				if(billText.getText().toString().matches("")){
					Toast.makeText(context, "Enter the bill amount", Toast.LENGTH_SHORT).show();
					return;
				}
				if(tipText.getText().toString().matches("")){
					Toast.makeText(context, "Enter the tip amount", Toast.LENGTH_SHORT).show();
					return;
				}

				float bill = Float.parseFloat(billText.getText().toString());
				float tipPercent = Float.parseFloat(tipText.getText().toString());
				float tipDecimal = tipPercent / 100;
				float tip = bill * tipDecimal;
				float total = bill + tip;
				tipTextView.setText(format.format(tip));
				totalTextView.setText(format.format(total));
				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
