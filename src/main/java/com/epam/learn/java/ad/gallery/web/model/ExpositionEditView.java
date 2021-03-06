package com.epam.learn.java.ad.gallery.web.model;

import java.util.List;

import com.epam.learn.java.ad.gallery.app.model.Exposition;
import com.epam.learn.java.ad.gallery.app.model.Room;

public class ExpositionEditView {

	private Exposition expo;

	private List<Room> rooms;

	public Exposition getExpo() {
		return expo;
	}

	public void setExpo(Exposition expo) {
		this.expo = expo;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

}
