package org.bitpipeline.lib.friendlyjson.complexdata;

import java.util.Map;

import org.bitpipeline.lib.friendlyjson.JSONMappingException;
import org.json.JSONObject;

public class EquipmentBrand extends AbstractEquipment {
	public EquipmentBrand (JSONObject json) throws JSONMappingException {
		super (json);
	}

	public EquipmentBrand (String name, String imageUrl, String description, Map<String, String> info) {
		super (name, imageUrl, description, info);
	}

	public EquipmentBrand (String name, String imageUrl, String description) {
		super (name, imageUrl, description);
	}
}