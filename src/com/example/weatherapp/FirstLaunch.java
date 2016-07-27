package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import generics.sharedprefs;

public class FirstLaunch extends ActionBarActivity{
	Spinner city_spinner;
	Button continue_btn;
	String city;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_launch);
		
		if(sharedprefs.containKey(getApplicationContext(),"city")){
			Intent myIntent = new Intent(FirstLaunch.this, MainActivity.class);
			startActivity(myIntent);
			finish();
		}else{
			
		
			city_spinner = (Spinner) findViewById(R.id.city_spinner);
			
			continue_btn = (Button) findViewById(R.id.continue_btn);
			
			continue_btn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					city = city_spinner.getSelectedItem().toString();
					
					sharedprefs.setDefaults("city", city, getApplicationContext());
					Intent myIntent = new Intent(FirstLaunch.this, MainActivity.class);
					startActivity(myIntent);
					finish();
					
				}
			});
		}
	}
	

}
