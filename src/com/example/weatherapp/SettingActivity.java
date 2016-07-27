package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import generics.sharedprefs;

public class SettingActivity extends ActionBarActivity{
	Spinner city_spinner;
	String city;
	Spinner units_spinner;
	String units;
	Button save_btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_activity);
		
		
	    
		city_spinner = (Spinner) findViewById(R.id.setting_city_spinner);
		
		
		
		units_spinner = (Spinner) findViewById(R.id.setting_degree_spinner);
		
		
		save_btn = (Button) findViewById(R.id.save);
		
		save_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				city = city_spinner.getSelectedItem().toString();
				
				sharedprefs.setDefaults("city", city, getApplicationContext());
				
				units = units_spinner.getSelectedItem().toString();
				if(units == "F")
					sharedprefs.setDefaults("units", "imperial", getApplicationContext());
				else
					sharedprefs.setDefaults("units", "metric", getApplicationContext());
				Intent myIntent = new Intent(SettingActivity.this, MainActivity.class);
				startActivity(myIntent);
				finish();
				
			}
		});
		
	}
	

}
