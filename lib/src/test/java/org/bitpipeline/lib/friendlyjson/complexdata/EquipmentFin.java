/**
 *
 */
package org.bitpipeline.lib.friendlyjson.complexdata;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author mtavares */
public class EquipmentFin extends AbstractEquipment {
	static final DecimalFormat LENGHT_NAME_FORMATER = new DecimalFormat ("# cm");

	static public class FinSpecs {
		final private String name;
		final private FinFittingType[] fittings;
		final private float length;
		final private float area;
		private FinFittingType selectedFitting = null;

		public FinSpecs (float length, float area, FinFittingType ... fittings) {
			this (EquipmentFin.LENGHT_NAME_FORMATER.format(length*100), length, area, fittings);
		}

		public FinSpecs (String name, float length, float area, FinFittingType ... fittings) {
			this.name = name;
			this.length = length;
			this.area = area;
			this.fittings = fittings;
		}

		public String getName () {return this.name;}
		public FinFittingType[] getFitting () { return this.fittings; }
		public float getLength () { return this.length; }
		public float getArea () { return this.area; }

		public FinFittingType selectFittingType (FinFittingType type) {
			for (FinFittingType finType : this.fittings) {
				if (finType == type) {
					this.selectedFitting = type;
					return this.selectedFitting;
				}
			}
			this.selectedFitting = null;
			return null;
		}

		public FinFittingType getSelectedFittingType () { return this.selectedFitting; }
	}

	private FinType type;
	private EquipmentCollection collection;
	private List<FinSpecs> specs;
	private FinSpecs selectedSpecs = null;

	public EquipmentFin (String name, FinType type, EquipmentCollection collection, String imageUrl, String description, Map<String, String> info, FinSpecs ... specs) {
		super (name, imageUrl, description, info);
		init (type, collection, specs);
	}
	
	public EquipmentFin (String name, FinType type, EquipmentCollection collection, String imageUrl, String description, FinSpecs ... specs) {
		super (name, imageUrl, description);
		init (type, collection, specs);
	}

	private void init (FinType type, EquipmentCollection collection, FinSpecs ... specs) {
		this.type = type;
		this.collection = collection;
		this.specs = Arrays.asList (specs);
	}

	public EquipmentCollection getCollection () {return this.collection;}
	public FinType getFinType () { return this.type; }
	public List<FinSpecs> getSpecs () { return this.specs; }
	public FinSpecs getSelectedSpecs () {return this.selectedSpecs;}

	public void selectSpecs (int position) {
		this.selectedSpecs = this.specs.get (position);
	}

	public FinSpecs selectClosestToLength (float desiredLength) {
		FinSpecs best = null;
		float diff = Float.MAX_VALUE;
		for (FinSpecs spec : this.specs) {
			float tmpDiff = Math.abs (desiredLength - spec.getLength ());
			if (tmpDiff < diff) {
				best = spec;
				diff = tmpDiff;
			}
		}
		this.selectedSpecs = best;
		return best;
	}

	public void clearSelection () { this.selectedSpecs = null; }
}
