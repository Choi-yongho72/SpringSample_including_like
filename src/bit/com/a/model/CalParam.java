package bit.com.a.model;

import java.io.Serializable;
import java.util.Calendar;

public class CalParam implements Serializable {
	private int year = -1;
	private int month = -1;
	private int day = -1;
	private int lastDay;	// 28, 29, 30, 31
	private int dayOfWeek;	// 요일
	
	private int hour = 0;
	private int min = 0;
	
	Calendar cal = Calendar.getInstance();

	public CalParam() {
	}

	public void calculate() {
		if(month == 0) {
			year--;
			month = 12;
		}else if(month == 13){
			year++;
			month = 1;
		}else if(month < 0) {	// 달력을 최초로 들어왔을때의 설정
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH) + 1;
			if(day < 0) {
				day = cal.get(Calendar.DATE);
			}
		}
		cal.set(year, month -1, 1);
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	public CalParam(int year, int month, int day, int lastDay, int dayOfWeek, int hour, int min, Calendar cal) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.lastDay = lastDay;
		this.dayOfWeek = dayOfWeek;
		this.hour = hour;
		this.min = min;
		this.cal = cal;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getLastDay() {
		return lastDay;
	}

	public void setLastDay(int lastDay) {
		this.lastDay = lastDay;
	}

	public int getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(int dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public Calendar getCal() {
		return cal;
	}

	public void setCal(Calendar cal) {
		this.cal = cal;
	}

	@Override
	public String toString() {
		return "CalParam [year=" + year + ", month=" + month + ", day=" + day + ", lastDay=" + lastDay + ", dayOfWeek="
				+ dayOfWeek + ", hour=" + hour + ", min=" + min + ", cal=" + cal + "]";
	}
}
