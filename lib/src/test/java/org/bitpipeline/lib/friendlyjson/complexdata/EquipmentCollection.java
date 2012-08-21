package org.bitpipeline.lib.friendlyjson.complexdata;

import java.util.Map;

import org.bitpipeline.lib.friendlyjson.JSONMappingException;
import org.json.JSONObject;


public class EquipmentCollection extends AbstractEquipment {
	private EquipmentBrand brand;

	public EquipmentCollection (JSONObject json) throws JSONMappingException {
		super (json);
	}
	
	public EquipmentCollection (String name, String imageUrl, String description, EquipmentBrand brand, Map<String, String> info) {
		super (name, imageUrl, description, info);
		this.brand = brand;
	}

	public EquipmentCollection (String name, String imageUrl, String description, EquipmentBrand brand) {
		super (name, imageUrl, description);
		this.brand = brand;
	}

	public EquipmentBrand getBrand () {
		return this.brand;
	}
}