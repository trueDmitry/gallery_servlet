package com.epam.learn.java.ad.gallery.web.view;

import com.epam.learn.java.ad.gallery.model.Exposition;

public class ExpositionView {

	private Exposition exposition;
	
	private boolean isTicketBought;

	public Exposition getExposition() {
		return exposition;
	}

	public void setExposition(Exposition exposition) {
		this.exposition = exposition;
	}

	public boolean getIsTicketBought() {
		return isTicketBought;
	}

	public void setTicketBought(boolean isTicketBought) {
		this.isTicketBought = isTicketBought;
	}

}
