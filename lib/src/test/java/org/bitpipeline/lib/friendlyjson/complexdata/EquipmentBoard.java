package org.bitpipeline.lib.friendlyjson.complexdata;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bitpipeline.lib.friendlyjson.JSONEntity;
import org.bitpipeline.lib.friendlyjson.JSONMappingException;
import org.json.JSONObject;

public class EquipmentBoard extends AbstractEquipment {

	static public class BoardSpecs extends JSONEntity {
		private String name;
		private float volume;
		private float length;
		private float width;
		private float minSailArea;
		private float maxSailArea;
		private FinFittingType[] finFittings;

		transient private int[] frontFins;
		transient private int[] backFins;
		transient private boolean dagger;

		public BoardSpecs (JSONObject json) throws JSONMappingException {
			super (json);
		}

		public BoardSpecs (float v, float l, float w, float minSailArea, float maxSailArea, FinFittingType ... finsFittingType) {
			this (Integer.toString ((int)v) + " l", v, l, w, minSailArea, maxSailArea, finsFittingType);
		}

		public BoardSpecs (String name, float v, float l, float w, float minSailArea, float maxSailArea, FinFittingType ... finsFittingType) {
			this.name = name;
			this.volume = v; this.length = l; this.width = w;
			this.minSailArea = minSailArea;
			this.maxSailArea = maxSailArea;
			this.finFittings = finsFittingType;

			int firstRealFin = 0;
			if (finsFittingType[0] == FinFittingType.DaggerBoard) {
				this.dagger = true;
				firstRealFin = 1;
			} else {
				this.dagger = false;
			}
			int lastRealFin = finsFittingType.length - 1;

			int numberOfRealFins = finsFittingType.length - firstRealFin;
			switch (numberOfRealFins) {
				case 0:
					this.frontFins  = new int[0];
					this.backFins  = new int[0];
					break;
				case 1:
					this.frontFins = new int[] { firstRealFin };
					this.backFins  = new int[0];
					break;
				case 2:
					this.frontFins = new int[] { firstRealFin    , lastRealFin };
					this.backFins  = new int[0];
					break;
				case 3:
					this.frontFins = new int[] { firstRealFin    , lastRealFin };
					this.backFins  = new int[] { firstRealFin + 1 };
					break;
				case 4:
					this.frontFins = new int[] { firstRealFin     ,  lastRealFin };
					this.backFins  = new int[] { firstRealFin + 1 , lastRealFin -1};
					break;
				default:
					this.frontFins = null;
					this.backFins = null;
					break;
			}
		}

		public String getName () {return this.name;}
		public float getVolume () {return this.volume;}
		public float getLength () {return this.length;}
		public float getWidth () {return this.width;}
		public float getMinSailArea () {return this.minSailArea;}
		public float getMaxSailArea () {return this.maxSailArea;}
		public boolean isSailInRange (float area) { return this.minSailArea < area && area < this.maxSailArea;}
		public FinFittingType[] getFinFittingType () {return this.finFittings;}
		public boolean hasDagger () { return this.dagger; };
		public int[] getFronFinPositions () { return this.frontFins; }
		public int[] getBackFinPositions () { return this.backFins; }

		public FinFittingType getFrontFinFittingType () {
			if (this.frontFins != null && this.frontFins.length > 0)
				return this.finFittings[this.frontFins[0]];
			return null;
		}

		public FinFittingType getBackFinFittingType () {
			if (this.backFins != null && this.backFins.length > 0)
				return this.finFittings[this.backFins[0]];
			return null;
		}
	}

	private EquipmentCollection collection;
	private WindsurfClass[] disciplines;
	transient private Set<WindsurfClass> disciplineListCache = null;
	private BoardSpecs[] specs;
	transient private List<BoardSpecs> specsListCache = null;

	private BoardSpecs selectedSpecs = null;

	public EquipmentBoard (JSONObject obj) throws JSONMappingException {
		super (obj);
	}

	public EquipmentBoard (String name, EquipmentCollection collection, String imageUrl, String description, Map<String, String> info, WindsurfClass[] disciplines, BoardSpecs ... specs) {
		super (name, imageUrl, description, info);
		this.collection = collection;
		this.disciplines = disciplines;
		this.specs = specs;
	}

	public EquipmentBoard (String name, EquipmentCollection collection, String imageUrl, String description, WindsurfClass[] disciplines, BoardSpecs ... specs) {
		super (name, imageUrl, description);
		this.collection = collection;
		this.disciplines = disciplines;
		this.specs = specs;
	}

	public List<BoardSpecs> getSpecs () {
		if (this.specsListCache == null) {
			this.specsListCache = Arrays.asList (this.specs);
		}
		return this.specsListCache;
	}

	public BoardSpecs getSelectedSpecs () {return this.selectedSpecs;}

	public EquipmentCollection getCollection () {return this.collection;}

	public void selectSpecs (int position) {
		this.selectedSpecs = this.specs[position];
	}

	public EquipmentBoard.BoardSpecs selectSpecsCloserToVolume (float volume) {
		if (this.specs.length == 0)
			return null;

		EquipmentBoard.BoardSpecs choice = null;
		float diff = Float.MAX_VALUE;

		for (EquipmentBoard.BoardSpecs spec : this.specs) {
			float tmpDiff = Math.abs(volume - spec.getVolume ());
			 if (tmpDiff < diff) {
				 diff = tmpDiff;
				 choice = spec;
			 }
		}
		this.selectedSpecs = choice;

		return this.selectedSpecs;
	}

	public Set<WindsurfClass> getWindsurfClasses () {
		if (this.disciplineListCache == null) {
			this.disciplineListCache = new HashSet<WindsurfClass> (Arrays.asList (this.disciplines));
		}
		return this.disciplineListCache;
	}
}