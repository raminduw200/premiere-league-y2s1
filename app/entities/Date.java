package entities;

import java.io.Serializable;

public class Date implements Serializable {
	private static final long serialVersionUID = -6828098300357262071L;
	private int day;
	private int month;
	private int year;
	
	public Date (int day, int month, int year) {
		setDay(day);
		setMonth(month);
		setYear(year);
	}

	public void setDay(int day){
		if (0< day && day <32){
			this.day = day;
		} else {
			throw new IllegalArgumentException("invalidDate");
		}
	}
	
	public void setMonth(int month){
		if (0< month && month <13){
			this.month = month;
		} else {
			throw new IllegalArgumentException("invalidDate");
		}
	}

	public void setYear(int year){
		if (1000< year){
			this.year = year;
		} else {
			throw new IllegalArgumentException("invalidDate");
		}
	}

	public int getDay(){
		return day;
	}

	public int getMonth(){
		return month;
	}
	
	public int getYear(){
		return year;
	}

	@Override
	public String toString() {
		return String.format("%02d/",day) + String.format("%02d/",month) + year;
	}
}
