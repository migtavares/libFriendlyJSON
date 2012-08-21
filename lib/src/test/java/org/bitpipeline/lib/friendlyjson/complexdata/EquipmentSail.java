package org.bitpipeline.lib.friendlyjson.complexdata;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class EquipmentSail extends AbstractEquipment {
	static final DecimalFormat AREA_NAME_FORMATER = new DecimalFormat ("#0.0 m\u00b2");

	static public class SailSpecs {
		private String name;
		private float area;
		private float mastHeigh;
		private float boomLength;
		private MastType[] mastTypes;

		public SailSpecs (float a, float m, float b, MastType ... mastTypes) {
			this (EquipmentSail.AREA_NAME_FORMATER.format (a), a, m, b, mastTypes);
		}

		public SailSpecs (String name, float a, float m, float b, MastType ... mastTypes) {
			this.name = name;
			this.area = a;
			this.mastHeigh = m;
			this.boomLength = b;
			this.mastTypes = mastTypes;
		}

		public String getName () {return this.name;}
		public float getArea () {return this.area;}
		public float getMastHeight () {return this.mastHeigh;}
		public float getBoomLength () {return this.boomLength;}
		public MastType[] getMastTypes () {return this.mastTypes;}
	}

	final private EquipmentCollection collection;
	final private SailType type;
	final private List<SailSpecs> specs = new ArrayList<SailSpecs> ();
	private SailSpecs selectedSpecs = null;

	public EquipmentSail (String name, SailType type, EquipmentCollection collection, String imageUrl, String description, Map<String, String> info, SailSpecs ... specs) {
		super (name, imageUrl, description, info);
		this.type = type;
		this.collection = collection;
		this.specs.addAll (Arrays.asList (specs));
	}

	public EquipmentSail (String name, SailType type, EquipmentCollection collection, String imageUrl, String description, SailSpecs ... specs) {
		super (name, imageUrl, description);
		this.type = type;
		this.collection = collection;
		this.specs.addAll (Arrays.asList (specs));
	}

	public EquipmentCollection getCollection () {return this.collection;}
	public SailType getSailType () {return this.type;}
	public List<SailSpecs> getSpecs () {return this.specs;}
	public SailSpecs getSelectedSpecs () {return this.selectedSpecs;}

	public void selectSpecs (int position) {
		this.selectedSpecs = this.specs.get (position);
	}

	public EquipmentSail.SailSpecs selectSpecsCloserToArea (float area) {
		if (this.specs.size () == 0)
			return null;

		EquipmentSail.SailSpecs choice = null;
		float diff = Float.MAX_VALUE;

		for (EquipmentSail.SailSpecs spec : this.specs) {
			float tmpDiff = Math.abs (area - spec.getArea ());
			if (tmpDiff < diff) {
				diff = tmpDiff;
				choice = spec;
			}
		}
		this.selectedSpecs = choice;

		return this.selectedSpecs;
	}
}