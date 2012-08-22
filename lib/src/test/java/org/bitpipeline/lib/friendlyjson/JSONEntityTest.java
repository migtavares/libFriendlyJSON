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
package org.bitpipeline.lib.friendlyjson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 * @author mtavares */
public class JSONEntityTest extends TestCase {

	class Xpto extends JSONEntity {
		String name;

		public Xpto (JSONObject json) throws JSONMappingException {
			super (json);
		}

		public Xpto (String name) {
			this.name = name;
		}
		public String getName () {
			return this.name;
		}
	}
	
	class Entity extends JSONEntity {
		boolean aBoolean;
		byte aByte;
		char aChar;
		short aShort;
		int aInt;
		long aLong;
		float aFloat;
		double aDouble;

		String aString;
		Map<String, String> aMap;
		Map<String, List<String>> aMapOfLists;
		
		List<String> names;
		List<Xpto> xptos;

		transient int transientValue;

		protected FieldSetter getFieldReader (String fieldName) {
			return null;
		}
		
		public Entity () {
			super ();
		}

		public Entity (JSONObject json) throws JSONMappingException {
			super (json);
		}
		
		public Entity (String source) throws JSONMappingException, JSONException {
			super (source);
		}

	}

	Entity createEntity () {
		Entity e = new Entity ();
		e.aBoolean = true;
		e.aByte = 8;
		e.aChar = 'c';
		e.aShort = 127;
		e.aInt = 32000;
		e.aLong = 1000;
		e.aFloat = 3.14f;
		e.aDouble = 3.1415;

		e.aString = "String";

		e.transientValue = 1001;

		e.aMap = new HashMap<String, String> ();

		e.aMap.put ("author", "Max Payne");

		e.aMapOfLists = new HashMap<String, List<String>> ();

		e.aMapOfLists.put ("listA", Arrays.asList (new String[]{"val1a", "val2a", "val3a"}));
		e.aMapOfLists.put ("listB", Arrays.asList (new String[]{"val1b", "val2b", "val3b"}));

		e.names = new ArrayList<String> (2);
		e.names.add ("Miguel");
		e.names.add ("Tavares");
		
		e.xptos = new ArrayList<Xpto> (3);
		e.xptos.add (new Xpto ("Uno"));
		e.xptos.add (new Xpto ("Doi"));
		e.xptos.add (new Xpto ("Tre"));
		
		return e;
	}

	@Test
	public void testJSonSerialization () throws JSONMappingException, JSONException  {
		Entity orig = createEntity ();

		JSONObject json = orig.toJson ();
		assertNotNull (json);
		Entity copy = new Entity (json);

		assertNotNull (copy);
		assertEquals (orig.aBoolean, copy.aBoolean);
		assertEquals (orig.aByte, copy.aByte);
		assertEquals (orig.aChar, copy.aChar);
		assertEquals (orig.aShort, copy.aShort);
		assertEquals (orig.aInt, copy.aInt);
		assertEquals (orig.aLong, copy.aLong);
		assertEquals (orig.aFloat, copy.aFloat, Float.MIN_VALUE * 10.0f);
		assertEquals (orig.aDouble, copy.aDouble, Double.MIN_VALUE * 10.0);
		assertEquals (orig.aString, copy.aString);

		assertFalse (orig.transientValue == copy.transientValue);

		assertNotNull (copy.aMap);
		for (String key : orig.aMap.keySet ()) {
			assertEquals (orig.aMap.get (key), copy.aMap.get (key));
		}

		assertNotNull (copy.aMapOfLists);
		for (String key : orig.aMapOfLists.keySet ()) {
			assertEquals (orig.aMapOfLists.get (key), copy.aMapOfLists.get (key));
		}

		assertNotNull (copy.xptos);
		assertEquals (copy.xptos.size (), orig.xptos.size ());
		for (Xpto x : orig.xptos) {
			boolean found = false;
			for (Xpto y : copy.xptos)
				if (x.getName ().equals (y.getName ()))
					found = true;
			assertTrue (found);
		}

		assertEquals (orig.toString (), copy.toString ());
	}
	
	@Test
	public void testSerializationStrings () throws JSONMappingException, JSONException  {
		Entity orig = createEntity ();

		JSONObject json = orig.toJson ();
		assertNotNull (json);
		String origJsonStr = orig.toString (4);
		assertNotNull (origJsonStr);
		Entity copy = new Entity (origJsonStr);

		assertNotNull (copy);
		assertEquals (orig.aBoolean, copy.aBoolean);
		assertEquals (orig.aByte, copy.aByte);
		assertEquals (orig.aChar, copy.aChar);
		assertEquals (orig.aShort, copy.aShort);
		assertEquals (orig.aInt, copy.aInt);
		assertEquals (orig.aLong, copy.aLong);
		assertEquals (orig.aFloat, copy.aFloat, Float.MIN_VALUE * 10.0f);
		assertEquals (orig.aDouble, copy.aDouble, Double.MIN_VALUE * 10.0);
		assertEquals (orig.aString, copy.aString);

		assertFalse (orig.transientValue == copy.transientValue);

		assertNotNull (copy.aMap);
		for (String key : orig.aMap.keySet ()) {
			assertEquals (orig.aMap.get (key), copy.aMap.get (key));
		}

		assertNotNull (copy.aMapOfLists);
		for (String key : orig.aMapOfLists.keySet ()) {
			assertEquals (orig.aMapOfLists.get (key), copy.aMapOfLists.get (key));
		}

		assertNotNull (copy.xptos);
		assertEquals (copy.xptos.size (), orig.xptos.size ());
		for (Xpto x : orig.xptos) {
			boolean found = false;
			for (Xpto y : copy.xptos)
				if (x.getName ().equals (y.getName ()))
					found = true;
			assertTrue (found);
		}

		assertEquals (orig.toString (), copy.toString ());
	}

}
