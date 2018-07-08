package com.emirates.reservation.pages;

import com.emirates.crux.SeleniumHelper;

public class SearchResultPage {
	SeleniumHelper wrapper = new SeleniumHelper();

	public void validateRates() {
		wrapper.holdOn(5000);
		wrapper.validateTableDataAsc("Rate", "Rate");
		
	}

}
