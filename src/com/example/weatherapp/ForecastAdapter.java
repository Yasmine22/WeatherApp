package com.example.weatherapp;

import java.util.ArrayList;

import com.squareup.picasso.Picasso;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ForecastAdapter  extends BaseAdapter {
	
	ArrayList<ListObjModel> forecastListModel;
	private LayoutInflater forecast_layoutInflater;
	public OnClickListener forecast_listener;
	private Context forecast_Context;
	private ArrayList<String> days_arraylist;
	private TempObjModel forecast_temp;
	LayoutParams params ;

	public ForecastAdapter(ArrayList<ListObjModel> forecastListModel, Context forecast_Context) {
		super();
		this.forecastListModel = forecastListModel;
		forecast_layoutInflater = LayoutInflater.from(forecast_Context);
		this.forecast_Context = forecast_Context;
		days_arraylist = new ArrayList<String>();
		days_arraylist.add("Sunday");
		days_arraylist.add("Monday");
		days_arraylist.add("Tuesday");
		days_arraylist.add("Wedensday");
		days_arraylist.add("Thuresday");
		days_arraylist.add("Friday");
		days_arraylist.add("Saturday");
		
	}
	
	public void setButtonListener(OnClickListener forecast_listener) {
		this.forecast_listener = forecast_listener;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return forecastListModel.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return forecastListModel.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder holder;
		final ListObjModel forecast_obj_model = (ListObjModel)forecastListModel.get(position);
		
		forecast_temp = forecast_obj_model.getTemp();
		
		if (convertView == null) 
		{
			convertView = forecast_layoutInflater.inflate(R.layout.forecast_list_item, null);
			holder = new ViewHolder();

			holder.forecast_text=(TextView) convertView.findViewById(R.id.name);
			holder.forecast_image=(ImageView) convertView.findViewById(R.id.img);
			holder.forecast_date=(TextView) convertView.findViewById(R.id.date);
			
			

			
			convertView.setTag(holder);

		} 
		else 
		{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.forecast_text.setText("Max : "+forecast_temp.getMax() +"/Min : "+forecast_temp.getMin());
		
		int date_stamp = forecast_obj_model.getDt();
		java.util.Date date = new java.util.Date((long)date_stamp*1000);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(days_arraylist.get(date.getDay()));
		stringBuilder.append("  ");
		stringBuilder.append(date.getDate());
		stringBuilder.append("-");
		stringBuilder.append(date.getMonth()+1);

		holder.forecast_date.setText(stringBuilder.toString());
		try
		{
			
			if(position == 0){
			
				holder.forecast_image.getLayoutParams().width = 300;
				holder.forecast_image.getLayoutParams().height = 300;
				
			}else{
//				
				holder.forecast_image.getLayoutParams().width = 120;
				holder.forecast_image.getLayoutParams().height = 120;

			}
			Picasso.with(forecast_Context).load(R.drawable.weather_big).resize(120, 120).noFade().into(holder.forecast_image);

			holder.forecast_image.setScaleType(ImageView.ScaleType.FIT_XY);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if (this.forecast_listener != null) {

		}
		return convertView;
	}
	static class ViewHolder {
		TextView forecast_text;
		ImageView forecast_image;
		TextView forecast_date;
		
	}

}
