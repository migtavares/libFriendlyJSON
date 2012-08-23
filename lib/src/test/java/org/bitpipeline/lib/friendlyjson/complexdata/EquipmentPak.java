package org.bitpipeline.lib.friendlyjson.complexdata;

import java.util.ArrayList;
import java.util.List;

import org.bitpipeline.lib.friendlyjson.JSONEntity;
import org.bitpipeline.lib.friendlyjson.JSONMappingException;
import org.json.JSONException;
import org.json.JSONObject;

public class EquipmentPak extends JSONEntity {
	private transient List<EquipmentBrand> brands;
	private transient List<EquipmentCollection> collections;
	private List<EquipmentBoard> boards;

	public EquipmentPak (JSONObject json) throws JSONMappingException {
		super (json);
		init ();
	}

	public EquipmentPak (String source) throws JSONMappingException, JSONException {
		super (source);
		init ();
	}

	public EquipmentPak () {
		init ();
	}

	private void init () {
		if (this.brands == null)
			this.brands = new ArrayList<EquipmentBrand> ();
		if (this.collections == null)
			this.collections = new ArrayList<EquipmentCollection> ();
		if (this.boards == null)
			this.boards = new ArrayList<EquipmentBoard> ();
		else {
			for (EquipmentBoard board : this.boards) {
				addCollection (board.getCollection ());
			}
		}
	}

	public List<EquipmentBrand> getBrands () {
		return this.brands;
	}

	public List<EquipmentCollection> getCollections () {
		return this.collections;
	}

	public List<EquipmentBoard> getBoards () {
		return this.boards;
	}

	public void addBrand (EquipmentBrand brand) {
		for (EquipmentBrand b : this.brands)
			if (b.getName ().equals (brand.getName ()))
				return;
		this.brands.add (brand);
	}

	public void addCollection (EquipmentCollection coll) {
		for (EquipmentCollection c : this.collections)
			if (c.getName ().equals (coll.getName ()))
				return;
		addBrand (coll.getBrand ());
		this.collections.add (coll);
	}

	public void addBoard (EquipmentBoard board) {
		addCollection (board.getCollection ());
		this.boards.add (board);
	}
}