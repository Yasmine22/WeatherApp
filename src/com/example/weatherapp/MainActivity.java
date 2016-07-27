package com.example.weatherapp;

import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import generics.HTTPAdapter;
import generics.NetworkConnection;
import generics.sharedprefs;


public class MainActivity extends ActionBarActivity {
	NetworkConnection forecast_checknetwork;
	public String forecast_api_url ;
	HTTPAdapter forecast_httpadapter ;
	ListView forecast_listview;
	ForecastAdapter forecast_adapter;
	ArrayList<ListObjModel> forecast_list;
	ListObjModel forecast_obj;
	TempObjModel forecast_temp;
	String city;
	String units;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (getIntent().getBooleanExtra("LOGOUT", false))
		{
		    finish();
		}
		
		
		forecast_listview = (ListView) findViewById(R.id.forecast_listview);
		
		forecast_list = new ArrayList<ListObjModel>();
		
		city = sharedprefs.getDefaults("city", getApplicationContext());
		

		
		if(!sharedprefs.containKey(getApplicationContext(),"units")){
			units = "imperial";
			sharedprefs.setDefaults("units", units, getApplicationContext());
		}else{
			units = sharedprefs.getDefaults("units", getApplicationContext());
		}
		
		
		forecast_api_url = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+city+"&mode=json&units="+units+"&cnt=7&APPID=ae56fd6cae258d4dad4223edee3fb2fd";
		
		forecast_httpadapter = new HTTPAdapter();

        
        forecast_checknetwork = new NetworkConnection(getApplicationContext());
		
	     boolean connection = forecast_checknetwork.isNetworkOnline();
	     if(connection==true)
	     { 
	    		new ForecastAsynTask().execute(); 

	    		
	     }				
	     else
			{
				Toast.makeText(getApplicationContext(),"please check your internet connection", Toast.LENGTH_LONG).show();
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			Intent myIntent = new Intent(MainActivity.this, SettingActivity.class);
			startActivity(myIntent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent = new Intent(getApplicationContext(), MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("LOGOUT", true);
		startActivity(intent);
	}

	public class ForecastAsynTask extends AsyncTask<String, String, JSONObject>
	{
		
		public  ProgressDialog pDialog;
		public JSONArray forecast_json_array;
//			
				protected void onPreExecute() {
					super.onPreExecute();

					 pDialog = new ProgressDialog(MainActivity.this);
			            pDialog.setMessage("please wait . . . ");
			            pDialog.setIndeterminate(false);
			            pDialog.setCancelable(true);
			            pDialog.show();		
				}

		@Override
		protected JSONObject doInBackground(String... arg0)
		{
			
			JSONObject json = null;
		
			boolean serverconnect = forecast_checknetwork.isServerAvaliable(forecast_api_url);
			if(serverconnect==true)
			{	
			 json = forecast_httpadapter.makeHttpRequestWithNoParams(forecast_api_url);
			 
			
			 
			}
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			
			int error = 0 ;
			pDialog.dismiss();
			
			if(result != null){
				try {
					forecast_json_array = result.getJSONArray("list");
				
					JSONObject jObj ;
					JSONObject tempObj;
					
					for(int i = 0 ; i < forecast_json_array.length(); i++){
						
						jObj = forecast_json_array.getJSONObject(i);
						
						tempObj = jObj.getJSONObject("temp");
						
						forecast_temp = new TempObjModel();
						
						forecast_temp.setDay(tempObj.getDouble("day"));
						forecast_temp.setMin(tempObj.getDouble("min"));
						forecast_temp.setMax(tempObj.getDouble("max"));
						forecast_temp.setNight(tempObj.getDouble("night"));
						forecast_temp.setEve(tempObj.getDouble("eve"));
						forecast_temp.setMorn(tempObj.getDouble("morn"));
						
						forecast_obj = new ListObjModel();
						
						
						
						forecast_obj.setDt(jObj.getInt("dt"));
						forecast_obj.setTemp(forecast_temp);
						forecast_obj.setPressure(jObj.getDouble("pressure"));
						forecast_obj.setHumidity(jObj.getDouble("humidity"));
						forecast_obj.setSpeed(jObj.getDouble("speed"));
						forecast_obj.setDeg(jObj.getDouble("deg"));
						forecast_obj.setClouds(jObj.getDouble("clouds"));
						
						forecast_list.add(forecast_obj);
					}
				
					forecast_listview.setAdapter(new ForecastAdapter(forecast_list, getApplicationContext()));
				
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
			
			

		}
		
		
		
		
		
	}
}
