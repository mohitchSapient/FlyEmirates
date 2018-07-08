package com.emirates.reservation.pages;


import com.emirates.crux.BaseDriver;
import com.emirates.crux.BaseTest;
import com.emirates.crux.SeleniumHelper;

public class SearchFlightsPage extends BaseTest{
	SeleniumHelper wrapper = new SeleniumHelper();
	String date=wrapper.getFuture_dateFormatDDMMYYYY(7);

	public void checkAvailability() {
		BaseDriver.GetDriver();
		wrapper.setTextBoxValue("DepartureAirport", "DepartureCity");
		wrapper.setTextBoxValue( "ArrivalAirport", "ArivalCity");
		wrapper.setTextBoxValue_Direct("Date", date);
		wrapper.click("Continue");

		
	}

}
