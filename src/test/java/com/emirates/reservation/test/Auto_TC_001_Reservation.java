package com.emirates.reservation.test;

import java.io.IOException;

import org.testng.annotations.Test;

import com.emirates.crux.BaseTest;
import com.emirates.crux.SeleniumHelper;

import com.emirates.reservation.pages.SearchFlightsPage;
import com.emirates.reservation.pages.SearchResultPage;


public class Auto_TC_001_Reservation extends BaseTest{
	
	SearchFlightsPage sf=new SearchFlightsPage();
	SearchResultPage sr=new SearchResultPage();
	
	

    
@Test
	public void Auto_TC_Reservation_ReturnTicket() throws IOException {
		
		

		testStep="Functional Step 1: Navigate Flight Reservation  home Page & check availability";
		sf.checkAvailability();
	
		

		
		testStep="Functional Step 2: validate flight rates from search result";
		  sr.validateRates();
	}
}
