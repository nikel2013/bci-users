package com.bci.users.utils;

import java.util.Calendar;
import java.util.Date;

public class Dates {
	
	public static Date getCurrentDate() {
		Calendar fecha = Calendar.getInstance();
		return fecha.getTime();
	}

	public static Date addToCurrentDate(Integer date) {
		Calendar fecha = Calendar.getInstance();
		fecha.add(Calendar.DAY_OF_YEAR, date);
		return fecha.getTime();		
	}
}