/**
 * Copyright 2012 J. Miguel P. Tavares <mtavares@bitpipeline.eu>
 *         BitPipeline
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***************************************************************************/
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
		}

		public String getName () {return this.name;}
		public float getVolume () {return this.volume;}
		public float getLength () {return this.length;}
		public float getWidth () {return this.width;}
		public float getMinSailArea () {return this.minSailArea;}
		public float getMaxSailArea () {return this.maxSailArea;}
		public boolean isSailInRange (float area) { return this.minSailArea < area && area < this.maxSailArea;}
		public FinFittingType[] getFinFittingType () {return this.finFittings;}
	}

	
	private EquipmentCollection collection;
	private WindsurfClass[] disciplines;
	transient private Set<WindsurfClass> disciplineListCache = null;
	private BoardSpecs[] specs;
	transient private List<BoardSpecs> specsListCache = null;


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

	public EquipmentCollection getCollection () {return this.collection;}

	public Set<WindsurfClass> getWindsurfClasses () {
		if (this.disciplineListCache == null) {
			this.disciplineListCache = new HashSet<WindsurfClass> (Arrays.asList (this.disciplines));
		}
		return this.disciplineListCache;
	}
}