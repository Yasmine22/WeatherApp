package com.example.weatherapp;

import java.sql.Timestamp;

//"list":[{
//	"dt":1469534400,
//	"temp":{"day":294.9,"min":291.13,"max":294.9,"night":291.13,"eve":294.42,"morn":294.9},
//	"pressure":1026.18,
//	"humidity":64,
//	"weather":[{
//	  			"id":804,
//	  			"main":"Clouds",
//	  			"description":"overcast clouds",
//	  			"icon":"04d"
//			  }],
//	"speed":4.68,
//    "deg":255,
//     "clouds":88
//    }

public class ListObjModel {
	
	private int dt;
	private TempObjModel temp;
	private double pressure;
	private double humidity;
	private WeatherObjModel weather;
	private double speed;
	private double deg;
	private double clouds;
	public int getDt() {
		return dt;
	}
	public void setDt(int dt) {
		this.dt = dt;
	}
	public TempObjModel getTemp() {
		return temp;
	}
	public void setTemp(TempObjModel temp) {
		this.temp = temp;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public WeatherObjModel getWeather() {
		return weather;
	}
	public void setWeather(WeatherObjModel weather) {
		this.weather = weather;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDeg() {
		return deg;
	}
	public void setDeg(double deg) {
		this.deg = deg;
	}
	public double getClouds() {
		return clouds;
	}
	public void setClouds(double clouds) {
		this.clouds = clouds;
	}
	
	
	

}
